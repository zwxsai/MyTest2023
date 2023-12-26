package com.example.mytest2023.widget.YiChang;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;
import android.os.Process;
import android.text.TextUtils;


import com.example.mytest2023.helper.ToastUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 钟文祥 on 2018/6/10.
 * Describer:全局异常捕捉  (单例就这样写)
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {

    //懒汉单例
    private volatile static CrashHandler instance = null;//volatile是Java提供的一种轻量级的同步机制

    private CrashHandler() {
    }

    //	做了两次null检查，确保了只有第一次调用单例的时候才会做同步，这样也是线程安全的，同时避免了每次都同步的性能损耗
    public static CrashHandler getInstance() {
        if (instance == null) {//防止多个线程同时访问这个类中的synchronized static 方法
            synchronized (CrashHandler.class) {
                if (instance == null) {
                    instance = new CrashHandler();
                }
            }
        }
        return instance;
    }

//	加了同步，虽然线程安全了，但是每次都要同步，会影响性能，毕竟99%的情况下是不需要同步的，
//	public static synchronized CrashHandler getInstance() {
//		if (instance == null) {
//			instance = new CrashHandler();
//		}
//		return instance;
//	}

    private Context context;
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    //存蓄设备信息和日期信息
    private Map<String, String> map = new HashMap<>();
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 初始化
     *
     * @param context
     */
    public void init(Context context) {
        this.context = context;
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(mDefaultHandler);
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
//				1.收集错误信息
//				2.保存错误信息
//				3.上传到服务器

        if (!handleException(e)) {//未处理，调用系统默认的处理器
            if (mDefaultHandler != null) {
                mDefaultHandler.uncaughtException(t, e);
            }
        } else {//人为处理
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            Process.killProcess(Process.myPid());
            System.exit(1);
        }
    }

    /***
     *  是否认为处理异常
     * @param throwable
     * @return true 已经处理 false 没有处理
     */
    private boolean handleException(Throwable throwable) {

        if (throwable == null) {
            return false;
        }
        new Thread() {
            @Override
            public void run() {

                Looper.prepare();
                ToastUtil.showMsg(context, "我崩了");
                Looper.loop();
            }
        }.start();
        //开始收集错误信息
        collectErrorInfo();
        //保存错误信息
        saveErrorInfo(throwable);
        return false;
    }

    private void saveErrorInfo(Throwable e) {
        StringBuffer stringBuffer = new StringBuffer();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            stringBuffer.append(key + "=" + value);
        }
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        e.printStackTrace(printWriter);
        Throwable cause = e.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = e.getCause();
        }
        printWriter.close();

        String result = writer.toString();
        stringBuffer.append(result);

        long curTime = System.currentTimeMillis();
        String time = dateFormat.format(new Date());
        String fileName = "carsh" + time + "-" + curTime + ".log";

        //判断有没有sd卡
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            String path = "/sdcard/crash";
            File dir = new File(path);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(path + fileName);
                fos.write(stringBuffer.toString().getBytes());
            } catch (Exception e1) {
                e1.printStackTrace();
            } finally {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }

    }

    private void collectErrorInfo() {
        PackageManager pm = context.getPackageManager();
        try {
            PackageInfo info = pm.getPackageInfo(context.getPackageName(), PackageManager
                    .GET_SIGNATURES);
            if (info != null) {
                String versionName = TextUtils.isEmpty(info.versionName) ? "未设置版本名称" : info
                        .versionName;
                String versionCode = info.versionCode + "";
                map.put("versionName", versionName);
                map.put("versionCode", versionCode);
            }
            Field[] fields = Build.class.getFields();
            if (fields != null && fields.length > 0) {
                for (Field field : fields) {
                    field.setAccessible(true);
                    try {
                        map.put(field.getName(), field.get(null).toString());
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }
}

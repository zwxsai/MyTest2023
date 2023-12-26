package com.example.mytest2023.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.StrictMode;

import com.example.mytest2023.BuildConfig;
import com.example.mytest2023.greendao.dao.DaoMaster;
import com.example.mytest2023.greendao.dao.DaoSession;
import com.example.mytest2023.greendao.manager.MyOpenHelper;
import com.example.mytest2023.helper.AppHelper;
import com.example.mytest2023.module.Home.MainActivity;
import com.example.mytest2023.widget.YiChang.CrashHandler;
import com.tencent.bugly.crashreport.CrashReport;

import androidx.multidex.MultiDex;

/**
 * Created by 钟文祥 on 2023-04-10.
 * Describer:
 */
public class MyApp extends Application {

    /** 当前activity数 */
    private int activityCount = 0;
    /** 是否在前台 */
    private boolean isForeground = false;

    private static Context context;

    public static Context getContextObject() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();

        //调试模式下开启严格模式检测
        if (BuildConfig.DEBUG) {
            enableStrictMode();
        }

        //自己异常处理
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(this);

        //初始化腾讯bugly
        initBugly();

        //配置数据库
        createGreenDao();
//        createRoomDao();

        //注册生命周期监听
        registerActivityLifecycleCallbacks(new MyActivityLifecycleCallbacks());

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    private void enableStrictMode() {
        // 监测当前线程（UI线程）上的网络、磁盘读写等耗时操作
        StrictMode.setThreadPolicy(
                new StrictMode.ThreadPolicy.Builder()
                        .detectDiskReads() // 监测读磁盘
                        .detectDiskWrites() // 监测写磁盘
                        .detectNetwork() // 监测网络操作
                        .detectCustomSlowCalls() // 监测哪些方法执行慢
                        .detectResourceMismatches() // 监测资源不匹配
                        .penaltyLog() // 打印日志，也可设置为弹窗提示penaltyDialog()或者直接使进程死亡penaltyDeath()
                        .penaltyDropBox() //监测到将信息存到Dropbox文件夹 data/system/dropbox
                        .build()
        );

        // 监测VM虚拟机进程级别的Activity泄漏或者其它资源泄漏
        StrictMode.setVmPolicy(
                new StrictMode.VmPolicy.Builder()
                        .detectActivityLeaks() // 监测内存泄露情况
                        .detectLeakedSqlLiteObjects() // SqlLite资源未关闭，如cursor
                        .detectLeakedClosableObjects() // Closable资源未关闭，如文件流
//                .detectCleartextNetwork() // 监测明文网络
                        .setClassInstanceLimit(MainActivity.class, 1) // 设置某个类的实例上限，可用于内存泄露提示
                        .detectLeakedRegistrationObjects() // 监测广播或者ServiceConnection是否有解注册
                        .penaltyLog()
                        .penaltyDropBox()
                        .build()
        );
    }


    /** 初始化腾讯bugly */
    private void initBugly() {
        Context context = getApplicationContext();
        // 获取当前包名
        String packageName = context.getPackageName();
        // 获取当前进程名
        String processName = AppHelper.getProcessName(android.os.Process.myPid());
        // 设置是否为上报进程
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(context);
        strategy.setUploadProcess(processName == null || processName.equals(packageName));
        CrashReport.initCrashReport(context, BuildConfig.BUGLY_APPID, BuildConfig.DEBUG);
    }

//    private static DBMaster dbMaster;
//
//    private static void createRoomDao() {
//        dbMaster = Room.databaseBuilder(
//                getContextObject(),
//                DBMaster.class,
//                "IQIYIDBMaster"
//        ).build();
//    }
//
//    public static DBMaster getDBMaster() {
//        if (dbMaster == null) {
//            createRoomDao();
//        }
//        return dbMaster;
//    }


    private static DaoSession daoSession;

    private static void createGreenDao() {
        //创建数据库shop.db"
        MyOpenHelper helper = new MyOpenHelper(getContextObject(), BuildConfig.DB_NAME, null);
        //获取可写数据库
        SQLiteDatabase db = helper.getWritableDatabase();
        //获取数据库对象
        DaoMaster daoMaster = new DaoMaster(db);
        //获取Dao对象管理者
        daoSession = daoMaster.newSession();
    }

    public static DaoSession getDaoSession() {
        if (daoSession == null) {
            createGreenDao();
        }
        return daoSession;
    }

    /** Activity 生命周期监听，用于监控app前后台状态切换 */
    private class MyActivityLifecycleCallbacks implements ActivityLifecycleCallbacks {
        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

        }

        @Override
        public void onActivityStarted(Activity activity) {
            activityCount++;
            //回到前台
            if (activityCount == 1) {
                isForeground = true;
            }
        }

        @Override
        public void onActivityResumed(Activity activity) {

        }

        @Override
        public void onActivityPaused(Activity activity) {

        }

        @Override
        public void onActivityStopped(Activity activity) {
            activityCount--;
            if (activityCount == 0) {
                isForeground = false;
            }
        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {

        }
    }


}

//package com.example.mytest2023.base;
//
//import android.annotation.TargetApi;
//import android.app.Activity;
//import android.app.Application;
//import android.content.Context;
//import android.content.Intent;
//import android.database.sqlite.SQLiteDatabase;
//import android.os.Build;
//import android.os.Bundle;
//import android.widget.Toast;
//
//import com.example.mytest2023.BuildConfig;
//import com.example.mytest2023.greendao.dao.DaoMaster;
//import com.example.mytest2023.greendao.dao.DaoSession;
//import com.example.mytest2023.greendao.manager.MyOpenHelper;
//import com.example.mytest2023.helper.AppHelper;
//import com.example.mytest2023.widget.YiChang.CrashHandler;
//import com.tencent.bugly.Bugly;
//import com.tencent.bugly.beta.Beta;
//import com.tencent.bugly.beta.interfaces.BetaPatchListener;
//import com.tencent.bugly.crashreport.CrashReport;
//import com.tencent.tinker.entry.DefaultApplicationLike;
//
//import java.util.Locale;
//
//import androidx.multidex.MultiDex;
//
///**
// * Created by 钟文祥 on 2023-04-27.
// * Describer: application的逻辑都写在这
// */
//public class SampleApplicationLike extends DefaultApplicationLike {
//
//
//    public SampleApplicationLike(Application application, int tinkerFlags,
//                                 boolean tinkerLoadVerifyFlag, long applicationStartElapsedTime,
//                                 long applicationStartMillisTime, Intent tinkerResultIntent) {
//        super(application, tinkerFlags, tinkerLoadVerifyFlag, applicationStartElapsedTime,
//                applicationStartMillisTime, tinkerResultIntent);
//    }
//
//
//    /** 当前activity数 */
//    private int activityCount = 0;
//    /** 是否在前台 */
//    private boolean isForeground = false;
//
//    private static Application context;
//
//    public static Context getContextObject() {
//        return context;
//    }
//
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
//        context = getApplication();
//
//        //自己异常处理
//        CrashHandler crashHandler = CrashHandler.getInstance();
//        crashHandler.init(context);
//
//        //初始化腾讯bugly
//        initBugly();
//
//        //配置数据库
//        createGreenDao();
//
//        //注册生命周期监听
//        context.registerActivityLifecycleCallbacks(new MyActivityLifecycleCallbacks());
//
//        //初始化热修复
//        initHotFit();
//    }
//
//    //初始化热修复
//    private void initHotFit() {
//        // 设置是否开启热更新能力，默认为true
//        Beta.enableHotfix = true;
//        // 设置是否自动下载补丁，默认为true
//        Beta.canAutoDownloadPatch = true;
//        // 设置是否自动合成补丁，默认为true
//        Beta.canAutoPatch = true;
//        // 设置是否提示用户重启，默认为false
//        Beta.canNotifyUserRestart = true;
//        // 补丁回调接口
//        Beta.betaPatchListener = new BetaPatchListener() {
//            @Override
//            public void onPatchReceived(String patchFile) {
//                Toast.makeText(getApplication(), "补丁下载地址" + patchFile, Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onDownloadReceived(long savedLength, long totalLength) {
//                Toast.makeText(getApplication(),
//                        String.format(Locale.getDefault(), "%s %d%%",
//                                Beta.strNotificationDownloading,
//                                (int) (totalLength == 0 ? 0 : savedLength * 100 / totalLength)),
//                        Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onDownloadSuccess(String msg) {
//                Toast.makeText(getApplication(), "补丁下载成功", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onDownloadFailure(String msg) {
//                Toast.makeText(getApplication(), "补丁下载失败", Toast.LENGTH_SHORT).show();
//
//            }
//
//            @Override
//            public void onApplySuccess(String msg) {
//                Toast.makeText(getApplication(), "补丁应用成功", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onApplyFailure(String msg) {
//                Toast.makeText(getApplication(), "补丁应用失败", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onPatchRollback() {
//
//            }
//        };
//
//        // 设置开发设备，默认为false，上传补丁如果下发范围指定为“开发设备”，需要调用此接口来标识开发设备
//        Bugly.setIsDevelopmentDevice(getApplication(), true);
//        // 多渠道需求塞入
//        // String channel = WalleChannelReader.getChannel(getApplication());
//        // Bugly.setAppChannel(getApplication(), channel);
//        // 这里实现SDK初始化，appId替换成你的在Bugly平台申请的appId
//        Bugly.init(getApplication(), BuildConfig.BUGLY_APPID, BuildConfig.DEBUG);
//    }
//
//
//    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
//    @Override
//    public void onBaseContextAttached(Context base) {
//        super.onBaseContextAttached(base);
//        MultiDex.install(base);
//        //安装tinker
//        Beta.installTinker(this);
//    }
//
////    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
////    public void registerActivityLifecycleCallback(Application.ActivityLifecycleCallbacks callbacks) {
////        getApplication().registerActivityLifecycleCallbacks(callbacks);
////    }
//
//    @Override
//    public void onTerminate() {
//        super.onTerminate();
//        Beta.unInit();
//    }
//
//
//    /** 初始化腾讯bugly */
//    private void initBugly() {
////        Context context = getApplication();
//        // 获取当前包名
//        String packageName = context.getPackageName();
//        // 获取当前进程名
//        String processName = AppHelper.getProcessName(android.os.Process.myPid());
//        // 设置是否为上报进程
//        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(context);
//        strategy.setUploadProcess(processName == null || processName.equals(packageName));
//        CrashReport.initCrashReport(context, BuildConfig.BUGLY_APPID, BuildConfig.DEBUG);
//    }
//
//    private static DaoSession daoSession;
//
//    private static void createGreenDao() {
//        //创建数据库shop.db"
//        MyOpenHelper helper = new MyOpenHelper(getContextObject(), BuildConfig.DB_NAME, null);
//        //获取可写数据库
//        SQLiteDatabase db = helper.getWritableDatabase();
//        //获取数据库对象
//        DaoMaster daoMaster = new DaoMaster(db);
//        //获取Dao对象管理者
//        daoSession = daoMaster.newSession();
//    }
//
//    public static DaoSession getDaoSession() {
//        if (daoSession == null) {
//            createGreenDao();
//        }
//        return daoSession;
//    }
//
//    /** Activity 生命周期监听，用于监控app前后台状态切换 */
//    private class MyActivityLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {
//        @Override
//        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
//
//        }
//
//        @Override
//        public void onActivityStarted(Activity activity) {
//            activityCount++;
//            //回到前台
//            if (activityCount == 1) {
//                isForeground = true;
//            }
//        }
//
//        @Override
//        public void onActivityResumed(Activity activity) {
//
//        }
//
//        @Override
//        public void onActivityPaused(Activity activity) {
//
//        }
//
//        @Override
//        public void onActivityStopped(Activity activity) {
//            activityCount--;
//            if (activityCount == 0) {
//                isForeground = false;
//            }
//        }
//
//        @Override
//        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
//
//        }
//
//        @Override
//        public void onActivityDestroyed(Activity activity) {
//
//        }
//    }
//}

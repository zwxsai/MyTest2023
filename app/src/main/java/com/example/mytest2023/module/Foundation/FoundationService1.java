package com.example.mytest2023.module.Foundation;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

/**
 * Created by 钟文祥 on 2023-04-19.
 * Describer:基础 服务  （启动状态）  用startService()
 * 参考  https://blog.csdn.net/javazejian/article/details/52709857
 */
public class FoundationService1 extends Service {

    private static final String TAG = "FoundationService1:";

    //创建服务时调用
    //如果服务已在运行，则不会调用此方法。该方法只被调用一次
    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("shengming", TAG + "onCreate: ");
    }

    //服务执行的操作  用startService会执行,服务即会启动并可在后台无限期运行,通过调用 stopSelf() 或 stopService() 来停止服务
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //接收Activity所传值
        String data = intent.getStringExtra("data");
        int res = super.onStartCommand(intent, flags, startId);
        Log.e("shengming",
                TAG + "onStartCommand: data：" + data + ",flags:" + flags + ",startId:" + startId + ",res:" + res);
        return res;
    }

    // bindService() 才用到onBind
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e("shengming", TAG + "onBind: ");
        return null;
    }

    // unbindService() onUnbind
    @Override
    public boolean onUnbind(Intent intent) {
        Log.e("shengming", TAG + "onUnbind: ");
        return super.onUnbind(intent);
    }

    //销毁服务时调用
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("shengming", TAG + "onDestroy: ");
    }


}

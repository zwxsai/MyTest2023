package com.example.mytest2023.module.Foundation;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.example.mytest2023.helper.BaseThread;

import androidx.annotation.Nullable;

/**
 * Created by 钟文祥 on 2023-04-19.
 * Describer:基础 服务  (绑定状态)  用bindService()
 * * 参考  https://blog.csdn.net/javazejian/article/details/52709857
 */
public class FoundationService2 extends Service {

    private static final String TAG = "FoundationService2:";

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
        return START_STICKY;

        //因内存资源不足而杀死Service
        //这种情况比较容易处理，可将onStartCommand() 方法的返回值设为
        // START_STICKY或START_REDELIVER_INTENT
        // ，该值表示服务在内存资源紧张时被杀死后，在内存资源足够时再恢复。
        // 也可将Service设置为前台服务，这样就有比较高的优先级，在内存资源紧张时也不会被杀掉。

    }


    private SumBind bind = new SumBind();

    // bindService() 才用到onBind
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e("shengming", TAG + "onBind: ");
        return bind;
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
        thread.stopThread();

        thread.interrupt();
        thread = null;

        super.onDestroy();
        Log.e("shengming", TAG + "onDestroy: ");
    }


    public class SumBind extends Binder {
        public FoundationService2 getService() {
            return FoundationService2.this;
        }

        public void setSum() {
            if (thread != null && thread.getThreadState() == BaseThread.ThreadState.Running) {
                thread.setSum(12345);
                thread.setI(0);
            }
        }
    }

    private SumThread thread;

    public void startSumThread() {
        thread = new SumThread();
        thread.start();
        Log.e("shengming", TAG + "线程开始: ");
    }

}

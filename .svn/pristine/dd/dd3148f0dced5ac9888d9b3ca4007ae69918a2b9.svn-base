package com.example.mytest2023.module.Foundation;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

import com.example.mytest2023.R;
import com.example.mytest2023.helper.BaseEventBus;
import com.example.mytest2023.helper.OnItemRecyclerListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by 钟文祥 on 2023/4/12.
 * Describer：服务 service
 * <p>
 * 参考  https://blog.csdn.net/javazejian/article/details/52709857
 */
public class ServiceActivity extends FoundationActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override
    public void initView() {
        txt1.setText("第2页面:");
        txt2.setText("按钮1 启动服务\n按钮2  绑定服务");
        btn1.setVisibility(View.VISIBLE);
        btn1_2.setVisibility(View.VISIBLE);
        btn2.setVisibility(View.VISIBLE);
        btn2_2.setVisibility(View.VISIBLE);
        btn1.setText("启动服务");
        btn2.setText("绑定服务");
        btn3.setVisibility(View.VISIBLE);
        btn3.setText("重置数据12345");

        listener = new OnItemRecyclerListener() {
            @Override
            public void onClick(int viewId, Object object) {
                Intent intent1 = new Intent(ServiceActivity.this, FoundationService1.class);
                switch (viewId) {
                    case R.id.btn1:
                        intent1.putExtra("data", "aaa");
                        startService(intent1);
                        break;
                    case R.id.btn1_2:
                        stopService(intent1);
                        break;
                    case R.id.btn2:
                        controlService(true);
                        break;
                    case R.id.btn2_2:
                        controlService(false);
                        break;
                    case R.id.btn3:
                        if (sumBind != null)
                            sumBind.setSum();
                        break;
                }
            }
        };
    }

    private ServiceConnection scon;//服务连接
    private FoundationService2.SumBind sumBind;
    private static final String TAG = "ServiceActivity：";


    private void controlService(boolean isBind) {
        if (isBind) {
            scon = new ServiceConnection() {
                //绑定服务的时候被回调，在这个方法获取绑定Service传递过来的IBinder对象，
                //通过这个IBinder对象，实现宿主和Service的交互。
                @Override
                public void onServiceConnected(ComponentName name, IBinder service) {
                    Log.e("shengming", TAG + "onServiceConnected: ");
                    sumBind = (FoundationService2.SumBind) service;
                    sumBind.getService().startSumThread();
                }

                // * 当取消绑定的时候被回调。但正常情况下是不被调用的，它的调用时机是当Service服务被意外销毁时，
                // * 例如内存的资源不足时这个方法才被自动调用。
                @Override
                public void onServiceDisconnected(ComponentName name) {
                    Log.e("shengming", TAG + "onServiceDisconnected: ");
                    sumBind = null;
                }
            };
            Intent intent = new Intent(ServiceActivity.this, FoundationService2.class);
            bindService(intent, scon, BIND_AUTO_CREATE);
        } else {
            if (scon != null) {
                sumBind = null;
                try {
                    unbindService(scon);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void OnGetSumAndSumI(BaseEventBus<Integer[]> value) {
        if (value != null) {
            if (value.getCode().equals(BaseEventBus.SumI)) {
                txt2.setText("sum:" + value.getData()[0] + " ,i:" + value.getData()[1]);
            }
        }
    }
}
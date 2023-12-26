package com.example.mytest2023.module.Foundation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mytest2023.R;
import com.example.mytest2023.helper.BaseEventBus;
import com.example.mytest2023.helper.OnItemRecyclerListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by 钟文祥 on 2023-04-20.
 * Describer: BroadcastReceiver
 * 参考   https://blog.csdn.net/suiyue010211/article/details/125951403
 * 有静态注册 和 动态注册 本文用了静态注册
 */
public class BroadcastReceiverActivity extends FoundationActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void initView() {
        txt1.setText("BroadcastReceiver");
        txt2.setText("广播接受者");
        btn1.setVisibility(View.VISIBLE);
        listener = new OnItemRecyclerListener() {
            @Override
            public void onClick(int position, Object object) {
                switch (position) {
                    case R.id.btn1:
                        Intent intent = MyReceiver.getIntent(BroadcastReceiverActivity.this, "ABC");
                        sendBroadcast(intent);
                        break;
                }
            }
        };
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void OnGetBroadcastReceiver(BaseEventBus<String> value) {
        if (value != null && value.getCode().equals(BaseEventBus.MyReceiver)) {
            String data = value.getData();
            txt2.setText(data);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}

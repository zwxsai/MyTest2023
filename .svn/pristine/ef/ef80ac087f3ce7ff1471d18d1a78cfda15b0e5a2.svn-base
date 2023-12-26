package com.example.mytest2023.module.Foundation;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.mytest2023.helper.BaseEventBus;

import org.greenrobot.eventbus.EventBus;

public class MyReceiver extends BroadcastReceiver {

    public static final String action = "MyReceiver";

    public static Intent getIntent(Context context, String value) {
        Intent intent = new Intent(context, MyReceiver.class);
        intent.setAction(action);
        intent.putExtra("data", value);
        return intent;
    }


    @Override
    public void onReceive(Context context, Intent intent) {
        if (!intent.getAction().equals(action)) return;
        String value = intent.getStringExtra("data");
        EventBus.getDefault().post(new BaseEventBus<String>(BaseEventBus.MyReceiver, value));
    }
}
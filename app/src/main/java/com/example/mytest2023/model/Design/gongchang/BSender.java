package com.example.mytest2023.model.design.gongchang;

import android.util.Log;

/**
 * Created by 钟文祥 on 2023-04-21.
 * Describer:
 */
public class BSender implements Sender {
    private static final String TAG = "BSender";

    @Override
    public void send() {
        Log.e(TAG, "send: " + TAG);
    }
}

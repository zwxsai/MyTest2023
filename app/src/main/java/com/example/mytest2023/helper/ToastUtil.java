package com.example.mytest2023.helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mytest2023.R;


/**
 * Created by 钟文祥 on 2018/7/23.
 * Describer:
 */
public class ToastUtil {
    public static Toast toast;
    public static TextView tv;

    public static void showMsg(Context context, String str) {
        showMsg(context, str, Toast.LENGTH_SHORT);
    }

    public static void showMsg(Context context, String str, int duration) {
        if (toast == null) {
            toast = new Toast(context);
            View view = LayoutInflater.from(context).inflate(R.layout.widget_toast, null);
            tv = view.findViewById(R.id.toasttext);
            tv.setText(str);
            toast.setView(view);
            toast.setDuration(duration);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.widget_toast, null);
            tv = view.findViewById(R.id.toasttext);
            tv.setText(str);
            toast.setView(view);
        }
        toast.show();

    }
}

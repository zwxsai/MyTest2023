package com.example.mytest2023.base;

import android.os.Bundle;
import android.view.WindowManager;


import com.example.mytest2023.helper.ExitAppUtils;
import com.example.mytest2023.helper.StatusBarUtil;
import com.example.mytest2023.R;

import androidx.appcompat.app.AppCompatActivity;


/**
 * Created by 钟文祥 on 2019/1/24.
 * Describer: 基类Activity
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ExitAppUtils.getInstance().addActivity(this);
        setStatusBarColor(true, R.color.colorPrimary);
//        StatusBarUtil.setImmerseBar(this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    /***
     * 改变状态栏颜色
     * @param isTextDark  文字、图标是否为黑色 （false为默认的白色）
     * @param colorId 状态栏颜色
     */
    protected void setStatusBarColor(boolean isTextDark, int colorId) {
        StatusBarUtil.setStatusBarMode(this, isTextDark, colorId);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ExitAppUtils.getInstance().removeActivity(this);
    }

}

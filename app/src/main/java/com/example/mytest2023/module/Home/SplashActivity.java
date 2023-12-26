package com.example.mytest2023.module.Home;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.mytest2023.R;
import com.example.mytest2023.helper.SPHelper;
import com.example.mytest2023.helper.StatusBarUtil;

/**
 * Created by 钟文祥 on 2023/4/12.
 * Describer：启动页   启动页白屏问题及如何实现全屏显示
 * https://blog.csdn.net/crazestone0614/article/details/128178426
 */
public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Android各种屏，刘海屏，打孔屏满屏显示: https://blog.csdn.net/cention168/article/details/124037221
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            WindowManager.LayoutParams lp = getWindow().getAttributes();
            //用在android高版本满屏，穿过摄像头。
            lp.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
            getWindow().setAttributes(lp);
        } else {
            getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        final View decorView = getWindow().getDecorView();
        //View.SYSTEM_UI_FLAG_HIDE_NAVIGATION用来隐藏底部悬浮条
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        StatusBarUtil.setImmerseBar(this);
        setContentView(R.layout.activity_splash);

        //判断版本
        if (SPHelper.currentVersionIsNew(this)) {
            GuideActivity.openActivity(SplashActivity.this);
            finish();
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    MainActivity.openActivity(SplashActivity.this);
                    finish();
                }
            }, 3000);

        }

    }


}
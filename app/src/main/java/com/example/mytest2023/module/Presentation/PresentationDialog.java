package com.example.mytest2023.module.Presentation;

import android.app.Presentation;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.mytest2023.R;
import com.example.mytest2023.helper.ToastUtil;
import com.unity3d.player.IUnityPlayerLifecycleEvents;
import com.unity3d.player.MultiWindowSupport;
import com.unity3d.player.UnityPlayer;

/**
 * Created by 钟文祥 on 2024/6/11.
 * Describer: 双屏
 */
public class PresentationDialog extends Presentation implements IUnityPlayerLifecycleEvents {
    private static final String TAG = "PresentationActivity";
    private UnityPlayer mUnityPlayer;
    private Context activityContext;

    public PresentationDialog(Context outerContext, Display display) {
//            , UnityPlayer mUnityPlayer) {
        super(outerContext, display);
        activityContext = outerContext;
//        this.mUnityPlayer = mUnityPlayer;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_presentation);
        Log.e(TAG, "副屏 onCreate: ");

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            getWindow().setType(WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY);
//        } else {
//            getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
//        }


        Button btn1 = (Button) findViewById(R.id.btn_shuang_1);
        Button btn2 = (Button) findViewById(R.id.btn_shuang_2);
        RelativeLayout rl = (RelativeLayout) findViewById(R.id.rl);

        //        String cmdLine = this.getIntent().getStringExtra("unity");
//        this.getIntent().putExtra("unity", cmdLine);

        mUnityPlayer = new UnityPlayer(activityContext);
        this.mUnityPlayer.requestFocus();
        rl.addView(mUnityPlayer);
        UnityPlayer.UnitySendMessage("DispatcherManager", "isShowMulti", "1");

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showMsg(getContext(), "副屏btn1");
                Log.e(TAG, "onClick: 副屏btn1");
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showMsg(getContext(), "副屏btn2");
                Log.e(TAG, "onClick: 副屏btn2");
                UnityPlayer.UnitySendMessage("DispatcherManager", "isShowMulti", "1");
            }
        });


    }

    //=======

    public void onUnityPlayerUnloaded() {
//        this.moveTaskToBack(true);
    }

    public void onUnityPlayerQuitted() {
    }

    //
//    protected void onNewIntent(Intent intent) {
//        super.onNewIntent(intent);
//        this.setIntent(intent);
//        this.mUnityPlayer.newIntent(intent);
//        Log.e(TAG, "副屏 onNewIntent: ");
//    }
//
//
//    protected void onDestroy() {
//        this.mUnityPlayer.destroy();
//        super.onDestroy();
//        Log.e(TAG, "副屏 onDestroy: ");
//    }
    protected void onStart() {
        super.onStart();
        if (this.mUnityPlayer != null)
            this.mUnityPlayer.resume();
        Log.e(TAG, "副屏 onStart: ");
    }

    protected void onStop() {
        super.onStop();
        Log.e(TAG, "副屏 onStop: ");
//        if (MultiWindowSupport.getAllowResizableWindow(this)) {
//            Log.e(TAG, "副屏 onStop: 2");
//        }

        if (this.mUnityPlayer != null) {
            this.mUnityPlayer.pause();
//            this.mUnityPlayer.destroy();
        }

    }


//    protected void onPause() {
//        Log.e(TAG, "副屏 onPause: ");
//        super.onPause();
//        MultiWindowSupport.saveMultiWindowMode(this);
//        if (!MultiWindowSupport.getAllowResizableWindow(this)) {
//            Log.e(TAG, "副屏 onPause: 2");
////            this.mUnityPlayer.pause();
//        }
//    }

//    protected void onResume() {
//        super.onResume();
//        if (!MultiWindowSupport.getAllowResizableWindow(this) || MultiWindowSupport.isMultiWindowModeChangedToTrue
//        (this)) {
//            this.mUnityPlayer.resume();
//        }
//        Log.e(TAG, "副屏 onResume: ");
//    }
//
//    public void onLowMemory() {
//        super.onLowMemory();
//        this.mUnityPlayer.lowMemory();
//        Log.e(TAG, "副屏 onLowMemory: ");
//    }
//
//    public void onTrimMemory(int level) {
//        super.onTrimMemory(level);
//        if (level == 15) {
//            this.mUnityPlayer.lowMemory();
//        }
//        Log.e(TAG, "副屏 onTrimMemory: " + level);
//    }
//
//    public void onConfigurationChanged(Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//        this.mUnityPlayer.configurationChanged(newConfig);
//        Log.e(TAG, "副屏 onConfigurationChanged: ");
//    }


    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (this.mUnityPlayer != null)
            this.mUnityPlayer.windowFocusChanged(hasFocus);
        Log.e(TAG, "副屏 onWindowFocusChanged: ");
    }

    public boolean dispatchKeyEvent(KeyEvent event) {
        if (this.mUnityPlayer == null) return false;
        return event.getAction() == 2 ? this.mUnityPlayer.injectEvent(event) : super.dispatchKeyEvent(event);
    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (this.mUnityPlayer == null) return false;
        return this.mUnityPlayer.injectEvent(event);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (this.mUnityPlayer == null) return false;
        return this.mUnityPlayer.injectEvent(event);
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (this.mUnityPlayer == null) return false;
        return this.mUnityPlayer.injectEvent(event);
    }

    public boolean onGenericMotionEvent(MotionEvent event) {
        if (this.mUnityPlayer == null) return false;
        return this.mUnityPlayer.injectEvent(event);
    }
}
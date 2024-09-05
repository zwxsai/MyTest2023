package com.example.mytest2023.module.Presentation;

import android.app.Presentation;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.hardware.display.DisplayManager;
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
import com.google.gson.Gson;
import com.unity3d.player.IUnityPlayerLifecycleEvents;
import com.unity3d.player.MultiWindowSupport;
import com.unity3d.player.UnityPlayer;
import com.unity3d.player.UnityPlayerActivity;

import androidx.appcompat.app.AppCompatActivity;

public class PresentationActivity extends AppCompatActivity implements IUnityPlayerLifecycleEvents {
    private static final String TAG = "PresentationActivity";
    private UnityPlayer mUnityPlayer0;
    private Presentation presentation = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presentation);
        Log.e(TAG, "主屏 onCreate: ");


        Button btn0 = (Button) findViewById(R.id.btn_shuang_0);
        Button btn1 = (Button) findViewById(R.id.btn_shuang_1);
        Button btn2 = (Button) findViewById(R.id.btn_shuang_2);
        Button btn3 = (Button) findViewById(R.id.btn_shuang_3);
        Button btn4 = (Button) findViewById(R.id.btn_shuang_4);
        Button btn5 = (Button) findViewById(R.id.btn_shuang_5);
        RelativeLayout rl0 = (RelativeLayout) findViewById(R.id.rl0);
        //        String cmdLine = this.getIntent().getStringExtra("unity");
//        this.getIntent().putExtra("unity", cmdLine);

        mUnityPlayer0 = new UnityPlayer(this);
        this.mUnityPlayer0.requestFocus();
        rl0.addView(mUnityPlayer0.getView());


        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showMsg(PresentationActivity.this, "主屏btn0");
                Log.e(TAG, "onClick: 主屏btn0");
                differentDisplay();
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showMsg(PresentationActivity.this, "主屏btn1");
                String json = new Gson().toJson(new CameraDisplay(true, false, false));
                Log.e(TAG, "onClick: 主屏 1号相机:" + json);
                UnityPlayer.UnitySendMessage("DispatcherManager", "OnDispatchMessage", json);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showMsg(PresentationActivity.this, "主屏btn2");
                String json = new Gson().toJson(new CameraDisplay(false, true, false));
                Log.e(TAG, "onClick: 主屏 2号相机:" + json);
                UnityPlayer.UnitySendMessage("DispatcherManager", "OnDispatchMessage", json);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showMsg(PresentationActivity.this, "主屏 单屏");
                Log.e(TAG, "onClick: 主屏 单屏:");
                UnityPlayer.UnitySendMessage("DispatcherManager", "isShowMulti", "0");
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showMsg(PresentationActivity.this, "主屏 双屏");
                Log.e(TAG, "onClick: 主屏 双屏:");
                UnityPlayer.UnitySendMessage("DispatcherManager", "isShowMulti", "1");
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (presentation != null) {
                    presentation.dismiss();
                    presentation = null;
                }
                finish();
            }
        });
    }

    private void differentDisplay() {
        DisplayManager mDisplayManager = (DisplayManager) getSystemService(Context.DISPLAY_SERVICE);
        Display[] displays = mDisplayManager.getDisplays();
        Log.e(TAG, "differentDisplay: 有屏幕：" + displays.length);
        if (displays.length > 1) {
            //displays[0] 主屏，displays[1] 副屏
            Display display = displays[2];
            if (presentation == null) {
                presentation = new PresentationDialog(this, display);
                presentation.show();
            } else {
                presentation.dismiss();
                presentation = null;
            }

        }

    }


    public void onUnityPlayerUnloaded() {
        this.moveTaskToBack(true);
    }

    public void onUnityPlayerQuitted() {
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        this.setIntent(intent);
        if (this.mUnityPlayer0 != null)
            this.mUnityPlayer0.newIntent(intent);
        Log.e(TAG, "主屏 onNewIntent: ");
    }

    protected void onStart() {
        super.onStart();
        if (MultiWindowSupport.getAllowResizableWindow(this)) {
            if (this.mUnityPlayer0 != null)
                this.mUnityPlayer0.resume();
        }
        Log.e(TAG, "主屏 onStart: ");
    }

    protected void onResume() {
        super.onResume();
        if (!MultiWindowSupport.getAllowResizableWindow(this) || MultiWindowSupport.isMultiWindowModeChangedToTrue(this)) {
            if (this.mUnityPlayer0 != null)
                this.mUnityPlayer0.resume();
        }
        Log.e(TAG, "主屏 onResume: ");
    }

    protected void onPause() {
        Log.e(TAG, "主屏 onPause: ");
        super.onPause();
        MultiWindowSupport.saveMultiWindowMode(this);
        if (!MultiWindowSupport.getAllowResizableWindow(this)) {
            Log.e(TAG, "主屏 onPause: 2");
//            if (this.mUnityPlayer0 != null)
//                this.mUnityPlayer0.pause();
        }
    }

    protected void onStop() {
        super.onStop();
        Log.e(TAG, "主屏 onStop: ");
        if (MultiWindowSupport.getAllowResizableWindow(this)) {
            Log.e(TAG, "主屏 onStop: 2");
        }
    }

    protected void onDestroy() {
        if (this.mUnityPlayer0 != null)
            this.mUnityPlayer0.destroy();
        super.onDestroy();
        Log.e(TAG, "主屏 onDestroy: ");
    }

    public void onLowMemory() {
        super.onLowMemory();
        if (this.mUnityPlayer0 != null)
            this.mUnityPlayer0.lowMemory();
        Log.e(TAG, "主屏 onLowMemory: ");
    }

    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        if (level == 15) {
            if (this.mUnityPlayer0 != null)
                this.mUnityPlayer0.lowMemory();
        }
        Log.e(TAG, "主屏 onTrimMemory: " + level);
    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (this.mUnityPlayer0 != null)
            this.mUnityPlayer0.configurationChanged(newConfig);
        Log.e(TAG, "主屏 onConfigurationChanged: ");
    }

    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (this.mUnityPlayer0 != null)
            this.mUnityPlayer0.windowFocusChanged(hasFocus);
        Log.e(TAG, "主屏 onWindowFocusChanged: " + hasFocus);
    }


    public boolean dispatchKeyEvent(KeyEvent event) {
        if (this.mUnityPlayer0 == null) return false;
        return event.getAction() == 2 ? this.mUnityPlayer0.injectEvent(event) : super.dispatchKeyEvent(event);
    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (this.mUnityPlayer0 == null) return false;
        return this.mUnityPlayer0.injectEvent(event);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (this.mUnityPlayer0 == null) return false;
        return this.mUnityPlayer0.injectEvent(event);
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (this.mUnityPlayer0 == null) return false;
        return this.mUnityPlayer0.injectEvent(event);
    }

    public boolean onGenericMotionEvent(MotionEvent event) {
        if (this.mUnityPlayer0 == null) return false;
        return this.mUnityPlayer0.injectEvent(event);
    }


    private class CameraDisplay {
        private boolean camera1;
        private boolean camera2;
        private boolean camera3;

        public CameraDisplay() {
        }

        public CameraDisplay(boolean camera1, boolean camera2, boolean camera3) {
            this.camera1 = camera1;
            this.camera2 = camera2;
            this.camera3 = camera3;
        }

        public boolean isCamera1() {
            return camera1;
        }

        public void setCamera1(boolean camera1) {
            this.camera1 = camera1;
        }

        public boolean isCamera2() {
            return camera2;
        }

        public void setCamera2(boolean camera2) {
            this.camera2 = camera2;
        }

        public boolean isCamera3() {
            return camera3;
        }

        public void setCamera3(boolean camera3) {
            this.camera3 = camera3;
        }
    }


}
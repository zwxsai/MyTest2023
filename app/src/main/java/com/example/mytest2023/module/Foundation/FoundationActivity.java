package com.example.mytest2023.module.Foundation;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mytest2023.R;
import com.example.mytest2023.helper.OnItemRecyclerListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by 钟文祥 on 2023/4/12.
 * Describer：基础功能页 ---生命周期（只有一个中心控件）
 * 参考 https://blog.csdn.net/xiajun2356033/article/details/78741121
 * <p>
 * * 参考 https://blog.csdn.net/qq_38350635/article/details/88878310   singleTop以这个url内容为准
 * * https://blog.csdn.net/xiajun2356033/article/details/78741121   这个urlsingleTop内容不准
 */
public abstract class FoundationActivity extends AppCompatActivity implements View.OnClickListener {


    protected OnItemRecyclerListener listener;
    protected String data;
    protected TextView txt1;
    protected TextView txt2;
    protected Button btn1;
    protected Button btn1_2;
    protected Button btn2;
    protected Button btn2_2;
    protected Button btn3;
    protected Button btn3_2;
    protected Button btn4;
    protected Button btn4_2;


    //在Activity创建时调用，通常做一些初始化设置；
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foundation);
        data = getIntent().getStringExtra("data");
        findView();
        initView();
        Log.e("shengming", getViewNameStr() + "onCreate: 开始状态,   传来参数data:" + data);

    }


    //在Activity即将可见时调用
    @Override
    protected void onStart() {
        super.onStart();
        Log.e("shengming", getViewNameStr() + "onStart: 开始状态");
    }

    //在Activity已可见，获取焦点
    @Override
    protected void onResume() {
        super.onResume();
        Log.e("shengming", getViewNameStr() + "onResume: 开始状态 -》运行状态");
    }

    //在被其他Activity覆盖或锁屏时调用；
    @Override
    protected void onPause() {
        super.onPause();
        Log.e("shengming", getViewNameStr() + "onPause: -》暂停状态，  若回到前台进入onResume()");
    }

    //在Activity对用户不可见时调用
    @Override
    protected void onStop() {
        super.onStop();
        Log.e("shengming", getViewNameStr() + "onStop: -》停止状态,   若回到前台进入onRestart()  -> onStart()");
    }

    //在Activity从停止状态再次启动时调用；
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("shengming", getViewNameStr() + "onRestart: 要回到前台");
    }

    //在Activity销毁时调用
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("shengming", getViewNameStr() + "onDestroy: -》摧毁状态");
    }

    //横竖屏
    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.e("shengming",
                getViewNameStr() + "横竖屏 onConfigurationChanged: " + newConfig.toString());
    }

    /**
     * 复用回调  启动模式
     * 1：Standard  标准模式：
     * 使用场景：默认使用大多数场景
     * <p>
     * 2：singleTop 栈顶复用模式：
     * 复用该Activity置于栈顶不会重新创建Activity的实列，
     * 使用场景：适合接收通知内容显示页面
     * <p>
     * 3：SingleTask 栈内复用模式：
     * 如果栈内已经存在要启动的Activity实例，就把该Activity栈上面的activity清出栈，
     * 让该activity置于栈顶,如果复用它的onNewIntent方法会被回调,但是onCreate.onStart方法不会执行,因为它没有改变
     * 使用场景：APP的首页，当你需要到某个页面时，关闭之前所打开的Activity可以用到。
     * <p>
     * 4：SingleInstance单实例模式 ：
     * 启动的Activity实例单独占用一个任务栈，也就是启动该模式的Activity同时给它分配一个任务栈。
     * 使用场景：singleInstance适合需要与程序分离开的页面。例如闹铃提醒，将闹铃提醒与闹铃设置分离。
     * singleInstance不要用于中间页面，如果用于中间页面，跳转会有问题，比如：A -> B (singleInstance) -> C，
     * 完全退出后，在此启动，首先打开的是B。
     */
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.e("shengming", getViewNameStr() + "复用 onNewIntent: ");
    }


    public abstract void initView();

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
            case R.id.btn2:
            case R.id.btn3:
            case R.id.btn4:
            case R.id.btn1_2:
            case R.id.btn2_2:
            case R.id.btn3_2:
            case R.id.btn4_2:
                if (listener != null) {
                    listener.onClick(view.getId(), null);
                }
                break;
        }
    }

    protected String getViewNameStr() {
        return txt1.getText().toString();
    }

    private void findView() {
        txt1 = findViewById(R.id.txt1);
        txt2 = findViewById(R.id.txt2);
        btn1 = findViewById(R.id.btn1);
        btn1_2 = findViewById(R.id.btn1_2);
        btn2 = findViewById(R.id.btn2);
        btn2_2 = findViewById(R.id.btn2_2);
        btn3 = findViewById(R.id.btn3);
        btn3_2 = findViewById(R.id.btn3_2);
        btn4 = findViewById(R.id.btn4);
        btn4_2 = findViewById(R.id.btn4_2);
        txt1.setOnClickListener(this);
        txt2.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn1_2.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn2_2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn3_2.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn4_2.setOnClickListener(this);
    }


}
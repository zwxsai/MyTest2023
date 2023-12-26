package com.example.mytest2023.module.Foundation;

import android.os.Bundle;

/**
 * Created by 钟文祥 on 2023/4/12.
 * Describer：ContentProvider
 * 参考 https://blog.csdn.net/carson_ho/article/details/76101093
 */
public class ContentProviderActivity extends FoundationActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initView() {
        txt1.setText("第3页面:");
        txt2.setText("第3页面:");
    }
}
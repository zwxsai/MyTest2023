package com.example.mytest2023.base;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.mytest2023.R;
import com.example.mytest2023.helper.RecyclerViewHelper;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by 钟文祥 on 2019/7/q0.
 * Describer: list基类Activity
 */
public class BaseListActivity extends BaseActivity implements View.OnClickListener {

    private boolean isShowTopView = false;
    private String title = "";
    private ImageView btnBack;
    private TextView txtTitle;
    private LinearLayout viewTop;
    private RecyclerView baseRv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_list);
        initView();
    }

    protected void initTopView(boolean isShowTopView, String title) {
        this.isShowTopView = isShowTopView;
        this.title = title;
    }

    private void initView() {
        if (isShowTopView) {
            viewTop.setVisibility(View.VISIBLE);
            txtTitle.setText(title);
        } else {
            viewTop.setVisibility(View.GONE);
        }

        RecyclerViewHelper.init(this, baseRv, new LinearLayoutManager(this), true);
        baseRv.setAdapter(initAdapter());
        btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(this);
        txtTitle = findViewById(R.id.txt_title);
        viewTop = findViewById(R.id.view_top);
        baseRv = findViewById(R.id.base_rv);
    }

    protected RecyclerView.Adapter initAdapter() {
        return null;
    }

    public RecyclerView getBaseRv() {
        return baseRv;
    }

    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                break;
        }
    }
}

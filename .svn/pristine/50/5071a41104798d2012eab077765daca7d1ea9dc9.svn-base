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
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 钟文祥 on 2019/7/q0.
 * Describer: list基类Activity
 */
public class BaseListActivity extends BaseActivity {

    @BindView(R.id.base_rv) RecyclerView baseRv;
    @BindView(R.id.btn_back) ImageView btnBack;
    @BindView(R.id.view_top) LinearLayout viewTop;
    @BindView(R.id.txt_title) TextView txtTitle;

    private boolean isShowTopView = false;
    private String title = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_list);
        ButterKnife.bind(this);
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
    }

    protected RecyclerView.Adapter initAdapter() {
        return null;
    }

    public RecyclerView getBaseRv() {
        return baseRv;
    }

    @OnClick({R.id.btn_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
        }
    }
}

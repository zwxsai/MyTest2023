package com.example.mytest2023.module.Home;

import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.mytest2023.R;
import com.example.mytest2023.base.BaseFragment;
import com.google.android.material.appbar.AppBarLayout;

import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.core.widget.NestedScrollView;
import butterknife.BindView;

/**
 * Created by 钟文祥 on 2023-04-14.
 * Describer: 首页5 吸顶 +输入框渐变缩小
 */
public class Home5Fragment extends BaseFragment {


    @BindView(R.id.backgroundIcon) ImageView backgroundIcon;
    @BindView(R.id.iv_search) ImageView ivSearch;
    @BindView(R.id.search) RelativeLayout search;
    @BindView(R.id.ml) MotionLayout ml;
    @BindView(R.id.app_layout) AppBarLayout appLayout;
    @BindView(R.id.scrollable) NestedScrollView scrollable;

    public static Home5Fragment newInstance() {
        Home5Fragment fragment = new Home5Fragment();
        return fragment;
    }

    @Override
    public Object setLayout() {
        return R.layout.fragment_home5;
    }

    @Override
    public void initView() {
        appLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                //绑定 MotionLayout偏移量
                ml.setProgress((float) -verticalOffset / appBarLayout.getTotalScrollRange());
            }
        });


    }

    @Override
    public void onFragmentVisibleChange(boolean isVisible) {

    }

    @Override
    public void onFragmentFirstVisible() {

    }


}

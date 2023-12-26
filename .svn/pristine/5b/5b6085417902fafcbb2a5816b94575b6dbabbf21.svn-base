package com.example.mytest2023.module.Home;

import android.util.Log;

import com.example.mytest2023.R;
import com.example.mytest2023.base.BaseFragment;
import com.example.mytest2023.helper.UIHelper;

import androidx.core.widget.NestedScrollView;
import butterknife.BindView;

/**
 * Created by 钟文祥 on 2023-04-14.
 * Describer: 首页2 吸顶 +toolbar
 */
public class Home2Fragment extends BaseFragment {

    @BindView(R.id.rv_demo1_content) NestedScrollView rvDemo1Content;
    private int topHeight;

    public static Home2Fragment newInstance() {
        Home2Fragment fragment = new Home2Fragment();
        return fragment;
    }

    @Override
    public Object setLayout() {
        return R.layout.fragment_home2;
    }

    @Override
    public void initView() {
        topHeight = UIHelper.dip2px(getActivity(), 100);
        Log.e("aaa1", "topHeight: " + topHeight);

    }

    @Override
    public void onFragmentVisibleChange(boolean isVisible) {

    }

    @Override
    public void onFragmentFirstVisible() {

    }
}

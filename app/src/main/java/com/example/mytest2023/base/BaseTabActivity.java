package com.example.mytest2023.base;

import android.os.Bundle;

import com.example.mytest2023.R;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 钟文祥 on 2019/7/26.
 * Describer: 基类Tab Activity
 */
public class BaseTabActivity extends BaseActivity {

    @BindView(R.id.tabLayout) TabLayout tabLayout;
    @BindView(R.id.viewPager) ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_tab);
        ButterKnife.bind(this);

    }

    public void initView(List<String> titles, List<Fragment> fragments) {
        viewPager.setAdapter(new BaseTabAdapter(getSupportFragmentManager(), titles, fragments));
        viewPager.setOffscreenPageLimit(2);
        tabLayout.setupWithViewPager(viewPager);

    }


}

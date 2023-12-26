package com.example.mytest2023.module.viewpager2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.os.Bundle;

import com.example.mytest2023.R;
import com.example.mytest2023.base.BaseActivity;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

/**
 * https://www.cnblogs.com/spiropentadiene/p/16847849.html tab和viewpager绑定
 * https://qa.1r1g.com/sf/ask/3876058161/  不绑定
 */
public class ViewPager2Activity extends BaseActivity {

    @BindView(R.id.tab) TabLayout tabLayout;
    @BindView(R.id.viewpager2) ViewPager2 viewPager2;


    private List<Fragment> list = new ArrayList<>();
    private String[] title = {"标题1", "标题2", "标题3"};
    private ViewPager2Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager2);
        ButterKnife.bind(this);

        //添加Fragment
        list.add(BlankTestFragment.newInstance(title[0], "#03A9F4"));
        list.add(BlankTestFragment.newInstance(title[1], "#8BC34A"));
        list.add(BlankTestFragment.newInstance(title[2], "#009688"));
        adapter = new ViewPager2Adapter(this, list);
        viewPager2.setAdapter(adapter);
        viewPager2.setUserInputEnabled(false);

        //TabLayout与ViewPager2绑定   不论xml有多少个Tabitem 都以代码的Fragment数对应titles为准
        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(title[position]);
            }
        }).attach();

        //监听选中的下标
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
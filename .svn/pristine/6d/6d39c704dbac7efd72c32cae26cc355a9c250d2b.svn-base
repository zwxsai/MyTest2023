package com.example.mytest2023.module.viewpager2;

import android.content.Context;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

/**
 * Created by 钟文祥 on 2023/6/15.
 * Describer:
 */
public class ViewPager2Adapter extends FragmentStateAdapter {


    private List<Fragment> list;

    public ViewPager2Adapter(@NonNull FragmentActivity fragmentActivity, List<Fragment> list) {
        super(fragmentActivity);
        this.list = list;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return list.get(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

package com.example.mytest2023.widget.cusbanner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.mytest2023.R;

import java.util.ArrayList;
import java.util.List;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

/**
 * Created by 钟文祥 on 2023/10/25.
 * Describer:
 */
public abstract class BaseViewPagerAdapter<T> extends PagerAdapter implements AViewPager.OnPageChangeListener {

    private List<T> data = new ArrayList<>();

    private Context mContext;
    private AutoViewPager mView;

    private OnAutoViewPagerItemClickListener listener;

    public BaseViewPagerAdapter(List<T> t) {
        this.data = t;
    }

    public BaseViewPagerAdapter(Context context, AutoViewPager viewPager) {
        this.mContext = context;
        mView = viewPager;
        mView.setAdapter(this);
        mView.addOnPageChangeListener(this);
        mView.setCurrentItem(0);
    }


    public BaseViewPagerAdapter(Context context, AutoViewPager viewPager,
                                OnAutoViewPagerItemClickListener listener) {
        this.mContext = context;
        mView = viewPager;
        this.listener = listener;
        mView.setAdapter(this);
        mView.addOnPageChangeListener(this);
        mView.setCurrentItem(0);
    }

    public BaseViewPagerAdapter(Context context, List<T> data, AutoViewPager viewPager,
                                OnAutoViewPagerItemClickListener listener) {
        this.mContext = context;
        mView = viewPager;
        this.data = data;
        this.listener = listener;
        mView.setAdapter(this);
        mView.addOnPageChangeListener(this);
        mView.setCurrentItem(0);

        mView.start();
//        mView.updatePointView(getRealCount());
    }

    public void add(T t) {
        data.add(t);
        notifyDataSetChanged();
//        mView.updatePointView(getRealCount());
    }

    @Override
    public int getCount() {
        return data == null ? 0 : Integer.MAX_VALUE;
    }

    public int getRealCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ImageView) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        ImageView view = (ImageView) LayoutInflater.from(mContext)
                .inflate(R.layout.view_banner_item, container, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onItemClick(position % getRealCount(),
                            data.get(position % getRealCount()));
                }
            }
        });

        loadImage(view, position % getRealCount(), data.get(position % getRealCount()));
        container.addView(view);

        return view;
    }

    public abstract void loadImage(ImageView view, int position, T t);

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
//        mView.onPageSelected(position % getRealCount());
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public interface OnAutoViewPagerItemClickListener<T> {
        void onItemClick(int position, T t);
    }
}
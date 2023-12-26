package com.example.mytest2023.module.Foundation;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mytest2023.R;
import com.example.mytest2023.base.BaseActivity;
import com.example.mytest2023.helper.ToastUtil;
import com.example.mytest2023.module.Banner.BannerData;
import com.example.mytest2023.widget.cusbanner.AutoViewPager;
import com.example.mytest2023.widget.cusbanner.BaseViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AutoViewPagerActivity extends BaseActivity {

    @BindView(R.id.AutoViewPager) AutoViewPager autoViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.mytest2023.R.layout.activity_auto_view_pager);
        ButterKnife.bind(this);

        List<BannerData> list = new ArrayList<>();
        list.add(new BannerData("http://pic9.iqiyipic" +
                ".com/image/20231007/f1/f4/a_100535261_m_601_m25_848_478.jpg"));
        list.add(new BannerData("http://pic8.iqiyipic" +
                ".com/image/20231007/35/08/a_100506589_m_601_m11_848_478.jpg"));
        list.add(new BannerData("http://pic9.iqiyipic" +
                ".com/image/20230928/90/b3/a_100522203_m_601_m14_848_478.jpg"));
        list.add(new BannerData("https://img2.baidu.com/it/u=2096266411," +
                "1930497475&fm=253&fmt=auto&app=138&f=JPEG?w=1280&h=477"));
        list.add(new BannerData("https://img2.baidu.com/it/u=1367821108," +
                "121397947&fm=253&fmt=auto&app=138&f=JPEG?w=1200&h=500"));

        RequestOptions options = new RequestOptions();
        options.fitCenter().skipMemoryCache(false)
                .dontAnimate()
                .placeholder(R.drawable.ic_img_loading)
                .error(R.drawable.ic_img_loading_fail)
                .fallback(R.drawable.ic_img_loading_fail);
        BaseViewPagerAdapter adapter = new BaseViewPagerAdapter(this, list, autoViewPager,
                new BaseViewPagerAdapter.OnAutoViewPagerItemClickListener() {
                    @Override
                    public void onItemClick(int position, Object o) {
                        ToastUtil.showMsg(AutoViewPagerActivity.this, "点击：" + position);
                    }
                }) {
            @Override
            public void loadImage(ImageView view, int position, Object o) {
                Glide.with(AutoViewPagerActivity.this).load(list.get(position).getUrl()).apply(options).into(view);
            }
        };


    }


    @Override
    protected void onResume() {
        super.onResume();
        autoViewPager.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        autoViewPager.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        autoViewPager.onDestroy();
    }
}
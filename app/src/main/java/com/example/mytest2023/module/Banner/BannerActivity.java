package com.example.mytest2023.module.Banner;

import butterknife.BindView;
import butterknife.ButterKnife;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import com.example.mytest2023.R;
import com.example.mytest2023.base.BaseActivity;
import com.example.mytest2023.helper.ToastUtil;
import com.zhpan.bannerview.BannerViewPager;
import com.zhpan.bannerview.constants.IndicatorGravity;
import com.zhpan.bannerview.constants.PageStyle;
import com.zhpan.bannerview.utils.BannerUtils;
import com.zhpan.indicator.enums.IndicatorStyle;

import java.util.ArrayList;
import java.util.List;

public class BannerActivity extends BaseActivity {

    @BindView(R.id.main_vp) BannerViewPager<BannerData> bannerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);
        ButterKnife.bind(this);

        initBanner();
    }

    private void initBanner() {
        List<BannerData> list = new ArrayList<>();
        list.add(new BannerData("http://pic9.iqiyipic" +
                ".com/image/20231007/f1/f4/a_100535261_m_601_m25_848_478.jpg "));
        list.add(new BannerData("http://pic8.iqiyipic" +
                ".com/image/20231007/35/08/a_100506589_m_601_m11_848_478.jpg"));
        list.add(new BannerData("http://pic9.iqiyipic" +
                ".com/image/20230928/90/b3/a_100522203_m_601_m14_848_478.jpg"));
        list.add(new BannerData("https://img2.baidu.com/it/u=2096266411," +
                "1930497475&fm=253&fmt=auto&app=138&f=JPEG?w=1280&h=477"));
        list.add(new BannerData("https://img2.baidu.com/it/u=1367821108," +
                "121397947&fm=253&fmt=auto&app=138&f=JPEG?w=1200&h=500"));

        bannerView
                .setCanLoop(true)  //是否开启循环 | 默认值true|
                .setAutoPlay(true)//是否开启自动轮播 | 默认值true|
                .setInterval(6000) //自动轮播时间间隔 |单位毫秒，默认值3000 |
                .setScrollDuration(2000)//设置页面滚动时间 |单位毫秒，默认值800 |
                .setRoundCorner(50)//设置外部圆角 |默认无圆角 需要SDK_INT>=LOLLIPOP(API 21) |
                .setOnPageClickListener(new BannerViewPager.OnPageClickListener() {
                    @Override
                    public void onPageClick(View clickedView, int position) {
                        ToastUtil.showMsg(BannerActivity.this, "第" + position + "个");
                    }
                })//页面点击事件
                .showIndicatorWhenOneItem(true)//是否显示指示器|默认值true

                .setPageStyle(PageStyle.MULTI_PAGE)  /*设置画廊模式*/
                .setRevealWidth(BannerUtils.dp2px(250))  /*设置宽度*/
                .setPageMargin(BannerUtils.dp2px(10)) /*页面的外边距*/
                .setAdapter(new MyBannerViewPager(this))  //设置适配器    必须

                .setIndicatorStyle(IndicatorStyle.ROUND_RECT)  //指示器样式
                .setIndicatorGravity(IndicatorGravity.CENTER) //指示器位置
                .setIndicatorSliderColor(getResources().getColor(R.color.green),
                        getResources().getColor(R.color.red)) //指示器 选中与不选中颜色
                .setIndicatorSliderGap(BannerUtils.dp2px(2)) /*指示器的间距*/
                .create(list);  /*设置数据*/   /*必须*/
    }


}
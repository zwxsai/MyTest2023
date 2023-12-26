package com.example.mytest2023.module.Home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.mytest2023.R;
import com.example.mytest2023.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.bgabanner.BGABanner;
import cn.bingoogolapple.bgabanner.BGALocalImageSize;

/**
 * Created by 钟文祥 on 2023/4/12.
 * Describer：引导页
 */
public class GuideActivity extends BaseActivity {

    @BindView(R.id.banner_guide_background) BGABanner bannerGuideBackground;
    @BindView(R.id.banner_guide_foreground) BGABanner bannerGuideForeground;
//    @BindView(R.id.tv_guide_skip) TextView tvGuideSkip;
//    @BindView(R.id.btn_guide_enter) Button btnGuideEnter;


    public static void openActivity(Context context) {
        Intent intent = new Intent(context, GuideActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_guide);
        ButterKnife.bind(this);
        initGuide();

    }

    private void initGuide() {
        setListener();
        processLogic();
    }


    private void setListener() {
        /**
         * 设置进入按钮和跳过按钮控件资源 id 及其点击事件
         * 如果进入按钮和跳过按钮有一个不存在的话就传 0
         * 在 BGABanner 里已经帮开发者处理了防止重复点击事件
         * 在 BGABanner 里已经帮开发者处理了「跳过按钮」和「进入按钮」的显示与隐藏
         */
        bannerGuideForeground.setEnterSkipViewIdAndDelegate(R.id.btn_guide_enter, R.id.tv_guide_skip,
                new BGABanner.GuideDelegate() {
                    @Override
                    public void onClickEnterOrSkip() {
                        startActivity(new Intent(GuideActivity.this, MainActivity.class));
                        finish();
                    }
                });
    }

    private void processLogic() {
        // Bitmap 的宽高在 maxWidth maxHeight 和 minWidth minHeight 之间
        BGALocalImageSize localImageSize = new BGALocalImageSize(720, 1280, 320, 640);
        // 设置数据源
        bannerGuideBackground.setData(localImageSize, ImageView.ScaleType.CENTER_CROP,
                R.drawable.widget_guide_background_1,
                R.drawable.widget_guide_background_2,
                R.drawable.widget_guide_background_3);

        bannerGuideForeground.setData(localImageSize, ImageView.ScaleType.CENTER_CROP,
                R.drawable.widget_guide_foreground_1,
                R.drawable.widget_guide_foreground_2,
                R.drawable.widget_guide_foreground_3);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // 如果开发者的引导页主题是透明的，需要在界面可见时给背景 Banner 设置一个白色背景，避免滑动过程中两个 Banner 都设置透明度后能看到 Launcher
        bannerGuideBackground.setBackgroundResource(android.R.color.white);
    }
}
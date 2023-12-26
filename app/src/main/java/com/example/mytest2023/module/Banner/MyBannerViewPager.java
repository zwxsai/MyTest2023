package com.example.mytest2023.module.Banner;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mytest2023.R;
import com.zhpan.bannerview.BaseBannerAdapter;
import com.zhpan.bannerview.BaseViewHolder;

/**
 * Created by 钟文祥 on 2023/6/19.
 * Describer:
 */
public class MyBannerViewPager extends BaseBannerAdapter<BannerData> {

    private Context context;
    private RequestOptions options;

    public MyBannerViewPager(Context context) {
        this.context = context;

        this.options = new RequestOptions();
        this.options.fitCenter().skipMemoryCache(false)
                .dontAnimate()
                .placeholder(R.drawable.ic_img_loading)
                .error(R.drawable.ic_img_loading_fail)
                .fallback(R.drawable.ic_img_loading_fail);//.transform(new
        // GlideRoundTransform(context));ic_img_loading_fail

    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.adapter_banner_item;
    }

    @Override
    protected void bindData(BaseViewHolder<BannerData> holder, BannerData item, int position,
                            int pageSize) {
        Glide.with(context).load(item.getUrl()).apply(options).into((ImageView) holder.findViewById(R.id.banner_image));

    }


}

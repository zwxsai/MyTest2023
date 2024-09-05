package com.example.mytest2023.module.iqiyi;

import android.content.Context;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.mytest2023.R;
import com.example.mytest2023.model.home.Home1Item;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Created by 钟文祥 on 2023/6/14.
 * Describer:采用BaseRecyclerViewAdapterHelper
 * https://zhuanlan.zhihu.com/p/503238433
 * https://blog.csdn.net/chehec2010/article/details/83902263
 */
public class Home1BRVAHAdapter extends BaseQuickAdapter<Home1Item, BaseViewHolder> {

    private Context context;
    private RequestOptions options;

    public Home1BRVAHAdapter(Context context, @Nullable List<Home1Item> data) {
        super(R.layout.adapter_home1, data);
        this.context = context;
        this.options = new RequestOptions();
        this.options.fitCenter().skipMemoryCache(false)
                .dontAnimate()
                .placeholder(R.drawable.ic_img_loading)
                .error(R.drawable.ic_img_loading_fail)
                .fallback(R.drawable.ic_img_loading_fail);//.transform(new
        // GlideRoundTransform(context));ic_img_loading_fail

        for (int i = 0; i < 5; i++) {
            if (i == 3) {
                break;
            }
        }

        List<Integer> list = new ArrayList<Integer>();
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            list.forEach(integer -> {
                if(integer==3){

                }
            });
        }
    }


    @Override
    protected void convert(@NonNull BaseViewHolder holder, Home1Item item) {

        TextView txtIndex = holder.getView(R.id.txtIndex);
        ImageView iv_item = holder.getView(R.id.iv_item);
        TextView txtName = holder.getView(R.id.txtName);
        TextView txtSubName = holder.getView(R.id.txtSubName);


        if (item.getUrl() != null && !item.getUrl().equals("")) {
            Glide.with(context).load(item.getUrl()).apply(options).into(iv_item);
            iv_item.setVisibility(View.VISIBLE);
        } else {
            iv_item.setVisibility(View.GONE);
        }

        txtName.setText(item.getName());
        txtSubName.setText(item.getSubName());
//        getItemPosition(item)
        txtIndex.setText((holder.getLayoutPosition() + 1) + "");
        if (holder.getLayoutPosition() % 2 == 0) {
            txtIndex.setBackgroundResource(R.drawable.shape_shi_orange_banyuan);
        } else {
            txtIndex.setBackgroundResource(R.drawable.shape_shi_black_trans_yuan);
        }
//        //这个需要adapter.setOnItemClickListener
//        holder.addOnClickListener(R.id.txtSubName);
//        //为子控件添加事件
//        holder.setOnClickListener(R.id.txtSubName, new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

    }
}

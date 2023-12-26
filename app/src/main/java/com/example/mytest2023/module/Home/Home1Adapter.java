package com.example.mytest2023.module.Home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mytest2023.R;
import com.example.mytest2023.helper.DialogHelper;
import com.example.mytest2023.helper.OnItemRecyclerListener;
import com.example.mytest2023.model.home.Home1Item;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by 钟文祥 on 2023-04-18.
 * Describer:
 */
public class Home1Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Home1Item> list;
    private OnItemRecyclerListener listener;

    private RequestOptions options;


    public Home1Adapter(Context context, List<Home1Item> list, OnItemRecyclerListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;

        this.options = new RequestOptions();
        this.options.fitCenter().skipMemoryCache(false)
                .dontAnimate()
                .placeholder(R.drawable.ic_img_loading)
                .error(R.drawable.ic_img_loading_fail)
                .fallback(R.drawable.ic_img_loading_fail);//.transform(new
        // GlideRoundTransform(context));ic_img_loading_fail
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_home1, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder,
                                 @SuppressLint("RecyclerView") int position) {
        MyViewHolder holder = (MyViewHolder) viewHolder;
        Home1Item item = list.get(position);

        if (item.getUrl() != null && !item.getUrl().equals("")) {
            Glide.with(context).load(item.getUrl()).apply(options).into(holder.iv_item);
            holder.iv_item.setVisibility(View.VISIBLE);
        } else {
            holder.iv_item.setVisibility(View.GONE);
        }

        holder.txtName.setText(item.getName());
        holder.txtSubName.setText(item.getSubName());
        holder.txtIndex.setText((position + 1) + "");
        if (position % 2 == 0) {
            holder.txtIndex.setBackgroundResource(R.drawable.shape_shi_orange_banyuan);
        } else {
            holder.txtIndex.setBackgroundResource(R.drawable.shape_shi_black_trans_yuan);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (item.getCls() == null) {
                    DialogHelper.showIOSAlerView(context, "提示", "不能进入", null, false, null);
                    return;
                }
                if (listener != null) {
                    listener.onClick(position, item);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }


    private class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView txtIndex;
        private ImageView iv_item;
        private TextView txtName;
        private TextView txtSubName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtIndex = itemView.findViewById(R.id.txtIndex);
            iv_item = itemView.findViewById(R.id.iv_item);
            txtName = itemView.findViewById(R.id.txtName);
            txtSubName = itemView.findViewById(R.id.txtSubName);
        }
    }
}

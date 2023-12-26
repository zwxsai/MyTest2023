package com.example.mytest2023.module.mvvm;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mytest2023.R;
import com.example.mytest2023.databinding.AdapterRoomBinding;
import com.example.mytest2023.helper.DialogHelper;
import com.example.mytest2023.helper.OnItemRecyclerListener;
import com.example.mytest2023.model.home.Home1Item;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by 钟文祥 on 2023/6/16.
 * Describer:
 */
public class RoomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Home1Item> list;
    private OnItemRecyclerListener listener;
    private RequestOptions options;

    public RoomAdapter(Context context, List<Home1Item> list, OnItemRecyclerListener listener) {
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
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AdapterRoomBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.adapter_room, parent, false);
        return new MyViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        MyViewHolder newViewHolder = (MyViewHolder) holder;
        AdapterRoomBinding binding = (AdapterRoomBinding) newViewHolder.binding;
        Home1Item item = list.get(position);

        if (item.getUrl() != null && !item.getUrl().equals("")) {
            Glide.with(context).load(item.getUrl()).apply(options).into(binding.ivItem);
            binding.ivItem.setVisibility(View.VISIBLE);
        } else {
            binding.ivItem.setVisibility(View.GONE);
        }

        binding.txtName.setText(item.getName());
        binding.txtSubName.setText(item.getSubName());
        binding.txtIndex.setText((position + 1) + "");
        if (position % 2 == 0) {
            binding.txtIndex.setBackgroundResource(R.drawable.shape_shi_orange_banyuan);
        } else {
            binding.txtIndex.setBackgroundResource(R.drawable.shape_shi_black_trans_yuan);
        }

        binding.getRoot().setOnClickListener(new View.OnClickListener() {
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
        public ViewDataBinding binding;

        public MyViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

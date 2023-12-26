package com.example.mytest2023.module.Home;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mytest2023.R;
import com.example.mytest2023.model.home.Demo5Bean;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by 钟文祥 on 2023-04-14.
 * Describer:
 */
public class Home3Adapter extends RecyclerView.Adapter {
    public static final int VIEW_TYPE_TITLE = 0;
    public static final int VIEW_TYPE_MENU = 1;
    public static final int VIEW_TYPE_FOOTER = 2;
    private final LayoutInflater inflater;
    private List<Demo5Bean> data;
    private Context context;
    private int lastH;

    public Home3Adapter(List<Demo5Bean> data, Context context, int lastH) {
        this.data = data;
        this.context = context;
        this.lastH = lastH;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == data.size()) {
            return VIEW_TYPE_FOOTER;
        } else {
            Demo5Bean demo5Bean = data.get(position);
            return demo5Bean.getItemType();
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        switch (viewType) {
            case VIEW_TYPE_TITLE:
                viewHolder = new Demo5TitleViewHolder(inflater.inflate(R.layout.adapter_demo5_title, parent, false));
                break;
            case VIEW_TYPE_MENU:
                viewHolder = new Demo5MenuViewHolder(inflater.inflate(R.layout.adapter_demo5_menu, parent, false));
                break;
            case VIEW_TYPE_FOOTER:
                View view = new View(parent.getContext());
                view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, lastH));
                viewHolder = new Demo5FooterViewHolder(view);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case VIEW_TYPE_TITLE:
                ((Demo5TitleViewHolder) holder).tv_item_demo5_title.setText(data.get(position).getName());
                Log.i("TTT", holder.itemView.getMeasuredHeight() + "VIEW_TYPE_TITLE");

                break;
            case VIEW_TYPE_MENU:
                ((Demo5MenuViewHolder) holder).tv_item_demo5_menu.setText(data.get(position).getName());
                Log.i("TTT", holder.itemView.getMeasuredHeight() + "VIEW_TYPE_MENU");
                break;
        }
    }

    @Override
    public int getItemCount() {
        return data.size() + 1;
    }

    public static class Demo5TitleViewHolder extends RecyclerView.ViewHolder {
        private final TextView tv_item_demo5_title;

        public Demo5TitleViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_item_demo5_title = itemView.findViewById(R.id.tv_item_demo5_title);
        }
    }

    public static class Demo5MenuViewHolder extends RecyclerView.ViewHolder {
        private final TextView tv_item_demo5_menu;

        public Demo5MenuViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_item_demo5_menu = itemView.findViewById(R.id.tv_item_demo5_menu);
        }
    }

    public static class Demo5FooterViewHolder extends RecyclerView.ViewHolder {
        public Demo5FooterViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}


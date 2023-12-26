package com.example.mytest2023.helper;

import android.content.Context;


import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;


/**
 * Created by 钟文祥 on 2018/7/31.
 * Describer: RecyclerView 工具类
 */
public class RecyclerViewHelper {

    /**
     * @param context
     * @param recyclerView
     * @param layout
     * @param isAddItemDecoration 是否添加Android自带的分割线
     */
    public static void init(Context context, RecyclerView recyclerView,
                            RecyclerView.LayoutManager layout, boolean isAddItemDecoration) {
        recyclerView.setLayoutManager(layout);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        recyclerView.setHasFixedSize(true);
        if (isAddItemDecoration) {
            //添加Android自带的分割线
            recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration
                    .VERTICAL));
        }
        RecyclerView.ItemAnimator animator = recyclerView.getItemAnimator();
        if (animator instanceof SimpleItemAnimator) {
            ((SimpleItemAnimator) animator).setSupportsChangeAnimations(false);
        }
    }

    /***
     *
     * @param context
     * @param recyclerView
     * @param layout
     * @param isAddItemDecoration 是否添加Android自带的分割线
     */
    public static void initX(Context context, XRecyclerView recyclerView, RecyclerView
            .LayoutManager layout, boolean isAddItemDecoration) {

        recyclerView.setLayoutManager(layout);

        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        recyclerView.setHasFixedSize(true);
        if (isAddItemDecoration) {
            //添加自定义的分割线 去掉footerview的分割线
            recyclerView.addItemDecoration(new RecyclerViewDivider(context, LinearLayoutManager
                    .HORIZONTAL));
        }
        RecyclerView.ItemAnimator animator = recyclerView.getItemAnimator();
        if (animator instanceof SimpleItemAnimator) {
            ((SimpleItemAnimator) animator).setSupportsChangeAnimations(false);
        }
        //加载时样式
        recyclerView.setRefreshProgressStyle(ProgressStyle.BallZigZag);
        recyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallZigZag);
    }


//    /**
//     * 查找信息
//     * 从第一个完整可见item逆序遍历，如果初始位置为0，则不执行方法内循环
//     */
//    public static void computeBoundsBackward(List<ZoomImgInfo>
//                                                     zoomImgInfoList, LinearLayoutManager
//                                                     linearLayoutManager, int imageViewId) {
//        int firstCompletelyVisiblePos = linearLayoutManager.findFirstVisibleItemPosition();
//        for (int i = firstCompletelyVisiblePos; i < zoomImgInfoList.size(); i++) {
//            View itemView = linearLayoutManager.findViewByPosition(i);
//            Rect bounds = new Rect();
//            if (itemView != null) {
//                View thumbView = itemView.findViewById(imageViewId);
//                thumbView.getGlobalVisibleRect(bounds);
//            }
//            zoomImgInfoList.get(i).setBounds(bounds);
//        }
//    }
//
//
//    /**
//     * 查找信息
//     * 从第一个完整可见item逆序遍历，如果初始位置为0，则不执行方法内循环
//     */
//    public static void computeBoundsBackwardX(List<ZoomImgInfo>
//                                                      zoomImgInfoList, LinearLayoutManager
//                                                      linearLayoutManager, int imageViewId) {
//        int firstCompletelyVisiblePos = linearLayoutManager.findFirstVisibleItemPosition();
//        for (int i = firstCompletelyVisiblePos + 1; i < zoomImgInfoList.size(); i++) {
//            View itemView = linearLayoutManager.findViewByPosition(i);
//            Rect bounds = new Rect();
//            if (itemView != null) {
//                View thumbView = itemView.findViewById(imageViewId);
//                thumbView.getGlobalVisibleRect(bounds);
//            }
//            zoomImgInfoList.get(i - 1).setBounds(bounds);
//        }
//    }


    /***
     *
     * @param list1 全局变量
     * @param list2 局部获取变量
     * @param rv
     * @param adapter
     * @param pageNum
     */
    public static void loadDataX(List list1, List list2, XRecyclerView rv, RecyclerView.Adapter
            adapter, int pageNum) {

        if (pageNum == 0) {
            list1.clear();
        }
        list1.addAll(list2);
        adapter.notifyDataSetChanged();

        if (pageNum == 0) {
            rv.refreshComplete();
        } else {
            rv.loadMoreComplete();
        }
    }
}

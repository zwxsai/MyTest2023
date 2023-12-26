package com.example.mytest2023.module.swiperefresh;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.mytest2023.R;
import com.example.mytest2023.helper.RecyclerViewHelper;
import com.example.mytest2023.helper.ToastUtil;
import com.example.mytest2023.model.home.Home1Item;
import com.example.mytest2023.module.Foundation.BroadcastReceiverActivity;
import com.example.mytest2023.module.Foundation.CusViewActivity;
import com.example.mytest2023.module.Foundation.DesignActivity;
import com.example.mytest2023.module.Foundation.Open2Activity;
import com.example.mytest2023.module.Foundation.ServiceActivity;
import com.example.mytest2023.module.Foundation.ShuJuJieGouActivity;
import com.example.mytest2023.module.Foundation.SuanFaActivity;
import com.example.mytest2023.module.Foundation.ThreadActivity;
import com.example.mytest2023.module.iqiyi.Home1BRVAHAdapter;
import com.example.mytest2023.module.mvp.view.LoginTestActivityMVP;
import com.example.mytest2023.module.mvvm.LoginTestActivityMVVM;
import com.example.mytest2023.module.mvvm.RoomMvvmActivity;
import com.example.mytest2023.module.viewpager2.ViewPager2Activity;
import com.example.mytest2023.widget.other.CustomLoadMoreView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SwiperefreshActivity extends AppCompatActivity {

    @BindView(R.id.rv) RecyclerView rv;
//    @BindView(R.id.swipeRefreshLayout) SwipeRefreshLayout swipeRefreshLayout;

    private Home1BRVAHAdapter brvahAdapter;
    private List<Home1Item> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swiperefresh);
        ButterKnife.bind(this);
        initData(false);
        initView();
    }

    @SuppressLint("ResourceAsColor")
    private void initView() {
        RecyclerViewHelper.init(this, rv, new LinearLayoutManager(this), true);

        brvahAdapter = new Home1BRVAHAdapter(this, list);
//        View headView = getLayoutInflater().inflate(R.layout.top_view, (ViewGroup) mRecyclerView.getParent(), false);
//        brvahAdapter.addHeaderView(headView); //添加头部

        View nodataView = getLayoutInflater().inflate(R.layout.adapter_no_data, (ViewGroup) rv.getParent(), false);
        brvahAdapter.setEmptyView(nodataView);// 没有数据的时候默认显示该布局

        brvahAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);//加载动画
        brvahAdapter.isFirstOnly(false);//动画默认只执行一次,如果想重复执行可设置
        //事件
        brvahAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //获取其他控件
                TextView tv = (TextView) adapter.getViewByPosition(rv, position, R.id.txtName);
                ToastUtil.showMsg(SwiperefreshActivity.this, tv.getText().toString());
            }
        });
        //子控件的点击事件
//        brvahAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
////                if (view.getId() == R.id.ic_collect)
//            }
//        });


//        //swipeRefreshLayout解决下拉问题
//        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
//        swipeRefreshLayout.setProgressBackgroundColorSchemeColor(getResources().getColor(R.color.sandybrown));
//        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
////                list.clear();
////                initData(true);
////                brvahAdapter.notifyDataSetChanged();
//                brvahAdapter.getData().clear();
//                brvahAdapter.addData(initData(true));
//                swipeRefreshLayout.setRefreshing(false);
//
//            }
//        });


//        brvahAdapter.setUpFetchEnable(true);
//        brvahAdapter.setUpFetchListener(new BaseQuickAdapter.UpFetchListener() {
//            @Override
//            public void onUpFetch() {
//                startUpFetch();
//            }
//        });


        // 当列表滑动到倒数第N个Item的时候(默认是1)回调onLoadMoreRequested方法
        brvahAdapter.setPreLoadNumber(1);

        //设置自定义加载更多布局
        brvahAdapter.setLoadMoreView(new CustomLoadMoreView());

        //上拉加载更多  会出现加载中
        //// 滑动最后一个Item的时候回调onLoadMoreRequested方法
        brvahAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                rv.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadMore();
                    }
                }, 200);

            }
        }, rv);

        //默认第一次加载会进入onLoadMoreRequested回调，如果不需要可以配置：
//        brvahAdapter.disableLoadMoreIfNotFullPage();

        rv.setAdapter(brvahAdapter);

    }


    private void startUpFetch() {
        brvahAdapter.setUpFetching(true);
        rv.postDelayed(new Runnable() {
            @Override
            public void run() {
                brvahAdapter.getData().clear();
                int size = brvahAdapter.getData().size();
                brvahAdapter.setNewData(initData(true));
//                brvahAdapter.addData(initData(true));
                brvahAdapter.setUpFetching(false);
                if (brvahAdapter.getData().size() >= 16) {
                    size = brvahAdapter.getData().size();
                    brvahAdapter.setUpFetchEnable(false);
                }
            }
        }, 300);

    }

    private boolean isErr = false;

    private void loadMore() {
        if (brvahAdapter.getData().size() >= 16) {
            brvahAdapter.loadMoreEnd(); //全部数据加载完成
        } else {
            if (isErr) {
                brvahAdapter.addData(new Home1Item<String>("Room数据库" + list.size(), "增删改查", null,
                        RoomMvvmActivity.class,
                        null));
                brvahAdapter.loadMoreComplete(); //本次加载成功
            } else {
                //获取更多数据失败
                isErr = true;
                ToastUtil.showMsg(SwiperefreshActivity.this, "失败");
                brvahAdapter.loadMoreFail();
            }

//            brvahAdapter.loadMoreFail();       //本次加载失败
        }
    }


    private List<Home1Item> initData(boolean isADD) {

        if (isADD) {
            list.add(new Home1Item<String>("Activity", "生命周期与启动模式", null, Open2Activity.class, "生命周期与启动模式"));
            list.add(new Home1Item<String>("Service", "服务开启与绑定", null, ServiceActivity.class, null));
            list.add(new Home1Item<String>("BroadcastReceiver", "发送广播", null, BroadcastReceiverActivity.class, null));
            list.add(new Home1Item<String>("设计模式", "单例、工厂、建造者、观察者", null, DesignActivity.class, "单例、工厂、建造者、观察者"));
            list.add(new Home1Item<String>("数据结构", "数据接口", null, ShuJuJieGouActivity.class, null));
            list.add(new Home1Item<String>("算法", "插入排序、选择排序、冒泡排序、快速排序", null, SuanFaActivity.class,
                    "插入排序、选择排序、冒泡排序、快速排序"));
            list.add(new Home1Item<String>("MVP", "MVP", null, LoginTestActivityMVP.class, null));
            list.add(new Home1Item<String>("MVVM", "MVVM", null, LoginTestActivityMVVM.class, null));
        }

        list.add(new Home1Item<String>("多线程与异步", "多线程与异步", null, ThreadActivity.class, null));
        list.add(new Home1Item<String>("自定义view", "自定义view", null, CusViewActivity.class, null));
        list.add(new Home1Item<String>("Room数据库", "增删改查", null, RoomMvvmActivity.class, null));
        list.add(new Home1Item<String>("viewpager2", "viewpager2", null, ViewPager2Activity.class, null));
        return list;
    }
}
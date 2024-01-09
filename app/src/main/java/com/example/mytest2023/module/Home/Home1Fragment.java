package com.example.mytest2023.module.Home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.util.Log;

import com.example.mytest2023.R;
import com.example.mytest2023.base.BaseFragment;
import com.example.mytest2023.helper.OnItemRecyclerListener;
import com.example.mytest2023.helper.RecyclerViewHelper;
import com.example.mytest2023.model.home.Home1Item;
import com.example.mytest2023.module.Banner.BannerActivity;
import com.example.mytest2023.module.Coroutine.CoroutineActivity;
import com.example.mytest2023.module.Foundation.AutoViewPagerActivity;
import com.example.mytest2023.module.Foundation.BroadcastReceiverActivity;
import com.example.mytest2023.module.Foundation.CusViewActivity;
import com.example.mytest2023.module.Foundation.DesignActivity;
import com.example.mytest2023.module.kl.KotlinActivity;
import com.example.mytest2023.module.Foundation.Open2Activity;
import com.example.mytest2023.module.Foundation.ServiceActivity;
import com.example.mytest2023.module.Foundation.ShuJuJieGouActivity;
import com.example.mytest2023.module.Foundation.SuanFaActivity;
import com.example.mytest2023.module.Foundation.ThreadActivity;
import com.example.mytest2023.module.iqiyi.Home1BRVAHAdapter;
import com.example.mytest2023.module.mvp.view.LoginTestActivityMVP;
import com.example.mytest2023.module.mvvm.LoginTestActivityMVVM;
import com.example.mytest2023.module.mvvm.RoomMvvmActivity;
import com.example.mytest2023.module.swiperefresh.SwiperefreshActivity;
import com.example.mytest2023.module.viewpager2.ViewPager2Activity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * Created by 钟文祥 on 2023-04-14.
 * Describer: 首页1 简单RecyclerView+吸顶
 */
public class Home1Fragment extends BaseFragment {


    @BindView(R.id.rv) RecyclerView rv;


    private Home1Adapter adapter1;
    private Home1BRVAHAdapter brvahAdapter;
    private List<Home1Item> list = new ArrayList<>();
    ;

    public static Home1Fragment newInstance() {
        Home1Fragment fragment = new Home1Fragment();
        return fragment;
    }

    @Override
    public Object setLayout() {
        return R.layout.fragment_home1;
    }

    private List<Home1Item> initData(boolean isADD) {

        if (isADD) {
            list.add(new Home1Item<String>("Activity", "生命周期与启动模式", null, Open2Activity.class,
                    "生命周期与启动模式"));
            list.add(new Home1Item<String>("Service", "服务开启与绑定", null, ServiceActivity.class,
                    null));
            list.add(new Home1Item<String>("BroadcastReceiver", "发送广播", null,
                    BroadcastReceiverActivity.class, null));
            list.add(new Home1Item<String>("设计模式", "单例、工厂、建造者、观察者", null, DesignActivity.class,
                    "单例、工厂、建造者、观察者"));
            list.add(new Home1Item<String>("数据结构", "数据接口", null, ShuJuJieGouActivity.class, null));
            list.add(new Home1Item<String>("算法", "插入排序、选择排序、冒泡排序、快速排序", null, SuanFaActivity.class,
                    "插入排序、选择排序、冒泡排序、快速排序"));
            list.add(new Home1Item<String>("MVP", "MVP", null, LoginTestActivityMVP.class, null));
            list.add(new Home1Item<String>("MVVM", "MVVM", null, LoginTestActivityMVVM.class,
                    null));
        }

        list.add(new Home1Item<String>("多线程与异步", "多线程与异步", null, ThreadActivity.class, null));
        list.add(new Home1Item<String>("自定义view", "自定义view", null, CusViewActivity.class, null));
        list.add(new Home1Item<String>("下拉上拉", "BaseRecyclerViewAdapter+swiperefreshlayout", null,
                SwiperefreshActivity.class, null));
        list.add(new Home1Item<String>("Room数据库", "增删改查", null, RoomMvvmActivity.class, null));
        list.add(new Home1Item<String>("viewpager2", "viewpager2", null, ViewPager2Activity.class
                , null));
        list.add(new Home1Item<String>("Banner轮播", "Banner轮播", null, BannerActivity.class, null));
        list.add(new Home1Item<String>("自定义轮播图", "自定义轮播图", null, AutoViewPagerActivity.class,
                null));
        list.add(new Home1Item<String>("kotlin2", "学习kotlin", null, KotlinActivity.class, null));
        list.add(new Home1Item<String>("coroutine", "协程", null, CoroutineActivity.class,
                "协程的使用"));
        return list;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void initView() {
        Log.e("Home1Fragment", "initView: ");
        initData(true);
        RecyclerViewHelper.init(getActivity(), rv, new LinearLayoutManager(getActivity()), true);

        adapter1 = new Home1Adapter(getActivity(), list, new OnItemRecyclerListener() {
            @Override
            public void onClick(int position, Object object) {
                Intent intent = new Intent(getActivity(), ((Home1Item) object).getCls());
                Object data = ((Home1Item) object).getData();
                if (data != null) {
                    if (data instanceof Serializable) {
                        intent.putExtra("data", (Serializable) data);
                    } else if (data instanceof String) {
                        intent.putExtra("data", (String) data);
                    } else if (data instanceof Integer) {
                        intent.putExtra("data", (Integer) data);
                    } else if (data instanceof Float) {
                        intent.putExtra("data", (Float) data);
                    } else if (data instanceof Double) {
                        intent.putExtra("data", (Double) data);
                    } else if (data instanceof Boolean) {
                        intent.putExtra("data", (Boolean) data);
                    }
                }
                getActivity().startActivity(intent);
            }
        });

        rv.setAdapter(adapter1);


    }


    @Override
    public void onFragmentVisibleChange(boolean isVisible) {
        Log.e("Home1Fragment", "onFragmentVisibleChange: ");
    }

    @Override
    public void onFragmentFirstVisible() {
        Log.e("Home1Fragment", "onFragmentFirstVisible: ");
    }


}

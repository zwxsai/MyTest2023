package com.example.mytest2023.module.Home;

import android.content.Context;
import android.graphics.PointF;
import android.util.Log;

import com.example.mytest2023.R;
import com.example.mytest2023.base.BaseFragment;
import com.example.mytest2023.model.home.Demo5Bean;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * Created by 钟文祥 on 2023-04-14.
 * Describer: 首页3 吸顶+tab+直落 （像淘宝）
 */
public class Home3Fragment extends BaseFragment {
    @BindView(R.id.tb_demo5_content) TabLayout tb_demo5_content;
    @BindView(R.id.abl_demo5_content) AppBarLayout abl_demo5_content;
    @BindView(R.id.rv_demo5_content) RecyclerView rv_demo5_content;

    private List<Demo5Bean> data;
    private GridLayoutManager gridLayoutManager;
    private RecyclerView.SmoothScroller smoothScroller;
    private List<Integer> titlePosition;
    //是否正在滚动
    private boolean isScroll;

    public static Home3Fragment newInstance() {
        Home3Fragment fragment = new Home3Fragment();
        return fragment;
    }

    @Override
    public Object setLayout() {
        return R.layout.fragment_home3;
    }

    @Override
    public void initView() {
        initData();
        initTabLayout();
        initRecyclerView();
    }

    @Override
    public void onFragmentVisibleChange(boolean isVisible) {

    }

    @Override
    public void onFragmentFirstVisible() {

    }


    private void initData() {
        data = new ArrayList<>();
        titlePosition = new ArrayList<Integer>();
        Demo5Bean bean = null;
        for (int i = 0; i < 5; i++) {
            bean = new Demo5Bean("标题" + i, Home3Adapter.VIEW_TYPE_TITLE);
            data.add(bean);
            titlePosition.add(i + (i * 10));
            for (int i1 = 0; i1 < 10; i1++) {
                bean = new Demo5Bean(i + "_内容" + i1, Home3Adapter.VIEW_TYPE_MENU);
                data.add(bean);
            }
        }
    }

    private void initTabLayout() {
        for (Demo5Bean datum : data) {
            if (datum.getItemType() == Home3Adapter.VIEW_TYPE_TITLE) {
                tb_demo5_content.addTab(tb_demo5_content.newTab().setText(datum.getName()));
            }
        }
        tb_demo5_content.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (!isScroll) {
                    //收缩折叠区
                    abl_demo5_content.setExpanded(false);
                    int tabPosition = tab.getPosition();
                    int titlePosition = getTitlePosition(tabPosition);
                    smoothScroller.setTargetPosition(titlePosition);
                    gridLayoutManager.startSmoothScroll(smoothScroller);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    private void initRecyclerView() {
        gridLayoutManager = new GridLayoutManager(getContext(), 4);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position < data.size()) {
                    Demo5Bean bean = data.get(position);
                    if (bean.getItemType() == Home3Adapter.VIEW_TYPE_TITLE) {
                        //是标题则占4个item
                        return 4;
                    } else if (bean.getItemType() == Home3Adapter.VIEW_TYPE_MENU) {
                        //是menu则正常占用一个item
                        return 1;
                    } else {
                        return 0;
                    }
                } else {
                    //FooterView 占4个item
                    return 4;
                }
            }
        });
        //计算最后填充FooterView填充高度，全屏高度-状态栏高度-tablayout的高度(这里固定高度50dp)
        // -title标题高度（40）-最后分组高度（3排menu每个70），用于recyclerView的最后一个item FooterView填充高度
        int screenH = getScreenHeight();
        int statusBarH = getStatusBarHeight(getContext());
        int tabH = dip2px(getContext(), 50);
        int titleH = dip2px(getContext(), 40);
        int lastMenusH = dip2px(getContext(), 70) * 3;
        int lastH = screenH - statusBarH - tabH - titleH - lastMenusH;
        if (lastH <= 0) {
            lastH = 0;
        }

        Home3Adapter mAdapter = new Home3Adapter(data, getContext(), lastH);
        rv_demo5_content.setLayoutManager(gridLayoutManager);
        rv_demo5_content.setAdapter(mAdapter);
        //RecyclerView平滑Scroller
        smoothScroller = new LinearSmoothScroller(getContext()) {
            @Override
            protected int getVerticalSnapPreference() {
                return LinearSmoothScroller.SNAP_TO_START;
            }

            @Nullable
            @Override
            public PointF computeScrollVectorForPosition(int targetPosition) {
                return gridLayoutManager.computeScrollVectorForPosition(targetPosition);
            }
        };
        rv_demo5_content.addOnScrollListener(new RecyclerView.OnScrollListener() {
            /**
             * 滚动时回调
             * @param recyclerView RecyclerView
             * @param newState 状态
             * 0:SCROLL_STATE_IDLE:      结束，不处于滚动状态(The RecyclerView is not currently scrolling)
             * 1:SCROLL_STATE_DRAGGING:  手动滑动，被手动拖拽中(The RecyclerView is currently being dragged by outside input
             * such as user touch input)
             * 2:SCROLL_STATE_SETTLING:  自动滑动，撒手后的自动滚动或者代码设置的自动滚动(The RecyclerView is currently animating to a final
             * position while not under outside control)
             * 例：
             * 无反应：手指垂直放置垂直拿开（单击/长按）；手指进行左右方向的滑动；等未产生上下滑动的操作
             * 1,2,0：滑动后手指非垂直拿起，离屏时产生惯性滑动
             * 1,0：滑动后手指静止垂直拿起，未在离屏时产生惯性滑动
             */
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    isScroll = false;
                } else {
                    isScroll = true;
                }
            }

            /**
             * 列表在滑动过程中不断调用（注意事列表滑动不是手指滑动，比如列表已经到底部了还在继续下滑，是不会触发的）
             * @param recyclerView RecyclerView
             * @param dx 水平方向距上一次dx偏移量
             * @param dy 垂直方向距上一次dy偏移量
             */
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int findFirstVisibleItemPosition = gridLayoutManager.findFirstVisibleItemPosition();
                int TabPosition = getTabPosition(findFirstVisibleItemPosition);
                Log.e("aaa",
                        "findFirstVisibleItemPosition: " + findFirstVisibleItemPosition + " , TabPosition:" + TabPosition);
                TabLayout.Tab tabAt = tb_demo5_content.getTabAt(TabPosition);
                if (tabAt != null && !tabAt.isSelected()) {
                    tabAt.select();
                }
            }
        });
    }

    private int getTitlePosition(int tabPosition) {
        //根据tabPosition找出TitlePosition
        return titlePosition.get(tabPosition);
    }

    private int getTabPosition(int menuPosition) {
        return titlePosition.indexOf(menuPosition);
    }

    private int getScreenHeight() {
        return getResources().getDisplayMetrics().heightPixels;
    }

    public int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources()
                .getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}

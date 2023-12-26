package com.example.mytest2023.module.Home;

import android.view.View;
import android.widget.TextView;

import com.example.mytest2023.Api.Service.TestService;
import com.example.mytest2023.Api.Utils.ApiException;
import com.example.mytest2023.Api.Utils.RxSubscriber;
import com.example.mytest2023.R;
import com.example.mytest2023.base.BaseFragment;
import com.example.mytest2023.base.BasePagerAdapter;
import com.example.mytest2023.helper.UIHelper;
import com.example.mytest2023.model.home.ARSceneResponse;
import com.example.mytest2023.widget.other.CustomViewPager;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.scwang.smart.refresh.header.MaterialHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import io.reactivex.disposables.Disposable;

/**
 * Created by 钟文祥 on 2023-04-14.
 * Describer: 吸顶+滑动出现 +tab+CustomViewPager
 */
public class Home4Fragment extends BaseFragment {


    @BindView(R.id.header) MaterialHeader header;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.tab) TabLayout mTabLayout;
    @BindView(R.id.app_bar) AppBarLayout appBar;
    @BindView(R.id.viewPager) CustomViewPager mViewPager;
    @BindView(R.id.refreshLayout) SmartRefreshLayout refreshLayout;
    @BindView(R.id.waibu) TextView waibu;

    private List<String> titles = new ArrayList<>();
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private BasePagerAdapter adapter;

    public static Home4Fragment newInstance() {
        Home4Fragment fragment = new Home4Fragment();
        return fragment;
    }

    @Override
    public Object setLayout() {
        return R.layout.fragment_home4;
    }

    @Override
    public void initView() {
        appBar.addOnOffsetChangedListener(new MyOnOffsetChangedListener());
        adapter = new BasePagerAdapter(getActivity().getSupportFragmentManager(), titles, mFragments);
        mViewPager.setAdapter(adapter);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getARSceneResponse();
            }
        });
        refreshLayout.autoRefresh();
    }

    @Override
    public void onFragmentVisibleChange(boolean isVisible) {

    }

    @Override
    public void onFragmentFirstVisible() {

    }

    private int oldVerticalOffset = -1;

    private class MyOnOffsetChangedListener implements AppBarLayout.OnOffsetChangedListener {
        @Override
        public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
            if (oldVerticalOffset == verticalOffset) return;

            if (verticalOffset <= -UIHelper.dip2px(getActivity(), 60)) {
                waibu.setVisibility(View.VISIBLE);
            } else {
                waibu.setVisibility(View.GONE);
            }
            oldVerticalOffset = verticalOffset;
        }
    }

    private void getARSceneResponse() {
        TestService.testgetARSceneResponse().subscribe(new RxSubscriber<ARSceneResponse>(getActivity(), false) {

            @Override
            public void onApiNext(ARSceneResponse value) {
                titles.clear();
                mFragments.clear();

                for (int i = 0, size = value.getCategoryList().size(); i < size; i++) {
                    titles.add(value.getCategoryList().get(i).getName());
                    mFragments.add(new Fragment());
                }

                adapter.notifyDataSetChanged();
                mTabLayout.setupWithViewPager(mViewPager);
            }

            @Override
            public void onApiError(ApiException ex) {
                refreshLayout.finishRefresh();
            }

            @Override
            public void onAddDisposable(Disposable d) {

            }

            @Override
            public void onComplete() {
                super.onComplete();
                refreshLayout.finishRefresh();
            }


        });
    }
}

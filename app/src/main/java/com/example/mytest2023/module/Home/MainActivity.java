package com.example.mytest2023.module.Home;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.Toast;

import com.chaychan.library.BottomBarItem;
import com.chaychan.library.BottomBarLayout;
import com.example.mytest2023.Api.Service.TestService;
import com.example.mytest2023.Api.Utils.ApiException;
import com.example.mytest2023.Api.Utils.RxSubscriber;
import com.example.mytest2023.R;
import com.example.mytest2023.base.BaseActivity;
import com.example.mytest2023.base.BaseTabAdapter;
import com.example.mytest2023.helper.AppHelper;
import com.example.mytest2023.widget.other.CustomViewPager;
import com.yanzhenjie.permission.Action;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;

/**
 * Created by 钟文祥 on 2023/4/12.
 * Describer：主页  底部tab + viewpager 切换界面
 */
public class MainActivity extends BaseActivity {


    @BindView(R.id.bbl) BottomBarLayout bbl;
    @BindView(R.id.viewPager) CustomViewPager viewPager;
//    @BindView(R.id.fl_content) CustomViewPager fl_content;

    private List<String> titles = new ArrayList<>();
    private List<Fragment> mFragmentList = new ArrayList<>();
    private RotateAnimation mRotateAnimation;
    private FragmentTransaction transaction;


    public static void openActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initPermission();
        initFragment();
        initListener();
    }

    private void initFragment() {

        mFragmentList.add(Home1Fragment.newInstance());
        mFragmentList.add(Home2Fragment.newInstance());
        mFragmentList.add(Home3Fragment.newInstance());
        mFragmentList.add(Home4Fragment.newInstance());
        mFragmentList.add(Home5Fragment.newInstance());

        for (int i = 0; i < mFragmentList.size(); i++) {
            titles.add(i + "");
        }

        viewPager.setAdapter(new BaseTabAdapter(getSupportFragmentManager(), titles, mFragmentList));
        viewPager.setOffscreenPageLimit(2);
        viewPager.setScanScroll(false);
        viewPager.setAnimation(null);
        changeFragment(0);
    }


    private void changeFragment(int currentPosition) {
//        viewPager.setCurrentItem(currentPosition);
        viewPager.setCurrentItem(currentPosition, false);
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.fl_content, mFragmentList.get(currentPosition));
//        transaction.addToBackStack("fr" + currentPosition);
//        transaction.commit();
    }

    private void initListener() {
        bbl.setOnItemSelectedListener(new BottomBarLayout.OnItemSelectedListener() {
            @Override
            public void onItemSelected(BottomBarItem bottomBarItem, int previousPosition, int currentPosition) {
                changeFragment(currentPosition);

                if (currentPosition == 0 && previousPosition == currentPosition) {
                    //首页的动画还在进行中
                    if (mRotateAnimation != null && !mRotateAnimation.hasEnded()) {
                        //如果当前动画正在执行
                        return;
                    }

                    bottomBarItem.setSelectedIcon(R.drawable.activity_main_tab_loading);//更换成加载图标 setResId

                    //播放旋转动画
                    if (mRotateAnimation == null) {
                        mRotateAnimation = new RotateAnimation(0, 360,
                                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                                0.5f);
                        mRotateAnimation.setDuration(800);
                        mRotateAnimation.setRepeatCount(-1);
                    }
                    ImageView bottomImageView = bottomBarItem.getImageView();
                    bottomImageView.setAnimation(mRotateAnimation);
                    bottomImageView.startAnimation(mRotateAnimation);//播放旋转动画

                    //模拟数据刷新完毕
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            boolean tabNotChanged = bbl.getCurrentItem() == currentPosition; //是否还停留在当前页签
                            bottomBarItem.setSelectedIcon(R.drawable.activity_main_tab_home_selected);//更换成首页原来选中图标
                            cancelTabLoading(bottomBarItem);
                        }
                    }, 3000);
                    return;

                }

                //如果点击了其他条目
                BottomBarItem bottomItem = bbl.getBottomItem(0);
                bottomItem.setSelectedIcon(R.drawable.activity_main_tab_home_selected);//更换为原来的图标
                cancelTabLoading(bottomItem);//停止旋转动画
            }
        });
    }

    /**
     * 停止首页页签的旋转动画
     */
    private void cancelTabLoading(BottomBarItem bottomItem) {
        Animation animation = bottomItem.getImageView().getAnimation();
        if (animation != null) {
            animation.cancel();
        }
    }

    private void initPermission() {
        AppHelper.requestPermission(MainActivity.this, new Action() {
                    @Override
                    public void onAction(List<String> permissions) {

                    }
                },
                Manifest.permission.INTERNET,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_NETWORK_STATE);
    }

    private void testApi() {
        TestService.testApi().subscribe(new RxSubscriber<String>(this, true) {
            @Override
            public void onAddDisposable(Disposable d) {
                int sdf = 3;
            }

            @Override
            public void onApiNext(String value) {
                String sd = value;
                int sdf = 2;
            }

            @Override
            public void onApiError(ApiException ex) {
                int sdf = 3;
            }
        });
    }

    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
                return true;
            } else {
                AppHelper.exitProgram(this);
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
package com.example.mytest2023.module.Foundation;

import android.graphics.drawable.AnimatedImageDrawable;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.eric.android.view.ExpandableTextView;
import com.example.mytest2023.R;
import com.example.mytest2023.base.BaseActivity;
import com.example.mytest2023.widget.other.CusLinearLayout;
import com.example.mytest2023.widget.other.CusText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 钟文祥 on 2023-05-26.
 * Describer: 自定义view https://blog.csdn.net/shuo277/article/details/126234914
 */
public class CusViewActivity extends BaseActivity {

    @BindView(R.id.CusLinearLayout) CusLinearLayout cusLinearLayout;
    @BindView(R.id.CusText) CusText cusText;
    @BindView(R.id.image1) ImageView image1;
    @BindView(R.id.obj1) TextView obj1;
    @BindView(R.id.expand_tv1) ExpandableTextView expand_text_view1;
    @BindView(R.id.expand_tv2) ExpandableTextView expand_text_view2;
    @BindView(R.id.expand_tv3) ExpandableTextView expand_text_view3;
    @BindView(R.id.expand_tv4) ExpandableTextView expand_text_view4;

    private AnimationDrawable animationDrawable;

    String txt = "<p>接下来要创建一个登录界面，新建 LoginActivity（在 Android Studio 中，建议创建空的活动），编辑 activity_login.xml：</p>\n" +
            "<pre><code>&lt;LinearLayout xmlns:android=\"http://schemas.android.com/apk/res/android\"\n" +
            "    android:layout_width=\"match_parent\"\n" +
            "    android:layout_height=\"match_parent\"\n" +
            "    android:gravity=\"center_horizontal\"\n" +
            "    android:orientation=\"vertical\"&gt;\n" +
            "\n" +
            "    &lt;LinearLayout\n" +
            "        android:layout_width=\"match_parent\"\n" +
            "        android:layout_height=\"60dp\"\n" +
            "        android:orientation=\"horizontal\"&gt;\n" +
            "\n" +
            "        &lt;TextView\n" +
            "            android:layout_width=\"90dp\"\n" +
            "            android:layout_height=\"wrap_content\"\n" +
            "            android:layout_gravity=\"center_vertical\"\n" +
            "            android:text=\"账号：\"\n" +
            "            android:textSize=\"18sp\" /&gt;\n" +
            "\n" +
            "        &lt;EditText\n" +
            "            android:id=\"@+id/account\"\n" +
            "            android:layout_width=\"0dp\"\n" +
            "            android:layout_height=\"wrap_content\"\n" +
            "            android:layout_gravity=\"center_vertical\"\n" +
            "            android:layout_weight=\"1\" /&gt;\n" +
            "    &lt;/LinearLayout&gt;\n" +
            "\n" +
            "    &lt;LinearLayout\n" +
            "        android:layout_width=\"match_parent\"\n" +
            "        android:layout_height=\"60dp\"\n" +
            "        android:orientation=\"horizontal\"&gt;\n" +
            "\n" +
            "        &lt;TextView\n" +
            "            android:layout_width=\"90dp\"\n" +
            "            android:layout_height=\"wrap_content\"\n" +
            "            android:layout_gravity=\"center_vertical\"\n" +
            "            android:text=\"密码：\"\n" +
            "            android:textSize=\"18sp\" /&gt;\n" +
            "\n" +
            "        &lt;EditText\n" +
            "            android:id=\"@+id/password\"\n" +
            "            android:layout_width=\"0dp\"\n" +
            "            android:layout_height=\"wrap_content\"\n" +
            "            android:layout_gravity=\"center_vertical\"\n" +
            "            android:layout_weight=\"1\"\n" +
            "            android:inputType=\"textPassword\" /&gt;\n" +
            "    &lt;/LinearLayout&gt;\n" +
            "\n" +
            "    &lt;Button\n" +
            "        android:id=\"@+id/login\"\n" +
            "        android:layout_width=\"match_parent\"\n" +
            "        android:layout_height=\"60dp\"\n" +
            "        android:text=\"登录\" /&gt;\n" +
            "&lt;/LinearLayout&gt;\n" +
            "\n" +
            "</code></pre>\n" +
            "<p>我们使用 LinearLayout 编写了一个登陆布局，使用纵向排列，从上到下分别是账号、密码和登陆按钮。</p>\n" +
            "<p>接着修改 LoginActivity 中的代码：</p>";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cus_view);
        ButterKnife.bind(this);
        frameDonghua();
        bujianDonghua();

//        expand_text_view1.setText(Html.fromHtml(
//                "拉萨抗衰老的激发了快速的房间辣可视对讲福利卡圣诞节福利卡束带结发莱克斯顿解放啦可视对讲弗兰克绿卡技术的福利卡就是代理费卡技术的福利科技阿萨德冷风机阿萨德李逵负荆阿里时代峰峻阿里斯柯达解放啦可视对讲菲拉斯大姐夫卢卡斯大姐夫拉三等奖法拉克圣诞节法拉克三季度福利卡束带结发辣椒块豆腐卢卡斯对伐啦束带结发")
//        );
        expand_text_view1.setText(Html.fromHtml(txt));
        expand_text_view2.setText(Html.fromHtml(txt));
        expand_text_view3.setText(Html.fromHtml(txt));
        expand_text_view4.setText(Html.fromHtml(txt));
    }

    //帧动画
    private void frameDonghua() {
        image1.setImageResource(R.drawable.cus_frame_donghua1);
        animationDrawable = (AnimationDrawable) image1.getDrawable();
        animationDrawable.start();
    }

    //补间动画
    private void bujianDonghua() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.cus_bujian_donghua1);
        obj1.setAnimation(animation);
    }


    @OnClick({R.id.CusLinearLayout, R.id.CusText, R.id.image1})
    public void onBindClick(View view) {
        switch (view.getId()) {
            case R.id.CusLinearLayout:
                break;
            case R.id.CusText:
                break;
            case R.id.image1:
                //停止动画
                image1.clearAnimation();
                if (animationDrawable != null) {
                    animationDrawable.stop();
                }
                break;
        }
    }
}
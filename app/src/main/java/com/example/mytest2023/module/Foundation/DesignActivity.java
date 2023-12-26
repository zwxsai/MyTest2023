package com.example.mytest2023.module.Foundation;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.mytest2023.R;
import com.example.mytest2023.helper.OnItemRecyclerListener;
import com.example.mytest2023.model.design.danli.DoubleCheckSingle;
import com.example.mytest2023.model.design.gongchang.SenderFactory;
import com.example.mytest2023.model.design.gongchang.Sender;
import com.example.mytest2023.model.design.guancha.Weather;
import com.example.mytest2023.model.design.guancha.WeatherObservable;
import com.example.mytest2023.model.design.guancha.WeatherObserver;
import com.example.mytest2023.model.design.jianzao.UserItem;

/**
 * Created by 钟文祥 on 2023/4/21.
 * Describer：设计模式
 * https://blog.csdn.net/qq_14931305/article/details/81296329
 */
public class DesignActivity extends FoundationActivity {

    @Override
    public void initView() {
        txt1.setText("设计模式");
        txt2.setText(data);

        btn1.setVisibility(View.VISIBLE);
        btn1.setText("注册");
        btn1_2.setVisibility(View.VISIBLE);
        btn1_2.setText("注销");

        btn2.setVisibility(View.VISIBLE);
        btn2.setText("晴天");
        btn2.setVisibility(View.VISIBLE);
        btn2_2.setText("阴天");
        btn3.setVisibility(View.VISIBLE);
        btn3.setText("多云");

        listener = new OnItemRecyclerListener() {
            @Override
            public void onClick(int position, Object object) {
                switch (position) {
                    case R.id.btn1:

                        break;
                }
            }
        };
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //单例模式  之  双重检验锁
        DoubleCheckSingle single = DoubleCheckSingle.getInstance();

        //工厂模式
        Sender sender = SenderFactory.getASender();
        sender.send();

        //建造者模式
        UserItem item = new UserItem.Builder().id(0).name("abc").age(10).build();

        UserItem.Builder builder = new UserItem.Builder();
        UserItem item2 = builder.id(0).name("abc").age(10).build();

        //观察者模式
        testGuancha();
    }

    private void testGuancha() {
        //观察者模式
        //被观察者
        WeatherObservable<Weather> observable = new WeatherObservable<>();
        //观察者1
        WeatherObserver<Weather> observer1 = new WeatherObserver<Weather>() {

            @Override
            public void update(WeatherObservable<Weather> observable, Weather data) {
                Log.e("观察", "observer1：update: " + data.getName());
            }
        };
        //观察者2
        WeatherObserver<Weather> observer2 = new WeatherObserver<Weather>() {
            @Override
            public void update(WeatherObservable<Weather> observable, Weather data) {
                Log.e("观察", "observer2：update: " + data.getName());
            }
        };
        observable.register(observer1);
        observable.register(observer2);

        Weather weather1 = new Weather(0, "晴天");
        observable.guangBoTongZhi(weather1);

        Weather weather2 = new Weather(2, "阴天");
        observable.guangBoTongZhi(weather2);

        observable.unRegister(observer2);
        Weather weather3 = new Weather(3, "多云");
        observable.guangBoTongZhi(weather3);
    }
}
package com.example.mytest2023.model.design.guancha;

/**
 * Created by 钟文祥 on 2023-04-21.
 * Describer:天气观察者
 * 观察者接收到消息后，即进行update更新操作，对接收到的信息进行处理。
 */
public interface WeatherObserver<T> {
    public void update(WeatherObservable<T> observable, T data);
}

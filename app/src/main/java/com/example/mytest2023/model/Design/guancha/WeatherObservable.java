package com.example.mytest2023.model.design.guancha;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 钟文祥 on 2023-04-21.
 * Describer: 天气被观察者
 * 它必须能偶动态地增加、取消观察者，管理观察者并通知观察者。
 */
public class WeatherObservable<T> {
    private List<WeatherObserver<T>> mObservers = new ArrayList<>();

    public void register(WeatherObserver<T> observer) {
        if (observer == null) return;
        synchronized (this) {
            if (!mObservers.contains(observer)) {
                mObservers.add(observer);
            }
        }
    }

    public synchronized void unRegister(WeatherObserver<T> observer) {
        mObservers.remove(observer);
    }

    public void guangBoTongZhi(T data) {
        for (WeatherObserver<T> observer : mObservers) {
            observer.update(this, data);
        }
    }

}

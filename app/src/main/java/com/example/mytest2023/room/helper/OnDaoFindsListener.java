package com.example.mytest2023.room.helper;

/**
 * Created by 钟文祥 on 2023/6/17.
 * Describer: dao find的执行回调
 */
public interface OnDaoFindsListener<T> {
    /** 主线程回调 */
    void onNext(T t);
}

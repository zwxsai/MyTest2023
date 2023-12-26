package com.example.mytest2023.helper;

/**
 * Created by 钟文祥 on 2019/4/16.
 * Describer: EventBus 基类数据
 */
public class BaseEventBus<T> {
    public String code;
    public T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public BaseEventBus() {
    }

    public BaseEventBus(String code, T data) {
        this.code = code;
        this.data = data;
    }

    public static final String SumI = "SumI";
    public static final String MyReceiver = "MyReceiver";
}

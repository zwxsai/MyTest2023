package com.example.mytest2023.Api.Utils;

/**
 * Created by zwx on 2018/6/5.
 * Describer: 标准数据
 */
public class BaseResponse<T> {
    public int code;
    public String msg;
    public T data;

    public BaseResponse() {
    }

    public BaseResponse(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


    public static final int RESULT_SUCCESS = 0;

    public boolean isSuccess() {
        return RESULT_SUCCESS == code;
    }
}

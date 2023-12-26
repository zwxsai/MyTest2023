package com.example.mytest2023.Api.Utils;

/**
 * Created by zwx on 2018/6/5.
 * Describer: API错误信息
 */
public class ApiException extends Exception {
    public int code;
    public String message;

    public ApiException(Throwable throwable, int code) {
        super(throwable);
        this.code = code;
        this.message = "";
    }

    public ApiException(Throwable throwable, int code, String message) {
        super(throwable);
        this.code = code;
        this.message = message;

    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}



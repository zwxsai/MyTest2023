package com.example.mytest2023.Api.ResponseModel;


import com.example.mytest2023.helper.TimeHelper;

import java.util.Calendar;

/**
 * Created by 钟文祥 on 2023/6/16.
 * Describer:注册响应类
 */
public class RegisterResponse {

    private String token;

    //有效期，单位分钟, 默认有效期7天
    private int expiredIn;

    private String startTime;
    private String endTime;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getExpiredIn() {
        return expiredIn;
    }

    public void setExpiredIn(int expiredIn) {
        this.expiredIn = expiredIn;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public RegisterResponse() {
    }

    public RegisterResponse(String token, int expiredIn) {
        this.token = token;
        this.expiredIn = expiredIn;
        this.startTime = TimeHelper.currentTime(TimeHelper.dateFormat, false);
        this.endTime = TimeHelper.addTime(Calendar.MINUTE, expiredIn, TimeHelper.dateFormat);
    }

    public RegisterResponse(String token, int expiredIn, String startTime, String endTime) {
        this.token = token;
        this.expiredIn = expiredIn;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}

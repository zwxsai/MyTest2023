package com.example.mytest2023.Api.RequestModel;


import com.example.mytest2023.helper.TimeHelper;

/**
 * Created by 钟文祥 on 2023/6/15.
 * Describer:注册请求类
 */
public class RegisterRequest {
    //硬件型号uuid，由爱奇艺分配
    private String deviceUUID = "20190424192805636OhmTzeQqV101327";

    //VIN（车架号），该参数会影响部分个性化接口返回结果，建议同一台车机该参数固定
    private String sn = "ABC";
    //时间戳
    private int timestamp = (int) TimeHelper.currentTime2Unix();

    public RegisterRequest(String deviceUUID, String sn, int timestamp) {
        this.deviceUUID = deviceUUID;
        this.sn = sn;
        this.timestamp = timestamp;
    }

    public RegisterRequest() {
    }

    public String getDeviceUUID() {
        return deviceUUID;
    }

    public void setDeviceUUID(String deviceUUID) {
        this.deviceUUID = deviceUUID;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }
}

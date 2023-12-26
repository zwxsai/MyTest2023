package com.example.mytest2023.model.overall;

/**
 * Created by 钟文祥 on 2023-04-10.
 * Describer:
 */
public class UserInfo {
    private String phone;
    private String phoneDes;

    public UserInfo() {
    }

    public UserInfo(String phone, String phoneDes) {
        this.phone = phone;
        this.phoneDes = phoneDes;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoneDes() {
        return phoneDes;
    }

    public void setPhoneDes(String phoneDes) {
        this.phoneDes = phoneDes;
    }
}

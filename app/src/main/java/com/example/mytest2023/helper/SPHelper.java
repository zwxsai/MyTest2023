package com.example.mytest2023.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;

import com.example.mytest2023.Api.Utils.WebApiConfig;
import com.example.mytest2023.base.MyApp;
import com.example.mytest2023.model.overall.UserInfo;

/**
 * Created by 钟文祥 on 2019/7/9.
 * Describer:SharedPreferences 工具类
 */
public class SPHelper {
    public static final String sp_name = "spMyTest2023";//SharedPreferences名字就取spMyTest2023

    //------------全局相关 start-------------
    private static final String sp_user_phone = "sp_user_phone";//手机号
    private static final String sp_user_phone_des = "sp_user_phone_des";//手机号加密

    public static boolean currentVersionIsNew(Context context) {
        SharedPreferences preferences = MyApp.getContextObject().getSharedPreferences
                (sp_name, Context.MODE_PRIVATE);

        int currentVersionCode = 0;
        try {
            currentVersionCode = context.getPackageManager().getPackageInfo(context.getPackageName(), 0)
                    .versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        boolean isNew = preferences.getBoolean(currentVersionCode + "", true);
        if (isNew) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean(currentVersionCode + "", false);
            editor.commit();
        }
        return isNew;
    }

    public static void saveUserInfo(String phone) {
        SharedPreferences preferences = MyApp.getContextObject().getSharedPreferences
                (sp_name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(sp_user_phone, phone);
        String phoneDes = "";
        try {
            phoneDes = EncryptHelper.desEncrypt(phone, WebApiConfig.des_key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        editor.putString(sp_user_phone_des, phoneDes);
        editor.commit();
    }

    public static UserInfo getUserInfo() {
        SharedPreferences preferences = MyApp.getContextObject().getSharedPreferences
                (sp_name, Context.MODE_PRIVATE);
        String phone = preferences.getString(sp_user_phone, "");
        String phoneDes = preferences.getString(sp_user_phone_des, "");
        return new UserInfo(phone, phoneDes);
    }

    public static void clearUserInfo() {
        SharedPreferences preferences = MyApp.getContextObject().getSharedPreferences
                (sp_name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(sp_user_phone, "");
        editor.putString(sp_user_phone_des, "");
        editor.commit();
    }
    //------------全局相关 end-------------


}

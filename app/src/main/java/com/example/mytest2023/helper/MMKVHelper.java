package com.example.mytest2023.helper;

import com.example.mytest2023.Api.ResponseModel.RegisterResponse;
import com.tencent.mmkv.MMKV;

/**
 * Created by 钟文祥 on 2023/6/13.
 * Describer: MMKV 辅助类
 * https://www.jianshu.com/p/c872c1e01b57
 * https://blog.csdn.net/qq_42759120/article/details/123646399
 */
public class MMKVHelper {

    //------------全局相关 start-------------
    private static final String kv_app_token = "kv_app_token";//token
    private static final String kv_app_token_expiredIn = "kv_app_token_expiredIn";//token 有效期，单位分钟
    private static final String kv_app_token_start_time = "kv_app_token_start_time";//token开始时间
    private static final String kv_app_token_end_time = "kv_app_token_end_time";//token结束时间

    public static void savaToken(RegisterResponse res) {
        MMKV kv = MMKV.defaultMMKV();
        kv.putString(kv_app_token, res.getToken());
        kv.putInt(kv_app_token_expiredIn, res.getExpiredIn());
        kv.putString(kv_app_token_start_time, res.getStartTime());
        kv.putString(kv_app_token_end_time, res.getEndTime());
    }

    public static RegisterResponse getTokenInfo() {
        MMKV kv = MMKV.defaultMMKV();
        return new RegisterResponse(kv.getString(kv_app_token, ""),
                kv.getInt(kv_app_token_expiredIn, 0),
                kv.getString(kv_app_token_start_time, ""),
                kv.getString(kv_app_token_end_time, ""));


//        //删除数据，移除指定的key
//        kv.remove(sp_user_phone);
//        //kv.allKeys()方法获取数组返回所有的key，Arrays.toString方法把数组内容打印出来
//        String s1 = Arrays.toString(kv.allKeys());
//        //查询是否有这个键值对
//        boolean hasBool = kv.containsKey("bool");
//        //清空所有
//        kv.clear();
    }

}

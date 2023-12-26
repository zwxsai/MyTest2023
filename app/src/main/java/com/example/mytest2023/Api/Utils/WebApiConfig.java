package com.example.mytest2023.Api.Utils;

/**
 * Created by 钟文祥 on 2018/7/24.
 * Describer: 服务地址
 */
public class WebApiConfig {

    public static final String des_key = "myNetPre";

    /** 云雀，无线宝，压力测试 服务 */
    public static final String baseUrlRelease = "http://192.168.0.105:8080/";//正式
    public static final String baseUrlDebug = "http://192.168.0.105:8080/";//正式

//    public static final String baseUrlDebug = "http://192.168.8.174:8081/";//测试
//    public static final String baseUrlRelease = "http://192.168.8.174:8081/";//测试


    //自定义code  4位数
    public static final int success = 200; //新项目改为2000
    /** accessToken失效 4030 */
    public static final int access_token_fail = 403; //新项目改为4030
    /** refreshToken失效 4031 */
    public static final int refresh_token_fail = 44444;//新项目改为4031

    /** 分页每页数量 */
    public static final int pageSize = 20;
}

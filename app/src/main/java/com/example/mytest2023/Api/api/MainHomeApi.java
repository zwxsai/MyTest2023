package com.example.mytest2023.Api.api;

import com.example.mytest2023.Api.RequestModel.RegisterRequest;
import com.example.mytest2023.Api.ResponseModel.HotwordsResponse;
import com.example.mytest2023.Api.ResponseModel.RegisterResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by 钟文祥 on 2023/6/15.
 * Describer:主页api
 */
public interface MainHomeApi {

    /** 设备注册 立马获取的 要用Call */
    @POST("api/register")
    Call<RegisterResponse> register(@Body RegisterRequest request);

    /** 获取搜索推荐词 */
    @GET("api/hotwords")
    Observable<List<HotwordsResponse>> getHotwords(@Query("count") int count);


    /** 获取压力测试任务列表 */
//    @Headers({"method:GetNetPressureTaskList"})
//    @POST("NetPressureHandler.ashx")
//    Observable<BaseResponse<List<NetPressureTask>>> GetNetPressureTaskList();


}

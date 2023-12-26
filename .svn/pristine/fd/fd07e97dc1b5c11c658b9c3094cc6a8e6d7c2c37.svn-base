package com.example.mytest2023.Api.api;

import com.example.mytest2023.Api.Utils.BaseResponse;
import com.example.mytest2023.Api.Utils.BaseResource;
import com.example.mytest2023.model.home.ARSceneResponse;

import io.reactivex.Observable;
import retrofit2.http.POST;

/**
 * Created by 钟文祥 on 2023-04-11.
 * Describer:
 */
public interface TestApi {

    /** 测试 */
    @POST("Interface/testApi")
    Observable<BaseResponse<String>> testApi();

    /** 测试 */
    @POST("Interface/testgetARSceneResponse")
    Observable<BaseResponse<ARSceneResponse>> testgetARSceneResponse();

    @POST("Interface/testgetARSceneResponse")
    Observable<BaseResource<ARSceneResponse>> testgetARSceneResponse2();
}

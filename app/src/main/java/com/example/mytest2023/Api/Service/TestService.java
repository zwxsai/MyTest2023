package com.example.mytest2023.Api.Service;

import com.example.mytest2023.Api.Utils.BaseResponse;
import com.example.mytest2023.Api.Utils.BaseService;
import com.example.mytest2023.Api.Utils.ServiceManager;
import com.example.mytest2023.Api.Utils.BaseResource;
import com.example.mytest2023.Api.api.TestApi;
import com.example.mytest2023.model.home.ARSceneResponse;

import io.reactivex.Observable;

/**
 * Created by 钟文祥 on 2023-04-11.
 * Describer:
 */
public class TestService extends BaseService {

    /** 测试接口 验证AccessToken  和  refreshToken */
    public static Observable<String> testApi() {
        TestApi api = ServiceManager.getClientService(TestApi.class);
        Observable<BaseResponse<String>> observable = api.testApi();
        return initObservableByBaseResponse(observable);
    }


    public static Observable<ARSceneResponse> testgetARSceneResponse() {
        TestApi api = ServiceManager.getClientService(TestApi.class);
        Observable<BaseResponse<ARSceneResponse>> observable = api.testgetARSceneResponse();
        return initObservableByBaseResponse(observable);
    }

    public static Observable<ARSceneResponse> testgetARSceneResponse2() {
        TestApi api = ServiceManager.getClientService(TestApi.class);
        Observable<BaseResource<ARSceneResponse>> observable = api.testgetARSceneResponse2();
        return initObservableByBaseResource(observable);
    }
}

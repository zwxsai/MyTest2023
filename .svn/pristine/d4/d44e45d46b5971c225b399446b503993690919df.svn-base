package com.example.mytest2023.Api.Service;

import com.example.mytest2023.Api.RequestModel.RegisterRequest;
import com.example.mytest2023.Api.ResponseModel.HotwordsResponse;
import com.example.mytest2023.Api.ResponseModel.RegisterResponse;
import com.example.mytest2023.Api.Utils.BaseService;
import com.example.mytest2023.Api.Utils.ServiceManager;
import com.example.mytest2023.Api.api.MainHomeApi;
import com.example.mytest2023.helper.MMKVHelper;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;

/**
 * Created by 钟文祥 on 2023/6/16.
 * Describer:主页服务
 */
public class HomeService extends BaseService {
    /** 设备注册 立马获取的 */
    public static String register() {
        MainHomeApi api = ServiceManager.getClientService(MainHomeApi.class);
        RegisterRequest request = new RegisterRequest();
        Call<RegisterResponse> call = api.register(request);

        try {
            RegisterResponse response = call.execute().body();
            MMKVHelper.savaToken(response);
            String newToken = response.getToken();
            return newToken;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }


    /** 获取搜索推荐词 */
    public static Observable<List<HotwordsResponse>> getHotwords(int count) {
        MainHomeApi api = ServiceManager.getClientService(MainHomeApi.class);
        Observable<List<HotwordsResponse>> observable = api.getHotwords(count);
        return initObservable(observable);
    }


}

package com.example.mytest2023.module.mvvm;

import android.app.Application;

import com.example.mytest2023.Api.Service.TestService;
import com.example.mytest2023.Api.Utils.BaseResponse;
import com.example.mytest2023.Api.Utils.BaseResource;
import com.example.mytest2023.model.home.ARSceneResponse;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

/**
 * Created by 钟文祥 on 2023/6/15.
 * https://juejin.cn/post/6844903968884146184
 */
public class UserItemViewModel extends BaseViewModel {

    public UserItemViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }


    public MutableLiveData<ARSceneResponse> testgetARSceneResponse0() {
        final MutableLiveData<ARSceneResponse> liveData = new MutableLiveData<>();
        return subscribeGoData(TestService.testgetARSceneResponse(), liveData, false);
    }

    public MutableLiveData<BaseResponse<ARSceneResponse>> testgetARSceneResponse1() {
        //因为用到LiveData，我觉得都不需要切换到主线程了。LiveData可以帮我们做
        //调用接口，返回我们的MutableLiveData<List<BannerBean>>
        final MutableLiveData<BaseResponse<ARSceneResponse>> liveData = new MutableLiveData<>();
        return subscribeGoBaseResponse(TestService.testgetARSceneResponse(), liveData, false);
    }


    public MutableLiveData<BaseResource<ARSceneResponse>> testgetARSceneResponse2() {
        final MutableLiveData<BaseResource<ARSceneResponse>> liveData = new MutableLiveData<>();
        return subscribeGoBaseResource(TestService.testgetARSceneResponse2(), liveData, false);
    }
}

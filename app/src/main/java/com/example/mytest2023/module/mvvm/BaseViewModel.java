package com.example.mytest2023.module.mvvm;

import android.app.Application;

import com.example.mytest2023.Api.Utils.ApiException;
import com.example.mytest2023.Api.Utils.BaseResponse;
import com.example.mytest2023.Api.Utils.RxSubscriber;
import com.example.mytest2023.Api.Utils.BaseResource;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * Created by 钟文祥 on 2023/6/15.
 * Describer:
 * T 为model
 */
//继承AndroidViewModel，是因为里面要用context时候直接可以getApplication()
public abstract class BaseViewModel extends AndroidViewModel {

    public BaseViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    protected void onCleared() {
        super.onCleared();

    }



    public <T> MutableLiveData<T> subscribeGoData(Observable<T> observable,
                                                  MutableLiveData<T> liveData,
                                                  boolean isShowDialog) {
        observable.subscribe(new RxSubscriber<T>(getApplication(), isShowDialog) {
            @Override
            public void onAddDisposable(Disposable d) {

            }

            @Override
            public void onApiNext(T value) {
                liveData.postValue(value);
            }

            @Override
            public void onApiError(ApiException ex) {

            }
        });
        return liveData;
    }

    public <T> MutableLiveData<BaseResponse<T>> subscribeGoBaseResponse(Observable<T> observable,
                                                                        MutableLiveData<BaseResponse<T>> liveData,
                                                                        boolean isShowDialog) {
        observable.subscribe(new RxSubscriber<T>(getApplication(), isShowDialog) {
            @Override
            public void onAddDisposable(Disposable d) {

            }

            @Override
            public void onApiNext(T value) {
                liveData.postValue(new BaseResponse<T>(200, "", value));
            }

            @Override
            public void onApiError(ApiException ex) {

            }
        });
        return liveData;
    }

    public <T> MutableLiveData<BaseResource<T>> subscribeGoBaseResource(Observable<T> observable,
                                                                        MutableLiveData<BaseResource<T>> liveData,
                                                                        boolean isShowDialog) {
        observable.subscribe(new RxSubscriber<T>(getApplication(), isShowDialog) {
            @Override
            public void onAddDisposable(Disposable d) {
                liveData.postValue(new BaseResource<T>(BaseResource.LOADING, ""));
            }

            @Override
            public void onApiNext(T value) {
                liveData.postValue(new BaseResource<T>(BaseResource.SUCCESS, value));
            }

            @Override
            public void onApiError(ApiException ex) {
                liveData.postValue(new BaseResource<T>(BaseResource.ERROR, ex));
            }
        });
        return liveData;
    }


}

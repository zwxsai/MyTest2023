package com.example.mytest2023.Api.Utils;


import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by zwx on 2018/6/5.
 * Describer: 服务请求类基类
 */
public class BaseService {

    /***
     * 添加拦截器  带BaseResponse
     * @param observable
     * @param <T>
     * @return
     */
    protected static <T> Observable<T> initObservableByBaseResponse(Observable<BaseResponse<T>>
                                                                            observable) {
        return observable.switchMap(new Function<BaseResponse<T>, ObservableSource<T>>() {
            @Override
            public ObservableSource<T> apply(BaseResponse<T> baseResponse) throws Exception {
                //对返回码进行判断，如果http状态码不是200，则证明服务器端返回错误信息了，便根据跟服务器约定好的错误码去解析异常
                Log.e("BaseService.class", "自定义code：" + baseResponse.code);
                if (baseResponse.code == WebApiConfig.success) {
                    return Observable.just(baseResponse.data);//服务器请求数据成功，返回里面的数据实体
                } else if (baseResponse.code == WebApiConfig.access_token_fail) {
                    return Observable.just(baseResponse.data);
                } else if (baseResponse.code == WebApiConfig.refresh_token_fail) {
                    throw new ServerException(baseResponse.code, baseResponse.msg);
                } else {
                    //如果服务器端有错误信息返回，那么抛出异常，让下面的方法去捕获异常做统一处理
                    throw new ServerException(baseResponse.code, baseResponse.msg);
                }
            }
        }).onErrorResumeNext(new Function<Throwable, ObservableSource<T>>() {
            @Override
            public ObservableSource<T> apply(Throwable throwable) throws Exception {
                //ExceptionEngine为处理异常的驱动器
                return Observable.error(ExceptionEngine.handleException(throwable));
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    protected static <T> Observable<T> initObservableByBaseResource(Observable<BaseResource<T>>
                                                                            observable) {
        return observable.switchMap(new Function<BaseResource<T>, ObservableSource<T>>() {
            @Override
            public ObservableSource<T> apply(BaseResource<T> baseResponse) throws Exception {
                //对返回码进行判断，如果http状态码不是200，则证明服务器端返回错误信息了，便根据跟服务器约定好的错误码去解析异常
                Log.e("BaseService.class", "自定义code：" + baseResponse.code);
                if (baseResponse.code == WebApiConfig.success) {
                    return Observable.just(baseResponse.data);//服务器请求数据成功，返回里面的数据实体
                } else if (baseResponse.code == WebApiConfig.access_token_fail) {
                    return Observable.just(baseResponse.data);
                } else if (baseResponse.code == WebApiConfig.refresh_token_fail) {
                    throw new ServerException(baseResponse.code, baseResponse.msg);
                } else {
                    //如果服务器端有错误信息返回，那么抛出异常，让下面的方法去捕获异常做统一处理
                    throw new ServerException(baseResponse.code, baseResponse.msg);
                }
            }
        }).onErrorResumeNext(new Function<Throwable, ObservableSource<T>>() {
            @Override
            public ObservableSource<T> apply(Throwable throwable) throws Exception {
                //ExceptionEngine为处理异常的驱动器
                return Observable.error(ExceptionEngine.handleException(throwable));
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }


    /***
     * 添加拦截器 不带BaseResponse
     * @param observable
     * @param <T>
     * @return
     */
    protected static <T> Observable<T> initObservable(Observable<T> observable) {
        return observable.switchMap(new Function<T, ObservableSource<T>>() {
            @Override
            public ObservableSource<T> apply(T t) throws Exception {
                return Observable.just(t);
            }
        }).onErrorResumeNext(new Function<Throwable, ObservableSource<T>>() {
            @Override
            public ObservableSource<T> apply(Throwable throwable) throws Exception {
                //ExceptionEngine为处理异常的驱动器
                return Observable.error(ExceptionEngine.handleException(throwable));
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }


}

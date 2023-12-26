package com.example.mytest2023.Api.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.example.mytest2023.helper.DialogHelper;
import com.example.mytest2023.helper.SPHelper;
import com.example.mytest2023.helper.ToastUtil;
import com.example.mytest2023.module.Home.MainActivity;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by zwx on 2018/6/5.
 * Describer: 自定义响应请求回调类
 */
public abstract class RxSubscriber<T> implements Observer<T> {

    protected Context context;
    private boolean isShowDialog;
    private String diaLogMsg;
    private boolean cancelable;

    public RxSubscriber(Context context) {
        this.context = context;
        this.isShowDialog = false;
        this.diaLogMsg = "";
        this.cancelable = false;
    }

    public RxSubscriber(Context context, boolean isShowDialog) {
        this.context = context;
        this.isShowDialog = isShowDialog;
        this.diaLogMsg = "";
        this.cancelable = false;
    }

    public RxSubscriber(Context context, boolean isShowDialog, String diaLogMsg, boolean
            cancelable) {
        this.context = context;
        this.isShowDialog = isShowDialog;
        this.diaLogMsg = diaLogMsg;
        this.cancelable = cancelable;
    }

    @Override
    public void onSubscribe(Disposable d) {
        onAddDisposable(d);
        if (this.isShowDialog) {
            DialogHelper.showNetWebDialog(context, this.diaLogMsg, this.cancelable);
        }
    }


    @Override
    public void onError(Throwable error) {
        if (this.isShowDialog)
            DialogHelper.stopProgressDlg();
        if (error instanceof ApiException) {
            ApiException apiException = (ApiException) error;
            int code = apiException.getCode();
            if (code == WebApiConfig.refresh_token_fail) {//refreshToken失效
                SPHelper.clearUserInfo();
                ToastUtil.showMsg(context, apiException.getMessage());
                Intent intent = new Intent(context, MainActivity.class);
                context.startActivity(intent);
                if (context instanceof Activity) {
                    ((Activity) context).finish();
                }
            } else {
                ToastUtil.showMsg(context, "异常错误\n自定义code:" + apiException.getCode() + "\n msg:" +
                        apiException.getMessage());
                onApiError(apiException);
            }
        } else {
            ToastUtil.showMsg(context, "未知错误");
            onApiError(new ApiException(error, ExceptionEngine.ERROR.UNKNOWN, "未知错误"));
        }
    }

    @Override
    public void onComplete() {
        if (this.isShowDialog)
            DialogHelper.stopProgressDlg();
    }


    @Override
    public void onNext(T value) {
        onApiNext(value);
    }


    public abstract void onAddDisposable(Disposable d);

    /** 正确回调 */
    public abstract void onApiNext(T value);

    /** 异常回调 */
    public abstract void onApiError(ApiException ex);

}
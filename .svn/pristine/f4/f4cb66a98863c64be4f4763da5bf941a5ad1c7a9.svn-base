package com.example.mytest2023.Api.Utils;

/**
 * Created by 钟文祥 on 2023/6/17.
 * Describer:
 */
public class BaseResource<T> {
    //状态  这里有多个状态 0表示加载中；1表示成功；2表示联网失败；3表示接口虽然走通，但走的失败（如：关注失败）
    public static final int LOADING = 0;
    public static final int SUCCESS = 1;
    public static final int ERROR = 2;
    public static final int FAIL = 3;
    public static final int PROGRESS = 4;//注意只有下载文件和上传图片时才会有

    public int code;
    public String msg;
    public T data;
    public Throwable error;

    //这里和文件和进度有关了
    public int precent;//文件下载百分比
    public long total;//文件总大小

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public BaseResource(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BaseResource(int code, T data) {
        this.code = code;
        this.data = data;
    }

    public BaseResource(int code, T data, String msg) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public BaseResource(int code, Throwable error) {
        this.code = code;
        this.error = error;
    }

    public BaseResource(int code, int precent, long total) {
        this.code = code;
        this.precent = precent;
        this.total = total;
    }

    //这里定义我们状态的回调
    public interface OnHandleCallback<T> {
        void onLoading(String showMessage);

        void onSuccess(T data);

        void onFailure(String msg);

        void onError(Throwable error);

        void onCompleted();

        void onProgress(int precent, long total);
    }

    //...省略部分代码，便于理解

    //这里是判断，接口走通了，是否走了该走的逻辑，玩android api规则是code =0,算成功
    public static <T> BaseResource<T> response(BaseResponse<T> data) {
        if (data != null) {
            if (data.isSuccess()) {
                return new BaseResource<>(SUCCESS, data.getData(), null);
            }
            return new BaseResource<>(FAIL, null, data.getMsg());
        }
        return new BaseResource<>(ERROR, null, null);
    }


    public static <T> BaseResource<T> failure(String msg) {
        return new BaseResource<>(ERROR, null, msg);
    }

    public static <T> BaseResource<T> error(Throwable t) {
        return new BaseResource<>(ERROR, t);
    }

    public static <T> BaseResource<T> progress(int precent, long total) {
        return new BaseResource<>(PROGRESS, precent, total);
    }

    public void handler(OnHandleCallback<T> callback) {
        switch (code) {
            case LOADING:
                callback.onLoading(msg);
                break;
            case SUCCESS:
                callback.onSuccess(data);
                break;
            case FAIL:
                callback.onFailure(msg);
                break;
            case ERROR:
                callback.onError(error);
                break;
            case PROGRESS:
                callback.onProgress(precent, total);
                break;
        }

        if (code != LOADING) {
            callback.onCompleted();
        }
    }


}


package com.example.mytest2023.Api.Utils;

import android.util.Log;


import com.example.mytest2023.helper.SPHelper;
import com.example.mytest2023.model.overall.UserInfo;
import com.example.mytest2023.BuildConfig;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.Util;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;
import okio.BufferedSource;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by zwx on 2018/6/5.
 * Describer: 服务管理者  单例化Retrofit
 */
public class ServiceManager {

    private static Retrofit retrofit;

    public static synchronized <T> T getClientService(final Class<T> service) {
        return getClientRetrofit(null).create(service);
    }

    public static synchronized <T> T getClientService(final Class<T> service, ProgressResponseBody
            .IProgressListener listener) {
        return getClientRetrofit(listener).create(service);
    }


    private static Retrofit getClientRetrofit(final ProgressResponseBody.IProgressListener
                                                      progressListener) {
        if (retrofit == null) {

            OkHttpClient.Builder builder = new OkHttpClient().newBuilder()
                    //设置超时
                    .connectTimeout(20, TimeUnit.SECONDS)//设置连接超时时间
//                    .writeTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(10, TimeUnit.MINUTES)//获取response的返回等待时间
                    //错误重连
                    .retryOnConnectionFailure(true)
                    //401 HTTP状态码 处理 token验证
//                    .authenticator(new TokenAuthenticator())
                    //头处理
                    .addNetworkInterceptor(new HeaderInterceptor())
                    //添加log拦截器 显示日志
                    .addNetworkInterceptor(getHttpLoggingInterceptor())
                    //添加baseUrl拦截器 添加应用拦截器
                    .addInterceptor(new BaseUrlInterceptor())
                    //自定义code=403  token验证
                    .addInterceptor(new TokenInterceptor());

            if (progressListener != null) {
                //进度
                builder.addNetworkInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Response response = chain.proceed(chain.request());
                        return response.newBuilder().body(new ProgressResponseBody(response.body()
                                , progressListener)).build();
                    }
                });
            }
            OkHttpClient okHttpClient = builder.build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.DEBUG ? WebApiConfig.baseUrlDebug : WebApiConfig
                            .baseUrlRelease)
                    //添加字符串转换支持
                    .addConverterFactory(ScalarsConverterFactory.create())
                    //添加GSON转换支持
                    .addConverterFactory(GsonConverterFactory.create())
                    // .create()转换器的缘故，会将参数请求头的content-type值默认赋值application/json,
                    // 所以这里需要对参数请求头的content-type设置一个正确的值：text/plain
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return retrofit;
    }

    /** 显示日志 */
    private static HttpLoggingInterceptor getHttpLoggingInterceptor() {
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.e("wxb", "网络日志：" + message);
            }
        });
        if (BuildConfig.DEBUG) {
            logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            logInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }
        return logInterceptor;
    }

    /** 头处理 */
    private static class HeaderInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();

            UserInfo userInfo = SPHelper.getUserInfo();
            //头部参数
            request = request.newBuilder()
                    .removeHeader("User-Agent")
                    .removeHeader("Accept-Encoding")
                    .header("token", "")//添加统一通用header,会覆盖前面的token
                    .header("username", userInfo.getPhoneDes())
                    .build();
            Response response = chain.proceed(request);
            if (response.body() != null && response.body().contentType() != null) {
                MediaType mediaType = response.body().contentType();
                String content = response.body().string();
                ResponseBody responseBody = ResponseBody.create(mediaType, content);
                return response.newBuilder().body(responseBody).build();
            } else {
                return response;
            }
        }
    }

    /** 解决多个baseUrl问题 的拦截器  添加@Headers({"baseUrl:XXX"}) */
    private static class BaseUrlInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            //获取request
            Request request = chain.request();

            //获取request的创建者builder
            Request.Builder builder = request.newBuilder();
            //从request中获取headers，通过给定的键url_name
            List<String> headerValues = request.headers("baseUrl");
            if (headerValues != null && headerValues.size() > 0) {
                //如果有这个header，先将配置的header删除，因此header仅用作app和okhttp之间使用
                builder.removeHeader("baseUrl");

                //匹配获得新的BaseUrl
                String headerValue = headerValues.get(0);
                //从request中获取原有的HttpUrl实例oldHttpUrl
                HttpUrl oldHttpUrl = request.url();
                HttpUrl newBaseUrl = null;
                if (headerValue.equals("pay")) {
                    newBaseUrl = HttpUrl.parse("https://www.222.com/");
                } else if (headerValue.equals("weixin")) {
                    newBaseUrl = HttpUrl.parse("https://api.weixin.qq.com/");
                } else {
                    newBaseUrl = oldHttpUrl;
                }

                //重建新的HttpUrl，修改需要修改的url部分
                HttpUrl newFullUrl = oldHttpUrl
                        .newBuilder()
                        .scheme(newBaseUrl.scheme())
                        .host(newBaseUrl.host())
                        .port(newBaseUrl.port())
                        .build();

                //重建这个request，通过builder.url(newFullUrl).build()；
                //然后返回一个response至此结束修改
                //允许重试,使 Chain.proceed()调用多次.
                return chain.proceed(builder.url(newFullUrl).build());
            } else {
                return chain.proceed(request);
            }
        }
    }

    /** 自定义code token验证 */
    private static class TokenInterceptor implements Interceptor {
        @Override
        public Response intercept(final Chain chain) throws IOException {
            final Request request = chain.request();
            final Response originalResponse = chain.proceed(request);

            ResponseBody responseBody = originalResponse.body();
            BufferedSource source = responseBody.source();
            source.request(Long.MAX_VALUE);
            Buffer buffer = source.buffer();
            Charset charset = Util.UTF_8;
            MediaType contentType = responseBody.contentType();
            if (contentType != null) {
                charset = contentType.charset(Util.UTF_8);
            }

            String bodyString = buffer.clone().readString(charset);
            int code = 0;//自定义
            try {
                JSONObject jsonObject = new JSONObject(bodyString);
                code = jsonObject.getInt("code");
            } catch (JSONException e) {
                e.printStackTrace();
            }

//            if (code == WebApiConfig.access_token_fail) {//accessToken失效 403
//                //请求获取拿到新的access_token,重新发起请求
//                String newAccessToken = LoginService.getNewAccessToken();
//                if (TextUtils.isEmpty(newAccessToken)) {//证明refreshToken失效
//                    throw new ServerException(WebApiConfig.refresh_token_fail, "用户登录过期，请重新登录！");
//                }
//                SPHelper.saveAccessToken(newAccessToken);
//                Request newRequest = request.newBuilder().header("token", newAccessToken)
//                        .build();
//                originalResponse.body().close();
//                return chain.proceed(newRequest);
//            } else
            if (code == WebApiConfig.refresh_token_fail) {//refreshToken失效 403
                return originalResponse;
            } else {
                return originalResponse;
            }
        }
    }

    /** token验证 （401 HTTP状态码） */
//    private static class TokenAuthenticator implements Authenticator {
//        //	如果服务端返回一个401错误码(HTTP状态码)的时候，
//        // 401表示未认证，这个时候系统就会调用该方法来获取新的token并重新发起请求
//        @Override
//        public Request authenticate(Route route, Response response) throws IOException {
//            //请求获取拿到新的access_token,重新发起请求
//            String newAccessToken = LoginService.getNewAccessToken();
//            SPHelper.saveAccessToken(newAccessToken);
//            return response.request().newBuilder().header("token", newAccessToken)
//                    .build();
//        }
//    }


}

package com.bank.creditcardcollection.net.retrofit;

import com.bank.creditcardcollection.constant.HttpUrl;
import com.bank.creditcardcollection.net.HttpService;
import com.bank.creditcardcollection.net.okhttp.OkClient;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * 网络请求工具
 * Created by liyanjun on 2017/7/20.
 */
public final class HttpClient {
    private Retrofit retrofit;//网络请求对象
    private HttpService httpService;//请求服务

    private static class SingleTon {
        static final HttpClient INSTANCE = new HttpClient();
    }

    private HttpClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(HttpUrl.HTTP_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(OkClient.getInstance().getClient())
                .build();
        httpService = retrofit.create(HttpService.class);
    }

    /**
     * 获取单例
     *
     * @return
     */
    static HttpClient getInstance() {
        return SingleTon.INSTANCE;
    }

    public Retrofit getClient() {
        return retrofit;
    }

    HttpService getHttpService() {
        return httpService;
    }
}

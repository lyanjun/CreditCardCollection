package com.bank.creditcardcollection.net.okhttp;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * 作者： LYJ
 * 功能： 请求对象
 * 创建日期： 2017/5/3
 */

public final class OkClient {
    private OkHttpClient client;//网络请求对象

    /**
     * 私有的构造器 在HttpClient实例化后 创建OkHttpClient对象
     * 用于网络的请求
     */
    private OkClient(){
        client = new OkHttpClient.Builder()
                .writeTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10,TimeUnit.SECONDS)
                .readTimeout(10,TimeUnit.SECONDS)
                .build();
    }
    private static class SingleTon{
         static final OkClient INSTANCE = new OkClient();
    }

    /**
     * 获取单例
     * @return
     */
    public static OkClient getInstance(){
        return SingleTon.INSTANCE;
    }

    /**
     * 获取OkHttpClient
     * @return
     */
    public OkHttpClient getClient() {
        return client;
    }
}

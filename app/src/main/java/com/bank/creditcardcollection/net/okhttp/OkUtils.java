package com.bank.creditcardcollection.net.okhttp;

import android.os.Handler;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 作者： LYJ
 * 功能： 网络工具类
 * 创建日期： 2017/5/3
 */

public final class OkUtils {
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    //请求标记
    public static final int ERROR = 0;//错误

    /**
     * get请求网络数据
     */
    public static void okGet(final Handler handler, String url, final int requestCode) {
        final Request request = new Request.Builder().url(url).build();
        OkClient.getInstance().getClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.obtainMessage(ERROR, e.getMessage()).sendToTarget();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                handler.obtainMessage(requestCode, response.body().string()).sendToTarget();
            }
        });
    }

    /**
     * post请求网络数据
     */
    public static void okPost(final Handler handler, String url, Map params, final int requestCode) {
        FormBody.Builder builder = new FormBody.Builder();
        Iterator<Map.Entry> iterator = params.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = iterator.next();
            String key = entry.getKey().toString();
            String values = entry.getValue().toString();
            builder.add(key, values);
        }
        Request request = new Request.Builder().url(url).post(builder.build()).build();
        OkClient.getInstance().getClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.obtainMessage(ERROR, e.getMessage()).sendToTarget();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                handler.obtainMessage(requestCode, response.body().string()).sendToTarget();
            }
        });
    }

    /**
     * 上传JSON
     *
     * @param handler
     * @param url
     * @param jsonStr
     * @param requestCode
     */
    public static void okPostJson(final Handler handler, String url, String jsonStr, final int requestCode) {
        //创建一个RequestBody(参数1：数据类型 参数2传递的json串)
        RequestBody requestBody = RequestBody.create(JSON, jsonStr);
        //创建一个请求对象
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        //发送请求获取响应
        OkClient.getInstance().getClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.obtainMessage(ERROR, e.getMessage()).sendToTarget();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                handler.obtainMessage(requestCode, response.body().string()).sendToTarget();
            }
        });
    }
}

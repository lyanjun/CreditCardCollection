package com.bank.creditcardcollection.net.retrofit;


import com.bank.creditcardcollection.net.okhttp.OkUtils;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;

/**
 * 网络请求工具
 * Created by liyanjun on 2017/7/20.
 */

public final class HttpUtils {
    /**
     * 上传json
     * (测试)
     * @param requestJson
     * @return
     */
    public static Observable<String> postJson(String requestJson) {
        RequestBody requestBody = RequestBody.create(OkUtils.JSON, requestJson);
        return uiThread(HttpClient.getInstance()
                .getHttpService()
                .getResultJson(requestBody));
    }

    /**
     * 线程切换
     * @param observable
     * @param <T>
     * @return
     */
    private static<T> Observable<T> uiThread(Observable<T> observable){
        return observable .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }
}

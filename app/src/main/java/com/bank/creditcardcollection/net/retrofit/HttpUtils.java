package com.bank.creditcardcollection.net.retrofit;


import com.bank.creditcardcollection.net.HttpService;
import com.bank.creditcardcollection.net.okhttp.OkUtils;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;
import retrofit2.http.GET;

/**
 * 网络请求工具(上传数据格式为Json)
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
        return getHttpService().getResultJson(getRequestBody(requestJson))
                .compose(rxSchedulerHelper());
    }

    /**
     * 新增信用卡申请
     * @param requestJson
     * @return
     */
    public static Observable<String> postAddApplyResult(String requestJson) {
        return getHttpService().getAddApplyResult(getRequestBody(requestJson))
                .compose(rxSchedulerHelper());
    }

    /**
     * 查询推荐人信息
     * @param requestJson
     * @return
     */
    public static Observable<String> postAddReferrerInfoRequest(String requestJson) {
        return getHttpService().getAddReferrerInfoResult(getRequestBody(requestJson))
                .compose(rxSchedulerHelper());
    }

    /**
     * 获取下载服务
     * @return
     */
    private static HttpService getHttpService(){
        return HttpClient.getInstance()
                .getHttpService();
    }


    /**
     * 获取请求体
     * @param requestJson
     * @return
     */
    private static RequestBody getRequestBody(String requestJson){
        return RequestBody.create(OkUtils.JSON, requestJson);
    }
    /**
     * 统一线程处理
     * (异步线程处理)
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<T, T> rxSchedulerHelper() {
        return upstream -> upstream.
                subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }
}

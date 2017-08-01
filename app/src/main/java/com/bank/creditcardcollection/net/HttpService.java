package com.bank.creditcardcollection.net;


import com.bank.creditcardcollection.constant.HttpUrl;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * 网络请求
 * Created by liyanjun on 2017/7/19.
 */

public interface HttpService {
    /**
     * 请求Json数据
     * @param requestBody
     * @return
     */
    @POST(HttpUrl.FL)
    Observable<String> getResultJson(@Body RequestBody requestBody);

    /**
     * 新增申请返回结果
     * @param requestBody
     * @return
     */
    @POST(HttpUrl.APPLY_ADD)
    Observable<String> getAddApplyResult(@Body RequestBody requestBody);
}

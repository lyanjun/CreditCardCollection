package com.bank.creditcardcollection.model.old.request;

/**
 * 请求头
 * Created by liyanjun on 2017/7/19.
 */

public class HeaderReq {
    private String serviceCode;//服务编码

    public HeaderReq(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public HeaderReq() {
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }
}

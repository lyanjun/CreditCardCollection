package com.bank.creditcardcollection.model.request;

/**
 * 请求体
 * Created by liyanjun on 2017/7/19.
 */

public class ReqReport{
    private HeaderReq header;//请求头
    private BodyReq body;//请求体

    public ReqReport(HeaderReq header, BodyReq body) {
        this.header = header;
        this.body = body;
    }

    public HeaderReq getHeader() {
        return header;
    }

    public void setHeader(HeaderReq header) {
        this.header = header;
    }

    public BodyReq getBody() {
        return body;
    }

    public void setBody(BodyReq body) {
        this.body = body;
    }
}

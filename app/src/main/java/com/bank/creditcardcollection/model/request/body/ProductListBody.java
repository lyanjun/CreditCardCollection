package com.bank.creditcardcollection.model.request.body;


import com.bank.creditcardcollection.model.request.BodyReq;

/**
 * 产品列表
 * Created by liyanjun on 2017/7/19.
 */

public class ProductListBody extends BodyReq {
    private int pageNo;//请求页
    private int pageSize;//数据个数

    public ProductListBody() {
    }

    public ProductListBody(int pageNo, int pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}

package com.bank.creditcardcollection.model.now.request;

/**
 * 推荐人查询
 * Created by liyanjun on 2017/8/3.
 */

public class ReferreRequest {
    private String name;

    public ReferreRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

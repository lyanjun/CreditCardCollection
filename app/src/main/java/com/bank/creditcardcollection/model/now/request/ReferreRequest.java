package com.bank.creditcardcollection.model.now.request;

/**
 * 推荐人查询
 * Created by liyanjun on 2017/8/3.
 */

public class ReferreRequest {
    private String loginName;
    private String status = "1";//后台用于判断

    public ReferreRequest(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

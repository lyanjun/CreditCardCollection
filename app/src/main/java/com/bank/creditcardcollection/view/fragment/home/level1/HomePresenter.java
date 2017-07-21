package com.bank.creditcardcollection.view.fragment.home.level1;

import com.bank.creditcardcollection.base.BaseRxPresenter;
import com.bank.creditcardcollection.constant.HttpUrl;
import com.bank.creditcardcollection.model.request.HeaderReq;
import com.bank.creditcardcollection.model.request.ReqReport;
import com.bank.creditcardcollection.model.request.body.ProductListBody;
import com.bank.creditcardcollection.net.retrofit.HttpUtils;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;


/**
 * 控制器
 * Created by liyanjun on 2017/7/19.
 */

public class HomePresenter extends BaseRxPresenter<HomeContract.View> implements HomeContract.Presenter{


    public HomePresenter(HomeContract.View view) {
        super(view);
    }

    /**
     * 初始化数据
     */
    @Override
    public void initData() {
        mView.initViewData("初始化");
    }

    public void test(){
//        ReqReport reqReport = new ReqReport(new HeaderReq(HttpUrl.PRODUCT_LIST_CODE), new ProductListBody(1, 4));
//        String json = new Gson().toJson(reqReport);
        String json = new Gson().toJson(new A("1"));
        Logger.t("请求报文").w(json);
        mView.showLoadingDialog();
        addSubscribe( HttpUtils.postJson(json)
                .doFinally(() -> mView.hideLoadingDialog())
                .subscribe(s -> mView.initViewData(s),
                throwable -> mView.initViewData(throwable.getMessage())));
    }
    static class A {
        private String name;
//        private int age;
        private String type;

        public A(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

//        public A(String name, int age) {
//            this.name = name;
//            this.age = age;
//        }
//
//        public String getName() {
//            return name;
//        }
//
//        public void setName(String name) {
//            this.name = name;
//        }
//
//        public int getAge() {
//            return age;
//        }
//
//        public void setAge(int age) {
//            this.age = age;
//        }
    }
    @Override
    public void onDestroy() {
        detachView();//解除绑定
    }
}

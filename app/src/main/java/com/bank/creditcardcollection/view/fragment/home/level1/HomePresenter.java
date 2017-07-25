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

    @Override
    public void onDestroy() {
        detachView();//解除绑定
    }
}

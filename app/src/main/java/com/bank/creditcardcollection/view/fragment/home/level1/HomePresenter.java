package com.bank.creditcardcollection.view.fragment.home.level1;

import com.bank.creditcardcollection.base.BaseRxPresenter;


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
}

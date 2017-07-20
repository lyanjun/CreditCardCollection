package com.bank.creditcardcollection.base;

import com.lyan.tools.mvp.presenter.MineBasePresenter;

/**
 * 控制层
 * Created by liyanjun on 2017/7/19.
 */

public interface BasePresenter<V extends BaseView> extends MineBasePresenter{
    void attachView(V view);//挂载

    void detachView();//解除挂载
}

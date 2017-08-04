package com.bank.creditcardcollection.base.activity;

import com.bank.creditcardcollection.base.BaseView;


/**
 * Created by liyanjun on 2017/8/4.
 */

public abstract class CommonPresenter<V extends BaseView> implements LifePresenter<V>{
    protected V mView;

    @SuppressWarnings("unchecked")
    public CommonPresenter (V view){
        attachView(view);//绑定视图
        mView.setPresenter(this);
    }

    @Override
    public final void attachView(V view) {
        this.mView = view;
    }

    @Override
    public final void detachView() {
        this.mView = null;
    }

    @Override
    public void onDestroy() {
        detachView();
    }
}

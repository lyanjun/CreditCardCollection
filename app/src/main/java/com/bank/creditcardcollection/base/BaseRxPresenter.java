package com.bank.creditcardcollection.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Rx控制器
 * Created by liyanjun on 2017/7/19.
 */

public abstract class BaseRxPresenter<V extends BaseView> implements BasePresenter<V>{

    protected V mView;
    protected CompositeDisposable mCompositeDisposable;

    public BaseRxPresenter (V view){
        attachView(view);//绑定视图
        mView.setPresenter(this);
    }

    /**
     * 解除订阅
     */
    protected void unSubscribe() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }

    /**
     * 初始化数据
     */
    public abstract void initData();
    /**
     * 增加订阅
     * @param disposable
     */
    protected void addSubscribe(Disposable disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }

    @Override
    public final void attachView(V view) {
        this.mView = view;
    }

    @Override
    public final void detachView() {
        this.mView = null;
        unSubscribe();
    }
}

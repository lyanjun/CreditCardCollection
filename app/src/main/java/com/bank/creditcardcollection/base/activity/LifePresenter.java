package com.bank.creditcardcollection.base.activity;

import com.bank.creditcardcollection.base.BasePresenter;
import com.bank.creditcardcollection.base.BaseView;

/**
 * 拓展一个方法
 * Created by liyanjun on 2017/7/27.
 */

public interface LifePresenter<V extends BaseView> extends BasePresenter<V>{
    void initData();//初始化
    void onDestroy();//释放资源
}

package com.bank.creditcardcollection.view.activity.main;

import com.bank.creditcardcollection.base.BaseView;
import com.bank.creditcardcollection.base.activity.LifePresenter;

/**
 * Created by liyanjun on 2017/8/4.
 */

public interface MainContract {

    interface View extends BaseView<MainContract.Presenter> {

    }
    /**
     * 控制器
     */
    interface Presenter extends LifePresenter<MainContract.View> {
        void showFragment(int position);
    }
}

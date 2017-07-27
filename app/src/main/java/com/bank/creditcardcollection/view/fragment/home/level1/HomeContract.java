package com.bank.creditcardcollection.view.fragment.home.level1;


import com.bank.creditcardcollection.base.BasePresenter;
import com.bank.creditcardcollection.base.BaseView;
import com.bank.creditcardcollection.base.activity.LifePresenter;

/**
 * 契约接口
 * Created by liyanjun on 2017/7/19.
 */

public interface HomeContract {
    /**
     * 视图
     */
    interface View<P extends Presenter> extends BaseView<P> {
        void initViewData(String message);
    }

    /**
     * 控制器
     */
    interface Presenter extends LifePresenter<View> {
    }
}

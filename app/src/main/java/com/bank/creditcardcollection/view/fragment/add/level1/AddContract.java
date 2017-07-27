package com.bank.creditcardcollection.view.fragment.add.level1;

import com.bank.creditcardcollection.base.BaseView;
import com.bank.creditcardcollection.base.activity.LifePresenter;

/**
 * Created by liyanjun on 2017/7/27.
 */

public interface AddContract {
    /**
     * 视图
     */
    interface View<P extends AddContract.Presenter> extends BaseView<P> {

    }

    /**
     * 控制器
     */
    interface Presenter extends LifePresenter<AddContract.View> {
    }
}

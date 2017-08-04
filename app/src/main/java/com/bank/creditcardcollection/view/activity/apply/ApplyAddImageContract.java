package com.bank.creditcardcollection.view.activity.apply;

import com.bank.creditcardcollection.base.BaseView;
import com.bank.creditcardcollection.base.activity.LifePresenter;

/**
 *
 * Created by liyanjun on 2017/8/4.
 */

public interface ApplyAddImageContract {

    interface View extends BaseView<ApplyAddImageContract.Presenter>{

    }
    /**
     * 控制器
     */
    interface Presenter extends LifePresenter<ApplyAddImageContract.View> {
    }
}

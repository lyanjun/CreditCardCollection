package com.bank.creditcardcollection.view.fragment.base;

import com.bank.creditcardcollection.base.activity.LifePresenter;

/**
 * Created by liyanjun on 2017/8/2.
 */

public abstract class BaseMvpViewFragment<P extends LifePresenter> extends BaseWithOutBackFragment{
    protected P mPresenter;

    @Override
    public void onDestroy() {
        if (null != mPresenter) mPresenter.onDestroy();
        super.onDestroy();
    }
}

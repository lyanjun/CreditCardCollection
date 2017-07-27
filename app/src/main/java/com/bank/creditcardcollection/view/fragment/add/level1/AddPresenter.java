package com.bank.creditcardcollection.view.fragment.add.level1;

import com.bank.creditcardcollection.base.BaseRxPresenter;

/**
 * Created by liyanjun on 2017/7/27.
 */

public class AddPresenter extends BaseRxPresenter<AddContract.View> implements AddContract.Presenter{

    public AddPresenter(AddContract.View view) {
        super(view);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onDestroy() {
        detachView();
    }
}

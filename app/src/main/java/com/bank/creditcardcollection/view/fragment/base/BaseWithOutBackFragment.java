package com.bank.creditcardcollection.view.fragment.base;


import com.bank.creditcardcollection.constant.Constant;

/**
 * 无返回键
 * Created by liyanjun on 2017/7/19.
 */

public abstract class BaseWithOutBackFragment extends BaseTitleFragment{
    @Override
    protected int setRootLayoutType() {
        return Constant.LINEAR_LAYOUT;
    }

    @Override
    protected boolean addBackBtn() {
        return false;
    }
}

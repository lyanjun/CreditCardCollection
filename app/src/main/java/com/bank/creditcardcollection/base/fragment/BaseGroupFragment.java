package com.bank.creditcardcollection.base.fragment;


import com.bank.creditcardcollection.R;

/**
 * 模块(最初的四个模块都是当做容器用
 * 所以布局是一样的)
 * Created by liyanjun on 2017/7/19.
 */

public class BaseGroupFragment extends BaseMainFragment {

    /**
     * 设置容器的布局
     *
     * @return
     */
    @Override
    protected final int getLayoutID() {
        return R.layout.fragment_group;
    }
}

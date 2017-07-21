package com.bank.creditcardcollection.view.fragment.calculator.level1;

import android.os.Bundle;

import com.bank.creditcardcollection.R;
import com.bank.creditcardcollection.view.fragment.base.BaseWithOutBackFragment;
import com.lyan.tools.utils.ToastUtils;


/**
 * 计算器界面
 * Created by liyanjun on 2017/7/19.
 */

public class CalculatorFragment extends BaseWithOutBackFragment {
    /**
     * 获取对象
     * @return
     */
    public static CalculatorFragment newInstance() {
        Bundle args = new Bundle();
        CalculatorFragment fragment = new CalculatorFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int getLayoutID() {
        return R.layout.fragment_calculator;
    }

    @Override
    protected void setTitleBar() {
        super.setTitleBar();
        mTitleBar.setTitle(R.string.clms_calculator);
    }

    @Override
    protected void setFunction() {
        super.setFunction();
        ToastUtils.shortToast(mContext,"2");
    }
}

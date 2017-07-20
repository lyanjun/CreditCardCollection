package com.bank.creditcardcollection.view.fragment.calculator;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.bank.creditcardcollection.R;
import com.bank.creditcardcollection.view.fragment.base.BaseGroupFragment;
import com.bank.creditcardcollection.view.fragment.calculator.level1.CalculatorFragment;


/**
 * 计算器界面容器
 * Created by liyanjun on 2017/7/19.
 */

public class CalculatorGroupFragment extends BaseGroupFragment {
    /**
     * 获取对象
     * @return
     */
    public static CalculatorGroupFragment newInstance() {
        Bundle args = new Bundle();
        CalculatorGroupFragment fragment = new CalculatorGroupFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected void setFunction() {
        super.setFunction();
        mRootView.setBackgroundColor(Color.RED);
    }
    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        if (findChildFragment(CalculatorFragment.class) == null) {
            loadRootFragment(R.id.group_home, CalculatorFragment.newInstance());
        }
    }
}

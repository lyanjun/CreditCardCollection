package com.bank.creditcardcollection.base.activity;

import android.support.annotation.LayoutRes;
import com.lyan.tools.activity.BaseActivity;
import butterknife.ButterKnife;

/**
 * Activity基类
 * Created by liyanjun on 2017/7/21.
 */

public class MineBaseActivity extends BaseActivity{
    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }
}

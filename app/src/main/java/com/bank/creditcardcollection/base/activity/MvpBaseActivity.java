package com.bank.creditcardcollection.base.activity;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.bank.creditcardcollection.base.activity.LifePresenter;
import com.bank.creditcardcollection.weight.dialog.LoadingDialog;
import com.lyan.tools.activity.BaseActivity;

import butterknife.ButterKnife;

/**
 * Created by liyanjun on 2017/8/4.
 */

public class MvpBaseActivity<P extends LifePresenter> extends BaseActivity {
    protected P mPresenter;
    private LoadingDialog mLoadingDialog;//加载等待

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLoadingDialog = new LoadingDialog(this);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }
    public void showLoadingDialog() {
        mLoadingDialog.show();
    }

    public void hideLoadingDialog() {
        mLoadingDialog.dismiss();
    }

    @Override
    public void onDestroy() {
        if (null != mPresenter) mPresenter.onDestroy();
        super.onDestroy();
    }
}

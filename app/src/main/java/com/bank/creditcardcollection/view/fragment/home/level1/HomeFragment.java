package com.bank.creditcardcollection.view.fragment.home.level1;

import android.os.Bundle;
import android.widget.Button;

import com.bank.creditcardcollection.R;
import com.bank.creditcardcollection.constant.information.Information;
import com.bank.creditcardcollection.constant.information.InformationUtils;
import com.bank.creditcardcollection.view.fragment.base.BaseWothOutBackFragment;
import com.lyan.tools.utils.ToastUtils;
import com.orhanobut.logger.Logger;

import butterknife.BindView;

/**
 * 主界面（一级界面）
 * Created by liyanjun on 2017/7/19.
 */

public class HomeFragment extends BaseWothOutBackFragment implements HomeContract.View<HomePresenter> {

    @BindView(R.id.test)
    Button testBtn;
    private HomePresenter mHomePresenter;

    /**
     * 获取对象
     *
     * @return
     */
    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * 设置布局
     *
     * @return
     */
    @Override
    protected int getLayoutID() {
        return R.layout.fragment_home;
    }

    /**
     * 控制器
     *
     * @param presenter
     */
    @Override
    public void setPresenter(HomePresenter presenter) {
        this.mHomePresenter = presenter;
    }

    /**
     * 销毁视图释放资源
     */
    @Override
    public void onDestroy() {
        if (mHomePresenter != null) mHomePresenter.onDestroy();
        super.onDestroy();
    }


    /**
     * 设置标题栏
     */
    @Override
    protected void setTitleBar() {
        super.setTitleBar();
        mTitleBar.setTitle(getString(R.string.clms_home));
    }

    /**
     * 设置功能
     */
    @Override
    protected void setFunction() {
        super.setFunction();
        new HomePresenter(this);
        mHomePresenter.initData();//初始化数据
//        testBtn.setOnClickListener(v -> mHomePresenter.test());
        testBtn.setOnClickListener(v -> ToastUtils.shortToast(mContext,InformationUtils.getInfo().getCredentialType().get(1)));
    }

    /**
     * 初始化界面的数据
     *
     * @param message
     */
    @Override
    public void initViewData(String message) {
        ToastUtils.shortToast(mContext, message);
        Logger.t("返回").w(message);
    }
}

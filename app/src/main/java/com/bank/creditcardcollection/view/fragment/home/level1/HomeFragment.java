package com.bank.creditcardcollection.view.fragment.home.level1;

import android.os.Bundle;
import android.widget.Button;

import com.bank.creditcardcollection.R;
import com.bank.creditcardcollection.net.retrofit.HttpUtils;
import com.bank.creditcardcollection.view.fragment.base.BaseWithOutBackFragment;
import com.google.gson.Gson;
import com.lyan.tools.utils.ToastUtils;
import com.orhanobut.logger.Logger;

import butterknife.BindView;

/**
 * 主界面（一级界面）
 * Created by liyanjun on 2017/7/19.
 */

public class HomeFragment extends BaseWithOutBackFragment implements HomeContract.View<HomePresenter> {

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
        testBtn.setOnClickListener(v -> {
            HttpUtils.postJson(new Gson().toJson(new A(1)))
                    .subscribe(s -> {
                                Logger.t("返回数据").i(s);
                                ToastUtils.shortToast(mContext, s);
                            }
                            , throwable -> {
                                Logger.t("请求错误").w(throwable.getMessage());
                                ToastUtils.shortToast(mContext, throwable.getMessage());
                            });
        });
    }

    class A {
        private int gid;

        public A(int gid) {
            this.gid = gid;
        }

        public int getGid() {
            return gid;
        }

        public void setGid(int gid) {
            this.gid = gid;
        }
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

package com.bank.creditcardcollection.base;

import com.lyan.tools.mvp.view.MineBaseView;

/**
 * 视图层
 * Created by liyanjun on 2017/7/19.
 */

public interface BaseView<P extends BasePresenter> extends MineBaseView {
    void setPresenter(P presenter);
    void showLoadingDialog();
    void hideLoadingDialog();
}

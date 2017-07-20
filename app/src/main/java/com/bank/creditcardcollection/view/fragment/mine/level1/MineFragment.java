package com.bank.creditcardcollection.view.fragment.mine.level1;

import android.os.Bundle;

import com.bank.creditcardcollection.R;
import com.bank.creditcardcollection.view.fragment.base.BaseWothOutBackFragment;
import com.lyan.tools.utils.ToastUtils;


/**
 * 我的界面
 * Created by liyanjun on 2017/7/19.
 */

public class MineFragment extends BaseWothOutBackFragment {
    /**
     * 获取对象
     * @return
     */
    public static MineFragment newInstance() {
        Bundle args = new Bundle();
        MineFragment fragment = new MineFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int getLayoutID() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void setTitleBar() {
        super.setTitleBar();
        mTitleBar.setTitle(R.string.clms_mine);
    }
    @Override
    protected void setFunction() {
        super.setFunction();
        ToastUtils.shortToast(mContext,"4");
    }
}

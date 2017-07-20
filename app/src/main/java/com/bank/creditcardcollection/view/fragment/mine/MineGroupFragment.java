package com.bank.creditcardcollection.view.fragment.mine;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.bank.creditcardcollection.R;
import com.bank.creditcardcollection.view.fragment.base.BaseGroupFragment;
import com.bank.creditcardcollection.view.fragment.mine.level1.MineFragment;


/**
 * 我的界面容器
 * Created by liyanjun on 2017/7/19.
 */

public class MineGroupFragment extends BaseGroupFragment {
    /**
     * 获取对象
     * @return
     */
    public static MineGroupFragment newInstance() {
        Bundle args = new Bundle();
        MineGroupFragment fragment = new MineGroupFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void setFunction() {
        super.setFunction();
        mRootView.setBackgroundColor(Color.GREEN);
    }
    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        if (findChildFragment(MineFragment.class) == null) {
            loadRootFragment(R.id.group_home, MineFragment.newInstance());
        }
    }
}

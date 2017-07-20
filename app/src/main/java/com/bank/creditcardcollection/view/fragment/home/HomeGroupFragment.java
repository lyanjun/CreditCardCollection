package com.bank.creditcardcollection.view.fragment.home;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.bank.creditcardcollection.R;
import com.bank.creditcardcollection.view.fragment.base.BaseGroupFragment;
import com.bank.creditcardcollection.view.fragment.home.level1.HomeFragment;


/**
 * 首页界面容器
 * Created by liyanjun on 2017/7/19.
 */

public class HomeGroupFragment extends BaseGroupFragment {

    /**
     * 获取对象
     * @return
     */
    public static HomeGroupFragment newInstance() {
        Bundle args = new Bundle();
        HomeGroupFragment fragment = new HomeGroupFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected void setFunction() {
        super.setFunction();
        mRootView.setBackgroundColor(Color.CYAN);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        if (findChildFragment(HomeFragment.class) == null) {
            loadRootFragment(R.id.group_home, HomeFragment.newInstance());
        }
    }
}

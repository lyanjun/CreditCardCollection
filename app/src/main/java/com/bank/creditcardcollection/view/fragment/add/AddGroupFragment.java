package com.bank.creditcardcollection.view.fragment.add;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.bank.creditcardcollection.R;
import com.bank.creditcardcollection.view.fragment.base.BaseGroupFragment;
import com.bank.creditcardcollection.view.fragment.add.level1.AddFragment;


/**
 * 我的界面容器
 * Created by liyanjun on 2017/7/19.
 */

public class AddGroupFragment extends BaseGroupFragment {
    /**
     * 获取对象
     * @return
     */
    public static AddGroupFragment newInstance() {
        Bundle args = new Bundle();
        AddGroupFragment fragment = new AddGroupFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        if (findChildFragment(AddFragment.class) == null) {
            loadRootFragment(R.id.group_home, AddFragment.newInstance());
        }
    }
}

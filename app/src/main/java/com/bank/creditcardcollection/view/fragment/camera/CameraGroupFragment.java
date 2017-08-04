package com.bank.creditcardcollection.view.fragment.camera;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.bank.creditcardcollection.R;
import com.bank.creditcardcollection.base.fragment.BaseGroupFragment;
import com.bank.creditcardcollection.view.fragment.camera.level1.CameraFragment;


/**
 * 相机界面容器
 * Created by liyanjun on 2017/7/19.
 */

public class CameraGroupFragment extends BaseGroupFragment {
    /**
     * 获取对象
     * @return
     */
    public static CameraGroupFragment newInstance() {
        Bundle args = new Bundle();
        CameraGroupFragment fragment = new CameraGroupFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected void setFunction() {
        super.setFunction();
        mRootView.setBackgroundColor(Color.YELLOW);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        if (findChildFragment(CameraFragment.class) == null) {
            loadRootFragment(R.id.group_home, CameraFragment.newInstance());
        }
    }
}

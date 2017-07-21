package com.bank.creditcardcollection.view.fragment.camera.level1;

import android.os.Bundle;

import com.bank.creditcardcollection.R;
import com.bank.creditcardcollection.view.fragment.base.BaseWithOutBackFragment;
import com.lyan.tools.utils.ToastUtils;


/**
 * 相机界面
 * Created by liyanjun on 2017/7/19.
 */

public class CameraFragment extends BaseWithOutBackFragment {
    /**
     * 获取对象
     * @return
     */
    public static CameraFragment newInstance() {
        Bundle args = new Bundle();
        CameraFragment fragment = new CameraFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int getLayoutID() {
        return R.layout.fragment_camera;
    }

    @Override
    protected void setTitleBar() {
        super.setTitleBar();
        mTitleBar.setTitle(R.string.clms_camera);
    }
    @Override
    protected void setFunction() {
        super.setFunction();
        ToastUtils.shortToast(mContext,"3");
    }
}

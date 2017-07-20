package com.bank.creditcardcollection.view.fragment.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lyan.tools.fragment.MPermissionsFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Fragment基类
 * Created by liyanjun on 2017/7/19.
 */

public abstract class BaseFragment extends MPermissionsFragment{
    protected View mRootView;//视图
    protected Context mContext;//上下文
    protected Activity mActivity;//活动
    protected Unbinder mUnbinder;//控件绑定

    /**
     * 加载
     *
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context != null) {
            this.mActivity = (Activity) (this.mContext = context);
        } else {
            this.mContext = this.mActivity = getActivity();
        }
    }

    /**
     * 初始化视图
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null){
            mRootView = inflater.inflate(getLayoutID(),container,false);
            mUnbinder = ButterKnife.bind(this, mRootView);
            setFunction();//设置功能
        }
        return mRootView;
    }

    /**
     * 设置功能
     */
    protected void setFunction(){}

    /**
     * 视图的布局
     * @return
     */
    @LayoutRes
    protected abstract int getLayoutID();

    /**
     * 销毁
     */
    @Override
    public void onDestroy() {
        if (mUnbinder != null) mUnbinder.unbind();//接触控件绑定
        super.onDestroy();
    }
}

package com.bank.creditcardcollection.view.fragment.base;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bank.creditcardcollection.R;
import com.bank.creditcardcollection.constant.Constant;
import com.bank.creditcardcollection.weight.dialog.LoadingDialog;
import com.lyan.tools.view.TitleBar;

import butterknife.ButterKnife;

/**
 * 带有标题的视图界面
 * Created by liyanjun on 2017/7/19.
 */

public abstract class BaseTitleFragment extends BaseFragment{

    protected TitleBar mTitleBar;//标题栏
    private LoadingDialog mLoadingDialog;//加载等待
    /**
     * 设置功能
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null){
            addCustomView(inflater);//添加视图和标题栏
            mUnbinder = ButterKnife.bind(this, mRootView);
            setFunction();//设置功能
        }
        return mRootView;
    }

    /**
     * 添加视图
     * @param inflater
     */
    private void addCustomView(LayoutInflater inflater){
        final View bodyView = inflater.inflate(getLayoutID(), null);
        mTitleBar = new TitleBar(mContext);
        switch (setRootLayoutType()) {
            case Constant.LINEAR_LAYOUT://创建线性布局
                mRootView = new LinearLayout(mContext);
                ((LinearLayout) mRootView).setOrientation(LinearLayout.VERTICAL);
                ((LinearLayout) mRootView).addView(mTitleBar);//添加标题栏
                ((LinearLayout) mRootView).addView(bodyView);//添加内容
                LinearLayout.LayoutParams tParamsL = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                mTitleBar.setLayoutParams(tParamsL);
                LinearLayout.LayoutParams bParamsL = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT);
                bodyView.setLayoutParams(bParamsL);
                break;
            case Constant.RELATIVE_LAYOUT://创建相对布局
                mRootView = new RelativeLayout(mContext);
                ((RelativeLayout) mRootView).addView(bodyView);//添加内容
                ((RelativeLayout) mRootView).addView(mTitleBar);//添加标题栏
                RelativeLayout.LayoutParams tParamsR = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                mTitleBar.setLayoutParams(tParamsR);
                RelativeLayout.LayoutParams bParamsR = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                        RelativeLayout.LayoutParams.MATCH_PARENT);
                bodyView.setLayoutParams(bParamsR);
                break;
        }
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        mRootView.setLayoutParams(params);
    }

    /**
     * 设置根布局类型
     * @return
     */
    protected abstract int setRootLayoutType();

    /**
     * 设置功能
     */
    @Override
    protected void setFunction() {
        mLoadingDialog = new LoadingDialog(mContext);
        setTitleBar();
    }

    /**
     * 设置标题栏
     */
    protected void setTitleBar(){
        mTitleBar.setTitleBarBackground(R.drawable.title_bar_background);//设置标题栏北京颜色
        mTitleBar.removeLeftViewDefaultChild();//清空默认的返回键
        if (addBackBtn()){
            ImageButton backButton = new ImageButton(mContext);
            backButton.setBackgroundColor(Color.TRANSPARENT);//设置背景颜色
            backButton.setImageResource(R.drawable.title_bar_back_icon);//返回键按钮的图片
            backButton.setScaleType(ImageView.ScaleType.FIT_START);
            backButton.setOnClickListener(v -> pop());
            mTitleBar.addLeftView(backButton);
        }
    }
    protected abstract boolean addBackBtn();


    public void showLoadingDialog() {
        mLoadingDialog.show();
    }

    public void hideLoadingDialog() {
        mLoadingDialog.dismiss();
    }
}

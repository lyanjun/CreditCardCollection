package com.bank.creditcardcollection.weight.view.apply.level;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.bank.creditcardcollection.weight.view.apply.listener.LevelApplyInfoListener;
import com.bank.creditcardcollection.weight.view.apply.listener.LevelResetListener;
import com.bank.creditcardcollection.weight.view.apply.listener.LevelSetMessageListener;
import com.orhanobut.logger.Logger;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 步骤
 * Created by liyanjun on 2017/7/21.
 */

public abstract class LevelView extends LinearLayout implements LevelResetListener{
    protected Context mContext;
    protected Unbinder mUnbinder;
    private boolean isFirstInit;//初始化
    protected LevelApplyInfoListener applyInfoListener;
    protected LevelSetMessageListener setMessageListener;
    public LevelView(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public LevelView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    public LevelView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
    }

    /**
     * 初始化空间
     */
    private void initView() {
        inflate(mContext, setContentView(), this);//设置布局
        isFirstInit = true;//当控件挂载的时候设置功能
//        Logger.t("初始化").i("创建新的空间对象的时候调用");
    }

    /**
     * 设置布局
     *
     * @return
     */
    @LayoutRes
    protected abstract int setContentView();

    /**
     * 设置功能
     */
    protected abstract void setFunction();

    /**
     * 挂在到窗口上
     */
    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        mUnbinder = ButterKnife.bind(this);//绑定控件
        if (isFirstInit){//保证当空间挂载到窗口的时候只设置一次初始化功能
            setFunction();//设置功能
            isFirstInit = false;//关闭初始化功能
        }
//        Logger.t("挂载").i("挂在到窗口上");
    }

    /**
     * 从窗口上分离
     */
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mUnbinder.unbind();//解除绑定
//        Logger.t("分离").i("从窗口上分离");
    }

    /**
     * 设置步骤相关的操作
     * @param applyInfoListener
     */
    public void setApplyInfoListener(LevelApplyInfoListener applyInfoListener) {
        this.applyInfoListener = applyInfoListener;
    }

    /**
     * 获取相关步骤的信息
     * @param setMessageListener
     */
    public void setSetMessageListener(LevelSetMessageListener setMessageListener) {
        this.setMessageListener = setMessageListener;
    }
}

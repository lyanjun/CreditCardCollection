package com.bank.creditcardcollection.base.activity;


import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bank.creditcardcollection.R;
import com.lyan.tools.view.TitleBar;

import butterknife.ButterKnife;

/**
 * 带有标题的界面
 * Created by liyanjun on 2017/8/2.
 */

public abstract class TitleActivity<P extends LifePresenter> extends MvpBaseActivity<P> {
    protected TitleBar mTitleBar;
    private View body;//内容
    private LinearLayout mParentView;//容器

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        mParentView = new LinearLayout(this);//创建一个线性布局的对象
        mParentView.setOrientation(LinearLayout.VERTICAL);//设置内容的排列方式
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);//设置布局大小
        mParentView.setLayoutParams(params);//设置布局大小
        mTitleBar = new TitleBar(this);//标题栏
        mParentView.addView(mTitleBar);//添加标题栏
//        body = getLayoutInflater().inflate(layoutResID,mParentView,true);
        body = getLayoutInflater().inflate(layoutResID,null);

        mParentView.addView(body);//添加内容
        setContentView(mParentView);
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        ButterKnife.bind(this);
        setTitleBar();
    }

    /**
     * 设置标题栏
     */
    protected  void setTitleBar(){
        mTitleBar.setTitleBarBackground(R.drawable.title_bar_background);//设置标题栏背景颜色
        mTitleBar.removeLeftViewDefaultChild();//清空默认的返回键
        if (addBackBtn()){
            ImageButton backButton = new ImageButton(this);
            backButton.setBackgroundColor(Color.TRANSPARENT);//设置背景颜色
            backButton.setImageResource(R.drawable.title_bar_back_icon);//返回键按钮的图片
            backButton.setScaleType(ImageView.ScaleType.FIT_START);
            backButton.setOnClickListener(v -> finish());
            mTitleBar.addLeftView(backButton);
        }
    }

    /**
     * 是否添加返回键
     * @return
     */
    protected abstract boolean addBackBtn();
}

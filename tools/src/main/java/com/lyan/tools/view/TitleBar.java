package com.lyan.tools.view;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lyan.tools.R;


/**
 * 作者： LYJ
 * 功能： 默认的标题栏
 * 创建日期： 2017/5/4
 */

public class TitleBar extends FrameLayout {
    private ImageButton titleBack;
    private TextView titleName;
    private LinearLayout titleRight;//右侧
    private LinearLayout titleGroup;
    private LinearLayout titleLeft;//左侧

    public TitleBar(Context context) {
        super(context);
        initialize(context);//初始化
    }

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context);//初始化
    }

    /**
     * 初始化
     */
    private void initialize(Context context) {
        View view = View.inflate(context, R.layout.custom_title_bar, this);
        //初始化控件
        titleName = (TextView) view.findViewById(R.id.title_txt);
        titleRight = (LinearLayout) view.findViewById(R.id.title_right);
        titleGroup = (LinearLayout) view.findViewById(R.id.title_group);
        titleLeft = (LinearLayout) view.findViewById(R.id.title_left);
        addBackBtn(context);
        addListenerToView();
    }

    /**
     * 添加返回键
     *
     * @param context
     */
    private void addBackBtn(Context context) {
        titleBack = new ImageButton(context);
        titleBack.setBackgroundResource(0);
        titleBack.setScaleType(ImageView.ScaleType.FIT_START);
        addLeftView(titleBack);
    }

    /**
     * 关闭当前界面
     */
    private void addListenerToView() {
        titleBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity) getContext()).finish();
            }
        });
    }

    /**
     * 设置标题
     *
     * @param title
     */
    public void setTitle(@StringRes int title) {
        titleName.setText(title);
    }

    /**
     * 设置标题
     *
     * @param title
     */
    public void setTitle(CharSequence title) {
        titleName.setText(title);
    }

    /**
     * 设置字体颜色
     *
     * @param color
     */
    public void setTitleColor(@ColorInt int color) {
        titleName.setTextColor(color);
    }

    /**
     * 设置是否显示返回键
     *
     * @param isShow
     */
    public void backBtnIsShow(boolean isShow) {
        int visibility = isShow ? VISIBLE : GONE;
        titleBack.setVisibility(visibility);
    }

    /**
     * 设置标题栏的背景颜色
     *
     * @param color
     */
    public void setTitleBarBackgroundColor(@ColorInt int color) {
        titleGroup.setBackgroundColor(color);
    }
    /**
     * 设置标题栏的背景颜色
     *
     * @param background
     */
    public void setTitleBarBackground(@DrawableRes int background) {
        titleGroup.setBackgroundResource(background);
    }
    /**
     * 设置标题栏的背景颜色
     *
     * @param values
     */
    public void setTitleBarBackgroundAlpha(float values) {
        titleGroup.setAlpha(values);
    }

    /**
     * 设置返回键的图标
     *
     * @param res
     */
    public void setBackBtnIcon(@DrawableRes final int res) {
        titleBack.setImageResource(res);
    }

    /**
     * 添加右侧按钮
     *
     * @param view
     */
    public void addRightView(@NonNull View view) {
        addViewEvent(titleRight, view);
    }

    /**
     * 添加右侧按钮
     *
     * @param view
     */
    public void addLeftView(@NonNull View view) {
        addViewEvent(titleLeft, view);
    }

    /**
     * @param parent
     * @param child
     */
    private void addViewEvent(@NonNull LinearLayout parent, @NonNull View child) {
        if (parent.getChildCount() < 3 && !(child instanceof ViewGroup)) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT);
            params.weight = 1;
            child.setLayoutParams(params);
            parent.addView(child);
        }
    }

    /**
     * 清除不需要的按钮
     */
    public void removeLeftViewDefaultChild(){
        if (null != titleBack)titleLeft.removeView(titleBack);
    }
}

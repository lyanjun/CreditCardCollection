package com.lyan.tools.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 底部选择栏
 * Created by liyanjun on 2017/7/7.
 */

public class BottomBar extends LinearLayout implements RadioButton.OnCheckedChangeListener {
    public static final int INDEX_FIRST = 0;
    private List<RadioButton> radioButtons = new ArrayList<>();//单选按钮的集合
    private ColorStateList textColor;//文字的颜色
    private Context mContext;//上下文
    private int indexCount;//计数器

    public BottomBar(Context context) {
        this(context, null);
    }


    public BottomBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        setOrientation(HORIZONTAL);//设置子控件的排列方式
    }

    /**
     * 设置字体的颜色
     *
     * @param textColorRes
     */
    public final void setItemTextColor(@ColorRes int textColorRes) {
        this.textColor = ContextCompat.getColorStateList(mContext, textColorRes);
    }

    /**
     * 添加子项
     *
     * @param backgroundRes
     * @param text
     */
    public final void addItem(@DrawableRes int backgroundRes, String text) {
        RadioButton child = new RadioButton(mContext);
        child.setText(text);//设置显示文字
        Drawable drawable = ContextCompat.getDrawable(mContext, backgroundRes);
        if (null != drawable)
            drawable.setBounds(0, 0, 45, 45);
        child.setCompoundDrawables(null, drawable, null, null);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            child.setButtonDrawable(null);//无按钮背景
        } else {
            try {
                Field field = child.getClass().getSuperclass().getDeclaredField("mButtonDrawable");
                field.setAccessible(true);
                field.set(child, null);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        child.setGravity(Gravity.CENTER_HORIZONTAL);//水平居中
        child.setTextColor(textColor);//设置字体颜色
        child.setTextSize(TypedValue.COMPLEX_UNIT_SP,13);
        LinearLayout.LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params.weight = 1;
        params.gravity = Gravity.CENTER_VERTICAL;
        child.setLayoutParams(params);
        child.setOnCheckedChangeListener(this);
        child.setTag(indexCount);
        indexCount++;
        radioButtons.add(child);
        addView(child);
    }

    /**
     * 单选按钮的选择时间
     *
     * @param buttonView
     * @param isChecked
     */
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (!isChecked) {
            return;
        }else {
            if (null != itemSelectedListener)
                itemSelectedListener.onBottomBarItemSelected((int) buttonView.getTag());
        }
        //单选
        for (int i = 0; i < radioButtons.size(); i++) {
            if (buttonView != radioButtons.get(i)) {
                radioButtons.get(i).setChecked(false);
            }
        }
    }

    /**
     * 选中指点的单选按钮
     */
    public void checkedItem(int position) {
        //单选
        for (int i = 0; i < radioButtons.size(); i++) {
            RadioButton child = radioButtons.get(i);
            int tag = (int) child.getTag();
            if (tag == position) {
                child.setChecked(true);
                return;
            }
        }
    }

    /**
     * 选择回调
     */
    public interface OnBottomBarItemSelectedListener {
        void onBottomBarItemSelected(int position);
    }

    /**
     * 回调参数
     */
    private OnBottomBarItemSelectedListener itemSelectedListener;

    /**
     * 设置监听
     *
     * @param itemSelectedListener
     */
    public void setOnBottomBarItemSelectedListener(OnBottomBarItemSelectedListener itemSelectedListener) {
        this.itemSelectedListener = itemSelectedListener;
    }
}

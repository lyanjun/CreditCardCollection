package com.bank.creditcardcollection.utils;

import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import java.util.ArrayList;
import java.util.List;

/**
 * 单选帮助类
 * Created by liyanjun on 2017/7/31.
 */

public class RadioHelper<R extends ViewGroup> implements RadioButton.OnCheckedChangeListener {
    /**
     * 接口回调
     */
    public interface OnCheckedChangeListener {
        void onCheckedChanged(int parentID, String values);
    }
    /**
     * 属性
     */
    private R mParent;//按钮的容器
    private SparseArray<String> mValues;//值
    private OnCheckedChangeListener checkedChangeListener;
    private final List<RadioButton> radioButtons = new ArrayList<>();//储存单选按钮的容器

    public RadioHelper(R parent, SparseArray<String> values) {
        this.mParent = parent;
        this.mValues = values;
        setRadioButtons();
        setRadioValues();
//        log();
    }

    private void log() {
        for (int i = 0; i < radioButtons.size(); i++) {
            RadioButton child = radioButtons.get(i);
            Log.i("tag" , String.valueOf(child.getTag()) );
        }
    }

    /**
     * 设置单选按钮的选值
     */
    private void setRadioValues() {
        for (int i = 0; i < radioButtons.size(); i++) {
            RadioButton child = radioButtons.get(i);
            child.setOnCheckedChangeListener(this);//设置监听
            child.setTag(mValues.keyAt(i));//设置标记
        }
    }

    /**
     * 获取单选按钮(此方法默认为直接嵌套子控件，也就是容器中没有类型为ViewGroup的控件)
     */
    private void setRadioButtons() {
        int childCount = mParent.getChildCount();//获取子控件的个数
        for (int i = 0; i < childCount; i++) {
            View child = mParent.getChildAt(i);//获取子控件
            if (isRadioButton(child)) {
                radioButtons.add((RadioButton) child);
            }
        }
    }

    /**
     * 判断子控件是否为单选按钮
     *
     * @param childView
     * @return
     */
    private boolean isRadioButton(View childView) {
        return childView instanceof RadioButton;
    }

    /**
     * 选择监听
     *
     * @param buttonView
     * @param isChecked
     */
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (!isChecked) {
            return;
        } else {
            if (null != checkedChangeListener)
                checkedChangeListener.onCheckedChanged(mParent.getId(),String.valueOf(buttonView.getTag()));
        }
        //单选
        for (int i = 0; i < radioButtons.size(); i++) {
            if (buttonView != radioButtons.get(i)) {
                radioButtons.get(i).setChecked(false);
            }
        }
    }

    /**
     * 设置监听
     * @param checkedChangeListener
     */
    public void addCheckedChangeListener(OnCheckedChangeListener checkedChangeListener) {
        this.checkedChangeListener = checkedChangeListener;
    }
}

package com.bank.creditcardcollection.weight.dialog;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;

import com.bigkoo.pickerview.TimePickerView;
import com.lyan.tools.R;
import com.lyan.tools.entity.SmallDate;
import com.lyan.tools.utils.DateUtils;
import com.lyan.tools.utils.DrawUtils;

import java.util.Calendar;
import java.util.Date;

/**
 * 日期选择弹窗
 * Created by liyanjun on 2017/8/1.
 */

public class DateDialog implements View.OnClickListener {

    /**
     * 接口回调
     */
    public interface OnSelectDateListener {
        void afterDateSelected(int id, Date selectDate);
    }

    /**
     * 属性
     */
    private TimePickerView dateView;
    private int id;
    private OnSelectDateListener dateListener;

    public DateDialog(Context mContext, int tagID) {
        this.id = tagID;
        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        startDate.set(1900, 0, 1);//开始
        Calendar endDate = Calendar.getInstance();
        endDate.set(2100, 11, 31);//结束
        dateView = new TimePickerView.Builder(mContext, (date, v) -> {
            if (null != dateListener) dateListener.afterDateSelected(id, date);
        })
                .setType(new boolean[]{true, true, true, false, false, false})
                .setLabel("年", "月", "日", "", "", "")
                .setDividerColor(Color.DKGRAY)
                .setContentSize(18)
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setLayoutRes(R.layout.custom_date_select, this::addCustomViewListener)
                .isCenterLabel(false)
                .isCyclic(true)
                .isDialog(true)
                .build();
    }

    /**
     * 给子控件设置监听
     *
     * @param view
     */
    private void addCustomViewListener(View view) {
        Button cancelBtn = (Button) view.findViewById(R.id.iv_cancel);
        Button confirmBtn = (Button) view.findViewById(R.id.tv_finish);
        cancelBtn.setOnClickListener(this);
        confirmBtn.setOnClickListener(this);
    }

    /**
     * 显示弹窗
     */
    public void show() {
        dateView.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_cancel:
                break;
            case R.id.tv_finish:
                dateView.returnData();
                break;
        }
        dateView.dismiss();
    }

    /**
     * 添加时间监听
     * @param dateListener
     * @return
     */
    public DateDialog addDateListener(OnSelectDateListener dateListener) {
        this.dateListener = dateListener;
        return this;
    }

    /**
     * 设置时间为当前时间
     */
    public void setNowData(){
        SmallDate smallDate = DateUtils.getTodayDate();
        Calendar setDate = Calendar.getInstance();
        setDate.set(smallDate.getYear(),smallDate.getMonth() - 1,smallDate.getDay());
        dateView.setDate(setDate);
    }
}

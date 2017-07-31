package com.bank.creditcardcollection.weight.view.listener;

import android.support.v4.view.ViewPager;

/**
 * ViewPager选中后回调
 * Created by liyanjun on 2017/7/31.
 */

public class PageSelectedHelper implements ViewPager.OnPageChangeListener{
    public interface OnPageSelectedListener{
        void onPageSelected(int position);
    }
    private OnPageSelectedListener pageSelectedListener;

    public PageSelectedHelper(OnPageSelectedListener pageSelectedListener) {
        this.pageSelectedListener = pageSelectedListener;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
       pageSelectedListener.onPageSelected(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}

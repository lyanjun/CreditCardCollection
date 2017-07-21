package com.bank.creditcardcollection.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.bank.creditcardcollection.weight.view.level.LevelView;

import java.util.ArrayList;

/**
 * 新增申请
 * Created by liyanjun on 2017/7/21.
 */

public class ApplyInfoAdapter extends PagerAdapter{

    private ArrayList<LevelView> levelViews;//数据源

    public ApplyInfoAdapter(ArrayList<LevelView> levelViews) {
        this.levelViews = levelViews;
    }

    @Override
    public int getCount() {
        return levelViews.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LevelView levelView = levelViews.get(position);
        container.addView(levelView);
        return levelView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
       container.removeView(levelViews.get(position));
    }
}

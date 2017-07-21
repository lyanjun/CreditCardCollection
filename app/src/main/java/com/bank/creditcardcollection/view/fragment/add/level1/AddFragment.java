package com.bank.creditcardcollection.view.fragment.add.level1;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.SparseIntArray;
import android.widget.ImageView;

import com.bank.creditcardcollection.R;
import com.bank.creditcardcollection.adapter.ApplyInfoAdapter;
import com.bank.creditcardcollection.view.fragment.base.BaseWithOutBackFragment;
import com.bank.creditcardcollection.weight.view.level.LevelFiveView;
import com.bank.creditcardcollection.weight.view.level.LevelFourView;
import com.bank.creditcardcollection.weight.view.level.LevelOneView;
import com.bank.creditcardcollection.weight.view.level.LevelThreeView;
import com.bank.creditcardcollection.weight.view.level.LevelTwoView;
import com.bank.creditcardcollection.weight.view.level.LevelView;

import java.util.ArrayList;

import butterknife.BindView;


/**
 * 我的界面
 * Created by liyanjun on 2017/7/19.
 */

public class AddFragment extends BaseWithOutBackFragment implements ViewPager.OnPageChangeListener{

    @BindView(R.id.apply_indicate)
    ImageView applyIndicate;//指示器
    @BindView(R.id.apply_inter_phase)
    ViewPager applyInterPhase;//滑动视图
    private LevelView levelOneView, levelTwoView, levelThreeView, levelFourView, leve1FiveView;
    private SparseIntArray drawResID;
    private ArrayList<LevelView> levelViews;//数据源
    private ApplyInfoAdapter applyInfoAdapter;//数据适配器

    /**
     * 获取对象
     *
     * @return
     */
    public static AddFragment newInstance() {
        Bundle args = new Bundle();
        AddFragment fragment = new AddFragment();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * 设置布局
     *
     * @return
     */
    @Override
    protected int getLayoutID() {
        return R.layout.fragment_add;
    }

    /**
     * 设置标题
     */
    @Override
    protected void setTitleBar() {
        super.setTitleBar();
        mTitleBar.setTitle(R.string.clms_add);
    }

    /**
     * 设置功能
     */
    @Override
    protected void setFunction() {
        super.setFunction();
        initTabImage();//初始化指示器图片
        initItemView();//初始化选项
        applyInfoAdapter = new ApplyInfoAdapter(levelViews);//创建适配器
        applyInterPhase.setAdapter(applyInfoAdapter);//绑定适配器
        applyInterPhase.addOnPageChangeListener(this);

    }

    /**
     * 初始化指示器图片
     */
    private void initTabImage() {
        drawResID = new SparseIntArray(5);//设置容器为5
        drawResID.append(0, R.drawable.apply_info_level_one);
        drawResID.append(1, R.drawable.apply_info_level_two);
        drawResID.append(2, R.drawable.apply_info_level_three);
        drawResID.append(3, R.drawable.apply_info_level_four);
        drawResID.append(4, R.drawable.apply_info_level_five);
    }

    /**
     * 初始化填写步骤的控件
     */
    private void initItemView() {
        levelViews = new ArrayList<>();
        levelViews.add(levelOneView = new LevelOneView(mContext));
        levelViews.add(levelTwoView = new LevelTwoView(mContext));
        levelViews.add(levelThreeView = new LevelThreeView(mContext));
        levelViews.add(levelFourView = new LevelFourView(mContext));
        levelViews.add(leve1FiveView = new LevelFiveView(mContext));
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        applyIndicate.setImageResource(drawResID.get(position));//设置显示的图片
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}

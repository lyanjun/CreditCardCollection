package com.bank.creditcardcollection.view.fragment.add.level1;

import android.app.Activity;
import android.content.Context;
import android.util.SparseIntArray;

import com.bank.creditcardcollection.R;
import com.bank.creditcardcollection.base.BaseRxPresenter;
import com.bank.creditcardcollection.constant.Constant;
import com.bank.creditcardcollection.model.entity.ApplyInfo;
import com.bank.creditcardcollection.net.retrofit.HttpUtils;
import com.bank.creditcardcollection.weight.view.apply.level.Level;
import com.bank.creditcardcollection.weight.view.apply.level.LevelFiveView;
import com.bank.creditcardcollection.weight.view.apply.level.LevelFourView;
import com.bank.creditcardcollection.weight.view.apply.level.LevelOneView;
import com.bank.creditcardcollection.weight.view.apply.level.LevelThreeView;
import com.bank.creditcardcollection.weight.view.apply.level.LevelTwoView;
import com.bank.creditcardcollection.weight.view.apply.level.LevelView;
import com.bank.creditcardcollection.weight.view.apply.listener.LevelApplyInfoListener;
import com.bank.creditcardcollection.weight.view.apply.listener.LevelSetMessageListener;
import com.bank.creditcardcollection.weight.view.apply.listener.LevelStartActivityListener;
import com.google.gson.Gson;
import com.lyan.tools.utils.DateUtils;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

/**
 * MVP
 * Created by liyanjun on 2017/7/27.
 */

public class AddPresenter extends BaseRxPresenter<AddContract.View> implements AddContract.Presenter,
        LevelApplyInfoListener, LevelSetMessageListener, LevelStartActivityListener {

    private final ApplyInfo commitInfo = new ApplyInfo();
    private LevelView levelOneView, levelTwoView, levelThreeView, levelFourView, levelFiveView;
    private final SparseIntArray drawResID = new SparseIntArray(5);//设置容器为5;
    private ArrayList<LevelView> levelViews = new ArrayList<>(5);//数据源

    public AddPresenter(AddContract.View view) {
        super(view);
    }

    /**
     * 初始化
     */
    @Override
    public void initData() {
        initTabImage();//初始化指示器图片
        initItemView(mView.provideContext());//初始化选项
        setItemViewListener();//设置选项界面的监听事件
        mView.getLevelPageTabs(drawResID);
        mView.getLevelPageViews(levelViews);
    }

    /**
     * 设置监听事件
     */
    private void setItemViewListener() {
        //用来控制翻页
        levelOneView.setApplyInfoListener(this);
        levelTwoView.setApplyInfoListener(this);
        levelThreeView.setApplyInfoListener(this);
        levelFourView.setApplyInfoListener(this);
        levelFiveView.setApplyInfoListener(this);
        //用来监听页面展示信息的变化
        levelOneView.setSetMessageListener(this);
        levelTwoView.setSetMessageListener(this);
        levelThreeView.setSetMessageListener(this);
        levelFourView.setSetMessageListener(this);
        levelFiveView.setSetMessageListener(this);
        //跳转界面
        levelFourView.setStartActivityListener(this);
    }

    /**
     * 初始化指示器图片
     */
    private void initTabImage() {
        drawResID.append(0, R.drawable.apply_info_level_one);
        drawResID.append(1, R.drawable.apply_info_level_two);
        drawResID.append(2, R.drawable.apply_info_level_three);
        drawResID.append(3, R.drawable.apply_info_level_four);
        drawResID.append(4, R.drawable.apply_info_level_five);
    }

    /**
     * 初始化填写步骤的控件
     */
    private void initItemView(Context mContext) {
        levelViews.add(levelOneView = new LevelOneView(mContext));
        levelViews.add(levelTwoView = new LevelTwoView(mContext));
        levelViews.add(levelThreeView = new LevelThreeView(mContext));
        levelViews.add(levelFourView = new LevelFourView(mContext));
        levelViews.add(levelFiveView = new LevelFiveView(mContext));
    }

    @Override
    public void nextStep(int position) {
        mView.nextStep(position);
    }

    @Override
    public void lastStep(int position) {
        mView.lastStep(position);
    }

    /**
     * 提交
     */
    @Override
    public void commit() {
        commitInfo.setCreatetime(DateUtils.getTodayStr(DateUtils.FORMAT_DATE_TIME));
        commitInfo.setStatus(Constant.STATE_1);
        commitInfo.setUsercode("fanjian");
        Logger.t("提交的内容").i(commitInfo.toString());
        HttpUtils.postAddApplyResult(new Gson().toJson(commitInfo))
                .doFinally(this::resetApplyData)//订阅事件结束后重置
                .subscribe(s -> Logger.t("成功").w(s),
                        throwable -> Logger.t("失败").w(throwable.getMessage()));
//        resetApplyData();
    }

    /**
     * 组合选中的数据
     *
     * @param pageNo
     * @param changeInfo
     */
    @Override
    public void levelInfoChanged(int pageNo, ApplyInfo changeInfo) {
        switch (pageNo) {
            case Level.LEVEL1://第一页
                break;
            case Level.LEVEL2://第二页
                commitInfo.setLevel2Info(changeInfo);
                break;
            case Level.LEVEL3://第三页
                commitInfo.setLevel3Info(changeInfo);
                break;
            case Level.LEVEL4://第四页
                break;
            case Level.LEVEL5://第五页
                break;
        }
    }

    /**
     * 清空数据（重置前:null,重置后:""）
     */
    @Override
    public void resetApplyData() {
        for (int i = 0; i < levelViews.size(); i++) {
            levelViews.get(i).resetView();//清空填写的数据
        }
    }

    /**
     * 释放资源
     */
    @Override
    public void onDestroy() {
        for (int i = 0; i < levelViews.size(); i++) {
            levelViews.get(i).onDestroy();//销毁资源
        }
        super.onDestroy();
    }

    /**
     * 启动界面
     *
     * @param clazz
     * @param requestCode
     * @param <T>
     */
    @Override
    public <T extends Activity> void startActivity(Class<T> clazz, int requestCode) {
        mView.startActivityForResultInLevel(clazz, requestCode);
    }
}

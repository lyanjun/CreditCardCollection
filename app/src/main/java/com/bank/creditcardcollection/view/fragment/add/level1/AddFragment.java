package com.bank.creditcardcollection.view.fragment.add.level1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.SparseIntArray;
import android.widget.ImageView;

import com.bank.creditcardcollection.R;
import com.bank.creditcardcollection.adapter.ApplyInfoAdapter;
import com.bank.creditcardcollection.constant.Constant;
import com.bank.creditcardcollection.view.fragment.base.BaseWithOutBackFragment;
import com.bank.creditcardcollection.weight.view.apply.level.LevelView;
import com.bank.creditcardcollection.weight.view.listener.PageSelectedHelper;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

import butterknife.BindView;


/**
 * 我的界面
 * Created by liyanjun on 2017/7/19.
 */

public class AddFragment extends BaseWithOutBackFragment implements AddContract.View<AddPresenter> {

    @BindView(R.id.apply_indicate)
    ImageView applyIndicate;//指示器
    @BindView(R.id.apply_inter_phase)
    ViewPager applyInterPhase;//滑动视图
    private ArrayList<LevelView> levelViews;//数据源
    private SparseIntArray drawResID;//图片资源
    private AddPresenter mAddPresenter;

    /**
     * 设置功能
     */
    @Override
    protected void setFunction() {
        super.setFunction();
        new AddPresenter(this);//创建控制器
        mAddPresenter.initData();//初始化
        ApplyInfoAdapter applyInfoAdapter = new ApplyInfoAdapter(levelViews);
        applyInterPhase.setAdapter(applyInfoAdapter);//绑定适配器
        applyInterPhase.addOnPageChangeListener(new PageSelectedHelper(
                position -> applyIndicate.setImageResource(drawResID.get(position))));//设置显示的图片
    }

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
     * 获取控制器
     *
     * @param presenter
     */
    @Override
    public void setPresenter(AddPresenter presenter) {
        this.mAddPresenter = presenter;
    }

    /**
     * 下一步
     *
     * @param position
     */
    @Override
    public void nextStep(int position) {
        applyInterPhase.setCurrentItem(position);
    }

    /**
     * 上一步
     *
     * @param position
     */
    @Override
    public void lastStep(int position) {
        applyInterPhase.setCurrentItem(position);
    }

    /**
     * 提供上下文
     *
     * @return
     */
    @Override
    public Context provideContext() {
        return mContext;
    }

    /**
     * 获取选项页数
     *
     * @param views
     */
    @Override
    public void getLevelPageViews(ArrayList<LevelView> views) {
        this.levelViews = views;
    }

    /**
     * 获取指示器图片资源
     *
     * @param resIDs
     */
    @Override
    public void getLevelPageTabs(SparseIntArray resIDs) {
        this.drawResID = resIDs;
    }

    /**
     * 跳转界面
     * @param clazz
     * @param requestCode
     * @param <T>
     */
    @Override
    public <T extends Activity> void startActivityForResultInLevel(Class<T> clazz, int requestCode) {
        if (Constant.REQUEST_NONE == requestCode){//无返回跳转
            startActivity(new Intent(mActivity,clazz));
        }else {
            startActivityForResult(new Intent(mActivity,clazz),requestCode);
        }
    }

    /**
     * 返回结果
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Logger.i("返回当前界面");
    }

    /**
     * 销毁
     */
    @Override
    public void onDestroy() {
        mAddPresenter.onDestroy();
        super.onDestroy();
    }
}

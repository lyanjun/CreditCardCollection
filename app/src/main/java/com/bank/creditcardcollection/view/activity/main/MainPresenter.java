package com.bank.creditcardcollection.view.activity.main;

import android.util.SparseArray;

import com.bank.creditcardcollection.R;
import com.bank.creditcardcollection.base.activity.CommonPresenter;
import com.bank.creditcardcollection.base.fragment.BaseGroupFragment;
import com.bank.creditcardcollection.constant.Constant;
import com.bank.creditcardcollection.view.fragment.add.AddGroupFragment;
import com.bank.creditcardcollection.view.fragment.calculator.CalculatorGroupFragment;
import com.bank.creditcardcollection.view.fragment.camera.CameraGroupFragment;
import com.bank.creditcardcollection.view.fragment.home.HomeGroupFragment;

import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;

/**
 * Created by liyanjun on 2017/8/4.
 */

public class MainPresenter extends CommonPresenter<MainContract.View> implements MainContract.Presenter{
    private final SparseArray<BaseGroupFragment> mFragments = new SparseArray<>(4);
    public MainPresenter(MainContract.View view) {
        super(view);
    }

    @Override
    public void initData() {
        initFragment((SupportActivity) mView);
    }
    /**
     * 设置显示的视图
     *
     */
    private  void initFragment(SupportActivity activity) {
        BaseGroupFragment firstFragment = activity.findFragment(HomeGroupFragment.class);
        if (firstFragment == null) {//判断栈中是否有该对象
            mFragments.append(Constant.HOME, HomeGroupFragment.newInstance());
            mFragments.append(Constant.CALCULATOR, CalculatorGroupFragment.newInstance());
            mFragments.append(Constant.CAMERA, CameraGroupFragment.newInstance());
            mFragments.append(Constant.MINE, AddGroupFragment.newInstance());
            //添加要加载的视图
            activity.loadMultipleRootFragment(R.id.frag_home, Constant.HOME,
                    mFragments.get(Constant.HOME),
                    mFragments.get(Constant.CALCULATOR),
                    mFragments.get(Constant.CAMERA),
                    mFragments.get(Constant.MINE));
        } else {//没有就取出栈中的像
            mFragments.put(Constant.HOME,firstFragment);
            mFragments.put(Constant.CALCULATOR,activity.findFragment(CalculatorGroupFragment.class));
            mFragments.put(Constant.CAMERA,activity.findFragment(CameraGroupFragment.class));
            mFragments.put(Constant.MINE,activity.findFragment(AddGroupFragment.class));
        }
        activity.setFragmentAnimator(new DefaultHorizontalAnimator());//设置切换动画
    }

    @Override
    public void showFragment(int position) {
        ((SupportActivity) mView).showHideFragment(mFragments.get(position));
    }
}

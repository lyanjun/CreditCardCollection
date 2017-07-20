package com.bank.creditcardcollection.view.activity;

import android.os.Bundle;
import android.util.SparseArray;

import com.bank.creditcardcollection.R;
import com.bank.creditcardcollection.constant.Constant;
import com.bank.creditcardcollection.view.fragment.base.BaseGroupFragment;
import com.bank.creditcardcollection.view.fragment.calculator.CalculatorGroupFragment;
import com.bank.creditcardcollection.view.fragment.camera.CameraGroupFragment;
import com.bank.creditcardcollection.view.fragment.home.HomeGroupFragment;
import com.bank.creditcardcollection.view.fragment.mine.MineGroupFragment;
import com.lyan.tools.activity.BaseActivity;
import com.lyan.tools.view.BottomBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;


/**
 * 主界面
 * Created by liyanjun on 2017/7/18.
 */
public class MainActivity extends BaseActivity{

    @BindView(R.id.bottom_bar)//底部界面切换栏
    BottomBar bottomBar;
    private SparseArray<BaseGroupFragment> mFragments = new SparseArray<>(4);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);//绑定
        initFragment();//初始化Fragment
        //设置顶部切换栏
        bottomBar.setItemTextColor(R.color.bottom_bar_text);//设置切换文字的字体颜色
        bottomBar.addItem(R.drawable.bottom_bar_home, getString(R.string.clms_home));//添加首页选项
        bottomBar.addItem(R.drawable.bottom_bar_calculator, getString(R.string.clms_calculator));//添加计算器选项
        bottomBar.addItem(R.drawable.bottom_bar_camera, getString(R.string.clms_camera));//添加相机选项
        bottomBar.addItem(R.drawable.bottom_bar_mine, getString(R.string.clms_mine));//添加我的选项
        bottomBar.checkedItem(BottomBar.INDEX_FIRST);//默认选择第一个
        bottomBar.setOnBottomBarItemSelectedListener(position -> showHideFragment(mFragments.get(position)));//设置选择监听
    }
    /**
     * 设置显示的视图
     *
     */
    private void initFragment() {
        BaseGroupFragment firstFragment = findFragment(HomeGroupFragment.class);
        if (firstFragment == null) {//判断栈中是否有该对象
            mFragments.append(Constant.HOME, HomeGroupFragment.newInstance());
            mFragments.append(Constant.CALCULATOR, CalculatorGroupFragment.newInstance());
            mFragments.append(Constant.CAMERA, CameraGroupFragment.newInstance());
            mFragments.append(Constant.MINE, MineGroupFragment.newInstance());
            //添加要加载的视图
            loadMultipleRootFragment(R.id.frag_home, Constant.HOME,
                    mFragments.get(Constant.HOME),
                    mFragments.get(Constant.CALCULATOR),
                    mFragments.get(Constant.CAMERA),
                    mFragments.get(Constant.MINE));
        } else {//没有就取出栈中的像
            mFragments.put(Constant.HOME,firstFragment);
            mFragments.put(Constant.CALCULATOR,findFragment(CalculatorGroupFragment.class));
            mFragments.put(Constant.CAMERA,findFragment(CameraGroupFragment.class));
            mFragments.put(Constant.MINE,findFragment(MineGroupFragment.class));
        }
        setFragmentAnimator(new DefaultHorizontalAnimator());//设置切换动画
    }
}

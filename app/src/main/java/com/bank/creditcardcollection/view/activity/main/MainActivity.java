package com.bank.creditcardcollection.view.activity.main;

import android.os.Bundle;
import android.util.SparseArray;

import com.bank.creditcardcollection.R;
import com.bank.creditcardcollection.constant.Constant;
import com.bank.creditcardcollection.base.activity.MvpBaseActivity;
import com.bank.creditcardcollection.base.fragment.BaseGroupFragment;
import com.bank.creditcardcollection.view.fragment.calculator.CalculatorGroupFragment;
import com.bank.creditcardcollection.view.fragment.camera.CameraGroupFragment;
import com.bank.creditcardcollection.view.fragment.add.AddGroupFragment;
import com.bank.creditcardcollection.view.fragment.home.HomeGroupFragment;
import com.lyan.tools.view.BottomBar;

import butterknife.BindView;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;


/**
 * 主界面
 * Created by liyanjun on 2017/7/18.
 */
public class MainActivity extends MvpBaseActivity<MainContract.Presenter> implements MainContract.View{

    @BindView(R.id.bottom_bar)//底部界面切换栏
    BottomBar bottomBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new MainPresenter(this);
        mPresenter.initData();
        //设置顶部切换栏
        bottomBar.setItemTextColor(R.color.bottom_bar_text);//设置切换文字的字体颜色
        bottomBar.addItem(R.drawable.bottom_bar_home, getString(R.string.clms_home));//添加首页选项
        bottomBar.addItem(R.drawable.bottom_bar_calculator, getString(R.string.clms_calculator));//添加计算器选项
        bottomBar.addItem(R.drawable.bottom_bar_camera, getString(R.string.clms_camera));//添加相机选项
        bottomBar.addItem(R.drawable.bottom_bar_mine, getString(R.string.clms_add));//添加我的选项
        bottomBar.checkedItem(BottomBar.INDEX_FIRST);//默认选择第一个
        bottomBar.setOnBottomBarItemSelectedListener(position -> mPresenter.showFragment(position));//设置选择监听
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        this.mPresenter = presenter;
    }
}

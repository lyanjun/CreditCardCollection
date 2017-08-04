package com.bank.creditcardcollection.view.activity.apply;

import android.os.Bundle;
import android.widget.ScrollView;

import com.bank.creditcardcollection.R;
import com.bank.creditcardcollection.base.activity.TitleActivity;

import butterknife.BindView;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

/**
 * 添加身份证 正反面 带有地理位置信息的合影
 * Created by liyanjun on 2017/8/2.
 */
public class ApplyAddImageActivity extends TitleActivity<ApplyAddImageContract.Presenter> implements ApplyAddImageContract.View{

    @BindView(R.id.root_view)
    ScrollView rootView;
    /**
     * 初始化
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_add_image);
        new ApplyAddImagePresenter(this);
        setViews();//设置控件
    }

    /**
     * 设置控件
     */
    private void setViews() {
        OverScrollDecoratorHelper.setUpOverScroll(rootView);//弹性滑动效果
    }

    @Override
    public void setPresenter(ApplyAddImageContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    /**
     * 设置标题
     */
    @Override
    protected void setTitleBar() {
        super.setTitleBar();
        mTitleBar.setTitle(getString(R.string.title_activity_add_image));
    }

    /**
     * 又返回按钮
     * @return
     */
    @Override
    protected boolean addBackBtn() {
        return true;
    }
}

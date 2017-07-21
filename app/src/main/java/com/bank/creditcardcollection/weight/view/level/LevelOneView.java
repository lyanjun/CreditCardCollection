package com.bank.creditcardcollection.weight.view.level;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ScrollView;

import com.bank.creditcardcollection.R;

import butterknife.BindView;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

/**
 * 第一步
 * Created by liyanjun on 2017/7/21.
 */

public class LevelOneView extends LevelView{

    public LevelOneView(Context context) {
        super(context);
    }

    public LevelOneView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LevelOneView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @BindView(R.id.scroll_root)
    ScrollView rootView;

    /**
     * 第一步的布局
     * @return
     */
    @Override
    protected int setContentView() {
        return R.layout.view_apply_info_level_one;
    }

    /**
     * 设置功能
     */
    @Override
    protected void setFunction() {
        OverScrollDecoratorHelper.setUpOverScroll(rootView);//弹性滑动效果
    }
}

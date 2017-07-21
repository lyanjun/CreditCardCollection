package com.bank.creditcardcollection.weight.view.level;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.bank.creditcardcollection.R;

/**
 * 第一步
 * Created by liyanjun on 2017/7/21.
 */

public class LevelFiveView extends LevelView{
    public LevelFiveView(Context context) {
        super(context);
    }

    public LevelFiveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LevelFiveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected int setContentView() {
        return R.layout.view_apply_info_level_five;
    }

    @Override
    protected void setFunction() {

    }
}

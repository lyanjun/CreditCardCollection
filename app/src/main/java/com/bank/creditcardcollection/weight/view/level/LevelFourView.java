package com.bank.creditcardcollection.weight.view.level;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.bank.creditcardcollection.R;

/**
 * 第一步
 * Created by liyanjun on 2017/7/21.
 */

public class LevelFourView extends LevelView{
    public LevelFourView(Context context) {
        super(context);
    }

    public LevelFourView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LevelFourView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected int setContentView() {
        return R.layout.view_apply_info_level_four;
    }

    @Override
    protected void setFunction() {

    }
}

package com.bank.creditcardcollection.weight.view.apply.level;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bank.creditcardcollection.R;
import com.bank.creditcardcollection.constant.Constant;
import com.bank.creditcardcollection.view.activity.apply.ApplyAddImageActivity;
import com.lyan.tools.utils.DateUtils;
import com.lyan.tools.utils.ViewTextUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

/**
 * 第一步
 * Created by liyanjun on 2017/7/21.
 */

public class LevelFourView extends LevelView implements View.OnClickListener{
    public LevelFourView(Context context) {
        super(context);
    }

    public LevelFourView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LevelFourView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @BindView(R.id.scroll_root)
    ScrollView rootView;//最外层滑动布局
    @BindView(R.id.input_next_btn)
    Button nextStepBtn;//下一步按钮
    @BindView(R.id.input_last_btn)
    Button lastStepBtn;//上一步按钮
    @Override
    protected int setContentView() {
        return R.layout.view_apply_info_level_four;
    }
    //签名日期
    @BindViews({R.id.sign1_date_year,R.id.sign1_date_month,R.id.sign1_date_day})
    List<TextView> sign1TextDateTv;
    @BindViews({R.id.sign2_date_year,R.id.sign2_date_month,R.id.sign2_date_day})
    List<TextView> sign2TextDateTv;
    //添加图片
    @BindView(R.id.apply_image)
    Button addApplyImagesBtn;
    @Override
    protected void setFunction() {
        OverScrollDecoratorHelper.setUpOverScroll(rootView);//弹性滑动效果
        nextStepBtn.setOnClickListener(this);//设置下一步操作
        lastStepBtn.setOnClickListener(this);//设置上一步操作
        addApplyImagesBtn.setOnClickListener(this);//添加申请图片等的按钮
        //设置签名日期
        String dateStr = DateUtils.getTodayStr(DateUtils.FORMAT_DATE);
        ViewTextUtils.setDateToView(dateStr,sign1TextDateTv);
        ViewTextUtils.setDateToView(dateStr,sign2TextDateTv);
    }

    @Override
    public void resetView() {

    }

    /**
     * 点击事件
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.input_next_btn://下一步
                applyInfoListener.nextStep(Level.LEVEL5);
                break;
            case R.id.input_last_btn://上一步
                applyInfoListener.lastStep(Level.LEVEL3);
                break;
            case R.id.apply_image://跳转申请图片的界面
//                startActivity(ApplyAddImageActivity.class, Constant.REQUEST_NONE);
                startActivity(ApplyAddImageActivity.class, 100);
                break;
        }
    }
}

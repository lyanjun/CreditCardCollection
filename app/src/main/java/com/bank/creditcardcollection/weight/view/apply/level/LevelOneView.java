package com.bank.creditcardcollection.weight.view.apply.level;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bank.creditcardcollection.R;
import com.lyan.tools.utils.FormatUtils;
import com.lyan.tools.view.BoxEditText;
import com.lyan.tools.view.InputBox;

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
    //绑定控件
    @BindView(R.id.scroll_root)
    ScrollView rootView;//最外层滑动布局
    @BindView(R.id.input_card_hint_text)
    TextView hintTextApplyFor;//申请卡提示文字
    @BindView(R.id.level_one_hint_text)
    TextView hintTextAgreement;//协议提示文字
    @BindView(R.id.input_next_btn)
    Button nextStepBtn;//下一步按钮
    @BindView(R.id.level_1_card_alias)
    BoxEditText inputCardAlias;//卡种简称
    @BindView(R.id.level_1_card_code)
    InputBox inputCardCode;//卡种代码
    @BindView(R.id.level_1_select_one)
    RadioGroup inputSelectGroupOne;//选项一
    @BindView(R.id.level_1_select_two)
    RadioGroup inputSelectGroupTwo;//选项二
    @BindView(R.id.level_1_club_card)
    InputBox inputClubCard;//卡种代码
    @BindView(R.id.level_1_select_three)
    RadioGroup inputSelectGroupThree;//选项三
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
        //设置部分字体特殊的文本框
        hintTextApplyFor.setText(FormatUtils.getHtml(getResources().getString(R.string.apply_for_card_input_hint)));
        hintTextAgreement.setText(FormatUtils.getHtml(getResources().getString(R.string.apply_for_custom_agreement_hint)));
        //设置下一步操作
        nextStepBtn.setOnClickListener(v -> applyInfoListener.nextStep(Level.LEVEL2));
    }

    @Override
    public void resetView() {

    }
}

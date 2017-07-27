package com.bank.creditcardcollection.weight.view.level;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Html;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

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
    ScrollView rootView;//最外层滑动布局
    @BindView(R.id.input_card_hint_text)
    TextView hintTextApplyFor;//申请卡提示文字
    @BindView(R.id.level_one_hint_text)
    TextView hintTextAgreement;//协议提示文字
    @BindView(R.id.input_next_btn)
    Button nextStepBtn;//下一步按钮
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
//        hintTextApplyFor.setText(Html.fromHtml(mContext.getString(R.string.apply_for_card_input_hint)));
        hintTextAgreement.setText(Html.fromHtml("点击‘下一步’意味着您已阅、知晓并同意遵守" + "<font color = '#2F20FF'>"+
                "《长春农村商业银行信用卡章程》《长春农村商业银行性用卡领用合约》" +"</font>"));
        //设置下一步操作
        nextStepBtn.setOnClickListener(v -> applyInfoListener.nextStep(Level.LEVEL2));
    }

    @Override
    public void resetView() {

    }
}

package com.bank.creditcardcollection.weight.view.apply.level;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.bank.creditcardcollection.R;
import com.bank.creditcardcollection.constant.information.InformationUtils;
import com.bank.creditcardcollection.model.entity.ApplyInfo;
import com.bank.creditcardcollection.utils.RadioHelper;
import com.bank.creditcardcollection.weight.view.apply.help.EditTextHelper;
import com.lyan.tools.utils.ToastUtils;
import com.lyan.tools.utils.ViewTextUtils;
import com.lyan.tools.view.BoxEditText;
import com.lyan.tools.view.InputBox;
import com.lyan.tools.weight.dialog.MineDialog;

import butterknife.BindView;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

/**
 * 第一步
 * Created by liyanjun on 2017/7/21.
 */

public class LevelFiveView extends LevelView implements View.OnClickListener ,MineDialog.OnConfirmListener{
    public LevelFiveView(Context context) {
        super(context);
    }

    public LevelFiveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LevelFiveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //提交信息
    private final ApplyInfo setInfo = new ApplyInfo();
    @BindView(R.id.scroll_root)
    ScrollView rootView;//最外层滑动布局
    @BindView(R.id.input_commit_btn)
    Button commitStepBtn;//提交按钮
    @BindView(R.id.input_last_btn)
    Button lastStepBtn;//上一步按钮
    //填写信息
    @BindView(R.id.level5_employee_number)
    InputBox inputApplyInfoEmployeeNumber;//员工号
    @BindView(R.id.level5_employee_phone)
    InputBox inputApplyInfoEmployeePhone;//员工手机号
    @BindView(R.id.level5_employee_organization_code)
    InputBox inputApplyInfoEmployeeOrganizationCode;//员工组织机构号
    @BindView(R.id.level5_employee_name)
    BoxEditText inputApplyInfoEmployeeName;//员工名称
    @BindView(R.id.level5_employee_organization)
    BoxEditText inputApplyInfoEmployeeOrganization;//员工组织名称
    @BindView(R.id.level5_relation)
    GridLayout inputApplyInfoEmployeeRelation;//员工关系
    RadioHelper<GridLayout> inputApplyEmployeeRelationHelper;//邮寄方式单选
    private MineDialog mineDialog;//弹窗
    @Override
    protected int setContentView() {
        return R.layout.view_apply_info_level_five;
    }

    @Override
    protected void setFunction() {
        mineDialog = new MineDialog(mContext).addConfirmListener(this);
        mineDialog.setTitleText(getResources().getString(R.string.level_five_apply_input_referrer_number));
        OverScrollDecoratorHelper.setUpOverScroll(rootView);//弹性滑动效果
        commitStepBtn.setOnClickListener(this);//设置提交操作
        lastStepBtn.setOnClickListener(this); //设置上一步操作
        inputApplyEmployeeRelationHelper = new RadioHelper<>(inputApplyInfoEmployeeRelation, InformationUtils.getInfo().getRecommendType());
        inputApplyEmployeeRelationHelper.addCheckedChangeListener((parentID, values) -> ToastUtils.shortToast(mContext,values));
        inputApplyInfoEmployeeNumber.addOnInputBoxClickListener(box -> mineDialog.show());//开启弹窗
        setFocusNone();//清除焦点
        setListener();//设置监听
    }

    /**
     * 设置监听
     */
    private void setListener() {
        inputApplyInfoEmployeeNumber.setOnTextChangeListener(message -> {});
        inputApplyInfoEmployeePhone.setOnTextChangeListener(message -> {});
        inputApplyInfoEmployeeOrganizationCode.setOnTextChangeListener(message -> {});
        inputApplyInfoEmployeeName.addTextChangedListener(new EditTextHelper(message -> {}));
        inputApplyInfoEmployeeOrganization.addTextChangedListener(new EditTextHelper(message -> {}));
    }

    /**
     * 清除焦点
     */
    private void setFocusNone() {
        ViewTextUtils.setFocusNone(inputApplyInfoEmployeeName);
        ViewTextUtils.setFocusNone(inputApplyInfoEmployeeOrganization);
        ViewTextUtils.setFocusNone(inputApplyInfoEmployeeNumber);
        ViewTextUtils.setFocusNone(inputApplyInfoEmployeePhone);
        ViewTextUtils.setFocusNone(inputApplyInfoEmployeeOrganizationCode);
    }

    /**
     * 重置
     */
    @Override
    public void resetView() {
        ViewTextUtils.setTextViewEmpty(inputApplyInfoEmployeeName);
        ViewTextUtils.setTextViewEmpty(inputApplyInfoEmployeeOrganization);
        ViewTextUtils.setTextViewEmpty(inputApplyInfoEmployeeNumber);
        ViewTextUtils.setTextViewEmpty(inputApplyInfoEmployeePhone);
        ViewTextUtils.setTextViewEmpty(inputApplyInfoEmployeeOrganizationCode);
        inputApplyEmployeeRelationHelper.clearChecked();
        mineDialog.clearText();
    }

    /**
     * 发送
     */
    private void sendToView() {
        setMessageListener.levelInfoChanged(Level.LEVEL5, setInfo);
    }
    /**
     * 点击事件
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.input_commit_btn://提交
                applyInfoListener.commit();
                break;
            case R.id.input_last_btn://上一步
                applyInfoListener.lastStep(Level.LEVEL4);
                break;
        }
    }

    /**
     * 获取输入内容
     * @param inputText
     */
    @Override
    public void getInputMessage(String inputText) {
        inputApplyInfoEmployeeNumber.setTextToBox(inputText);
        if (!TextUtils.isEmpty(inputText))selectReferrerMessagerFromSevviceListener.selectReferrerEmployeeNumberInfo(inputText);
    }

    /**
     * 进行网络请求
     */
    public interface SelectReferrerMessageFromServiceListener{
        void selectReferrerEmployeeNumberInfo(String selectCode);
    }
    private SelectReferrerMessageFromServiceListener selectReferrerMessagerFromSevviceListener;

    public void addSelectReferrerMessageFromServiceListener(SelectReferrerMessageFromServiceListener selectReferrerMessagerFromSevviceListener) {
        this.selectReferrerMessagerFromSevviceListener = selectReferrerMessagerFromSevviceListener;
    }
}

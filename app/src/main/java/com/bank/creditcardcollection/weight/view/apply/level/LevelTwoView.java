package com.bank.creditcardcollection.weight.view.apply.level;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Button;
import android.widget.ScrollView;

import com.bank.creditcardcollection.R;
import com.bank.creditcardcollection.constant.information.InformationUtils;
import com.bank.creditcardcollection.model.entity.ApplyInfo;
import com.bank.creditcardcollection.utils.RadioHelper;
import com.bank.creditcardcollection.weight.view.apply.help.EditTextHelper;
import com.lyan.tools.utils.ToastUtils;
import com.lyan.tools.view.BoxEditText;
import com.lyan.tools.view.InputBox;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

/**
 * 第一步
 * Created by liyanjun on 2017/7/21.
 */

public class LevelTwoView extends LevelView implements RadioHelper.OnCheckedChangeListener {
    public LevelTwoView(Context context) {
        super(context);
    }

    public LevelTwoView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LevelTwoView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //提交信息
    private final ApplyInfo setInfo = new ApplyInfo();
    //绑定控件
    @BindView(R.id.scroll_root)
    ScrollView rootView;//最外层滑动布局
    @BindView(R.id.input_next_btn)
    Button nextStepBtn;//下一步按钮
    @BindView(R.id.input_last_btn)
    Button lastStepBtn;//上一步按钮
    @BindView(R.id.chinese_name)
    BoxEditText inputChineseName;//中文姓名
    @BindView(R.id.nationality)
    BoxEditText inputNationality;//国籍
    @BindView(R.id.spelling)
    InputBox inputSpelling;//拼音姓名
    @BindView(R.id.certificate_code)
    InputBox inputIdCardCode;//证件号码
    @BindView(R.id.apply_user_phone)
    InputBox inputApplyUserPhone;//申请人手机号
    @BindView(R.id.level2_province)
    BoxEditText inputApplyUserProvince;//申请人所在的省
    @BindView(R.id.level2_city)
    BoxEditText inputApplyUserCity;//申请人所在的城市
    @BindView(R.id.level2_area)
    BoxEditText inputApplyUserArea;//申请人居住的区
    @BindView(R.id.level2_address)
    BoxEditText inputApplyUserAddress;//申请人居住地址
    @BindView(R.id.kinsfolk_name)
    BoxEditText inputApplyKinsfolkName;//亲属的姓名
    @BindView(R.id.kinsfolk_relation)
    GridLayout inputApplyKinsfolkRelation;//亲属关系
    @BindView(R.id.kinsfolk_phone_number)
    InputBox inputKinsfolkPhoneNumber;//亲属的联系方式
    @BindView(R.id.emergency_contact_name)
    BoxEditText inputApplyContactName;//紧急联系人姓名
    @BindView(R.id.emergency_contact_relation)
    GridLayout inputApplyContactRelation;//紧急联系人关系
    @BindView(R.id.emergency_contact_phone)
    InputBox inputApplyContactPhone;//紧急联系人联系方式
    //属性
    RadioHelper<GridLayout> inputApplyKinsfolkRelationHelper;//直系亲属选择
    RadioHelper<GridLayout> inputApplyContactRelationHelper;//紧急联系人选择

    @Override
    protected int setContentView() {
        return R.layout.view_apply_info_level_two;
    }

    @Override
    protected void setFunction() {
        OverScrollDecoratorHelper.setUpOverScroll(rootView);//弹性滑动效果
        nextStepBtn.setOnClickListener(v -> applyInfoListener.nextStep(Level.LEVEL3));//设置下一步操作
        lastStepBtn.setOnClickListener(v -> applyInfoListener.lastStep(Level.LEVEL1));//设置上一步操作
        textInputHandle();//文本输入操作
        checkInputHandle();//选择文本操作
    }

    /**
     * 选择输入操作
     */
    private void checkInputHandle() {
        inputApplyKinsfolkRelationHelper = new RadioHelper<>(inputApplyKinsfolkRelation, InformationUtils.getInfo().getKinsfolkRelation());//直系亲属关系
        inputApplyContactRelationHelper = new RadioHelper<>(inputApplyContactRelation, InformationUtils.getInfo().getInstancyRelation());//紧急联系人关系
        setRadioCheckListener();//设置监听
    }

    /**
     * 设置单选监听
     */
    private void setRadioCheckListener() {
        inputApplyKinsfolkRelationHelper.addCheckedChangeListener(this);
        inputApplyContactRelationHelper.addCheckedChangeListener(this);
    }

    /**
     * 文本输入操作
     */
    private void textInputHandle() {
        inputChineseName.addTextChangedListener(new EditTextHelper(this::setApplyChineseName));//设置填写姓名的监听
        inputNationality.addTextChangedListener(new EditTextHelper(this::setApplyNationality));//设置填写国籍的监听
        inputSpelling.setOnTextChangeListener(this::setApplySpelling);//设置拼音姓名的监听
        inputIdCardCode.setOnTextChangeListener(this::setApplyCertificate);//设置证件号
        inputApplyUserPhone.setOnTextChangeListener(this::setApplyUserPhoneNumber);//申请人手机号码
        inputApplyUserProvince.addTextChangedListener(new EditTextHelper(this::setApplyUserProvince));//居住的省
        inputApplyUserCity.addTextChangedListener(new EditTextHelper(this::setApplyUserCity));//居住的城市
        inputApplyUserArea.addTextChangedListener(new EditTextHelper(this::setApplyUserArea));//所在的区
        inputApplyUserAddress.addTextChangedListener(new EditTextHelper(this::setApplyUserAddress));//居住的详细地址
        inputApplyKinsfolkName.addTextChangedListener(new EditTextHelper(this::setApplyKinsfolkName));//设置亲属的姓名
        inputKinsfolkPhoneNumber.setOnTextChangeListener(this::setApplyKinsfolkPhoneNumber);//设置亲属的联系方式
        inputApplyContactName.addTextChangedListener(new EditTextHelper(this::setApplyContactName));//设置紧急联系人的姓名
        inputApplyContactPhone.setOnTextChangeListener(this::setApplyContactPhone);//设置紧急联系人的联系方式
    }

    /**
     * 设置紧急联系人的联系方式
     * @param contactPhone
     */
    private void setApplyContactPhone(String contactPhone) {
        setInfo.setEmergencycontacttel(contactPhone);
        sendToView();
    }

    /**
     * 设置紧急联系人的姓名
     * @param contactName
     */
    private void setApplyContactName(String contactName) {
        setInfo.setEmergencycontactname(contactName);
        sendToView();
    }

    /**
     * 设置亲属的联系方式
     *
     * @param kinsfolkPhoneNumber
     */
    private void setApplyKinsfolkPhoneNumber(String kinsfolkPhoneNumber) {
        setInfo.setFamilycontacttel(kinsfolkPhoneNumber);
        sendToView();
    }

    /**
     * 设置亲属的姓名
     *
     * @param kinsfolkName
     */
    private void setApplyKinsfolkName(String kinsfolkName) {
        setInfo.setFamilycontactname(kinsfolkName);
        sendToView();
    }

    /**
     * 设置居住的详细地址
     *
     * @param liveAddress
     */
    private void setApplyUserAddress(String liveAddress) {
        setInfo.setAddress(liveAddress);
        sendToView();
    }

    /**
     * 设置居住的区
     *
     * @param liveArea
     */
    private void setApplyUserArea(String liveArea) {
        setInfo.setArea(liveArea);
        sendToView();
    }

    /**
     * 设置居住的城市
     *
     * @param liveCity
     */
    private void setApplyUserCity(String liveCity) {
        setInfo.setCity(liveCity);
        sendToView();
    }


    /**
     * 设置居住的省
     *
     * @param liveProvince
     */
    private void setApplyUserProvince(String liveProvince) {
        setInfo.setProvince(liveProvince);
        sendToView();
    }

    /**
     * 申请人电话号码
     *
     * @param phoneNumber
     */
    private void setApplyUserPhoneNumber(String phoneNumber) {
        setInfo.setTel(phoneNumber);
        sendToView();
    }

    /**
     * 设置证件号码
     *
     * @param certificate
     */
    private void setApplyCertificate(String certificate) {
        setInfo.setIdno(certificate);
        sendToView();
    }

    /**
     * 设置拼音
     *
     * @param spelling
     */
    private void setApplySpelling(String spelling) {
        setInfo.setPinyin(spelling);
        sendToView();
    }

    /**
     * 设置国籍
     *
     * @param nationality
     */
    private void setApplyNationality(String nationality) {
        setInfo.setNation(nationality);
        sendToView();
    }

    /**
     * 设置中文姓名
     *
     * @param chineseName
     */
    private void setApplyChineseName(String chineseName) {
        setInfo.setName(chineseName);
        sendToView();

    }

    /**
     * 发送
     */
    private void sendToView() {
        setMessageListener.levelInfoChanged(Level.LEVEL2, setInfo);
    }

    /**
     * 重置界面内容
     */
    @Override
    public void resetView() {

    }

    /**
     * 单选监听
     *
     * @param parentID
     * @param values
     */
    @Override
    public void onCheckedChanged(int parentID, String values) {
        switch (parentID) {
            case R.id.kinsfolk_relation://直系亲属关系
                setInfo.setFamilycontactrel(values);
                break;
            case R.id.emergency_contact_relation://紧急联系人关系
                setInfo.setEmergencycontactrel(values);
                break;
        }
        sendToView();
    }
}

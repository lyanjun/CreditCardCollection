package com.bank.creditcardcollection.weight.view.apply.level;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bank.creditcardcollection.R;
import com.bank.creditcardcollection.constant.information.InformationUtils;
import com.bank.creditcardcollection.model.entity.ApplyInfo;
import com.bank.creditcardcollection.utils.RadioHelper;
import com.bank.creditcardcollection.weight.dialog.DateDialog;
import com.bank.creditcardcollection.weight.view.apply.help.EditTextHelper;
import com.bigkoo.pickerview.TimePickerView;
import com.bigkoo.pickerview.lib.WheelView;
import com.bigkoo.pickerview.listener.CustomListener;
import com.lyan.tools.utils.DateUtils;
import com.lyan.tools.utils.FormatUtils;
import com.lyan.tools.utils.ToastUtils;
import com.lyan.tools.utils.ViewTextUtils;
import com.lyan.tools.view.BoxEditText;
import com.lyan.tools.view.InputBox;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

/**
 * 第一步
 * Created by liyanjun on 2017/7/21.
 */

public class LevelTwoView extends LevelView implements RadioHelper.OnCheckedChangeListener, View.OnClickListener,
        DateDialog.OnSelectDateListener {
    public LevelTwoView(Context context) {
        super(context);
    }

    public LevelTwoView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LevelTwoView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @BindViews({R.id.title_chinese_name, R.id.title_kinsfolk_name, R.id.title_contact_name, R.id.title_nationality,
            R.id.title_spell_name, R.id.title_credentials_number, R.id.title_uesful_life, R.id.title_mobile_phone,
            R.id.title_kinsfolk_phone_number, R.id.title_contact_phone_number, R.id.title_home_address,
            R.id.title_kinsfolk_relation, R.id.title_contact_relation})
    List<TextView> titleTvs;
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
    //选择时间
    @BindView(R.id.level2_apply_card_validity)
    LinearLayout inputApplyCardValidity;//证件有效期
    @BindViews({R.id.date_uesful_life_year, R.id.date_uesful_life_month, R.id.date_uesful_life_day})
    List<TextView> dateUsefulLife;//使用有效期
    //属性
    RadioHelper<GridLayout> inputApplyKinsfolkRelationHelper;//直系亲属选择
    RadioHelper<GridLayout> inputApplyContactRelationHelper;//紧急联系人选择
    private DateDialog inputSelectDateDialog;


    @Override
    protected int setContentView() {
        return R.layout.view_apply_info_level_two;
    }

    @Override
    protected void setFunction() {
        OverScrollDecoratorHelper.setUpOverScroll(rootView);//弹性滑动效果
        setTitleText();//设置标题的文字
        setOnClickHandle();//设置选择日期的操作
        textInputHandle();//文本输入操作
        checkInputHandle();//选择文本操作
        setDialogHandle();//设置弹窗
    }

    /**
     * 设置弹窗
     */
    private void setDialogHandle() {
        inputSelectDateDialog = new DateDialog(mContext, 0).addDateListener(this);
    }

    /**
     * 设置选择日期的操作
     */
    private void setOnClickHandle() {
        inputApplyCardValidity.setOnClickListener(this);//开启选择日期的弹窗
        nextStepBtn.setOnClickListener(this);//设置下一步操作
        lastStepBtn.setOnClickListener(this);//设置上一步操作
    }

    /**
     * 设置标题的文字
     */
    private void setTitleText() {
        titleTvs.get(0).setText(FormatUtils.getHtml(getResources().getString(R.string.level_two_apply_chinese_name)));
        titleTvs.get(1).setText(FormatUtils.getHtml(getResources().getString(R.string.level_two_apply_chinese_name)));
        titleTvs.get(2).setText(FormatUtils.getHtml(getResources().getString(R.string.level_two_apply_chinese_name)));
        titleTvs.get(3).setText(FormatUtils.getHtml(getResources().getString(R.string.level_two_apply_nationality)));
        titleTvs.get(4).setText(FormatUtils.getHtml(getResources().getString(R.string.level_two_apply_spell_name)));
        titleTvs.get(5).setText(FormatUtils.getHtml(getResources().getString(R.string.level_two_apply_credentials_number)));
        titleTvs.get(6).setText(FormatUtils.getHtml(getResources().getString(R.string.level_two_apply_uesful_life)));
        titleTvs.get(7).setText(FormatUtils.getHtml(getResources().getString(R.string.level_two_apply_mobile_phone)));
        titleTvs.get(8).setText(FormatUtils.getHtml(getResources().getString(R.string.level_two_apply_mobile_phone)));
        titleTvs.get(9).setText(FormatUtils.getHtml(getResources().getString(R.string.level_two_apply_mobile_phone)));
        titleTvs.get(10).setText(FormatUtils.getHtml(getResources().getString(R.string.level_two_apply_home_address)));
        titleTvs.get(11).setText(FormatUtils.getHtml(getResources().getString(R.string.level_two_apply_relation)));
        titleTvs.get(12).setText(FormatUtils.getHtml(getResources().getString(R.string.level_two_apply_relation)));
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
     *
     * @param contactPhone
     */
    private void setApplyContactPhone(String contactPhone) {
        setInfo.setEmergencycontacttel(contactPhone);
        sendToView();
    }

    /**
     * 设置紧急联系人的姓名
     *
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

    /**
     * 点击事件
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.level2_apply_card_validity://选择证件的有效期
                inputSelectDateDialog.show();//开启弹窗
                break;
            case R.id.input_next_btn://下一步
                applyInfoListener.nextStep(Level.LEVEL3);
                break;
            case R.id.input_last_btn://上一步
                applyInfoListener.lastStep(Level.LEVEL1);
                break;
        }
    }

    /**
     * 选择日期的监听
     *
     * @param id
     * @param selectDate
     */
    @Override
    public void afterDateSelected(int id, Date selectDate) {
        String dateStr = DateUtils.getDateTimeStr(selectDate, DateUtils.FORMAT_DATE);
        switch (id) {
            case 0://证件有效期
                ViewTextUtils.setDateToView(dateStr,dateUsefulLife);
                setInfo.setIdvalidate(dateStr);
                sendToView();
                break;
        }
    }
}

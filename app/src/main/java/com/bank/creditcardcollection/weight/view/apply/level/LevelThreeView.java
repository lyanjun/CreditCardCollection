package com.bank.creditcardcollection.weight.view.apply.level;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.TransitionRes;
import android.support.v7.widget.GridLayout;
import android.text.TextUtils;
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
import com.lyan.tools.utils.DateUtils;
import com.lyan.tools.utils.FormatUtils;
import com.lyan.tools.utils.ViewTextUtils;
import com.lyan.tools.view.BoxEditText;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

/**
 * 第一步
 * Created by liyanjun on 2017/7/21.
 */

public class LevelThreeView extends LevelView implements RadioHelper.OnCheckedChangeListener, View.OnClickListener ,
DateDialog.OnSelectDateListener{
    public LevelThreeView(Context context) {
        super(context);
    }

    public LevelThreeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LevelThreeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //提交信息
    private final ApplyInfo setInfo = new ApplyInfo();
    //绑定控件
    @BindViews({R.id.title_company_name, R.id.title_department, R.id.title_company_address,
            R.id.title_phone_number, R.id.title_after_tax_income,
            R.id.title_select_post_address, R.id.title_email_address})
    List<TextView> titleTvs;
    @BindView(R.id.scroll_root)
    ScrollView rootView;//最外层滑动布局
    @BindView(R.id.input_next_btn)
    Button nextStepBtn;//下一步按钮
    @BindView(R.id.input_last_btn)
    Button lastStepBtn;//上一步按钮
    @BindView(R.id.level3_email)
    BoxEditText inputApplyEmailAddress;//电子邮箱地址
    @BindView(R.id.level3_apply_company_name)
    BoxEditText inputApplyCompanyName;//申请人单位名称
    @BindView(R.id.department)
    BoxEditText inputApplyDepartment;//部门名称
    @BindView(R.id.comprovince)
    BoxEditText inputApplyComProvince;//单位所在的省
    @BindView(R.id.comcity)
    BoxEditText inputApplyComCity;//单位所在的城市
    @BindView(R.id.comarea)
    BoxEditText inputApplyComArea;//单位居住的区
    @BindView(R.id.comaddress)
    BoxEditText inputApplyComAddress;//单位居住地址
    @BindView(R.id.company_phone_number)
    BoxEditText inputApplyCompanyPhoneNumber;//单位联系方式
    @BindView(R.id.incoming)
    BoxEditText inputApplyIncoming;//税后年收入
    @BindView(R.id.post_type)
    GridLayout inputApplyPostType;//邮寄方式
    //属性
    RadioHelper<GridLayout> inputApplyApplyPostTypeHelper;//邮寄方式单选
    //**************************************** 选填项 *****************************************
    @BindView(R.id.level3_apply_vehicle_registration)
    LinearLayout inputApplyVehicleRegistration;//车辆登记日期
    DateDialog inputSelectVehicleRegistrationDateDialog;//日期选择弹窗
    @BindViews({R.id.vehicle_registration_year,R.id.vehicle_registration_month,R.id.vehicle_registration_day})
    List<TextView> vehicleRegistrationTv;//显示选择的车辆登记日期
    @Override
    protected int setContentView() {
        return R.layout.view_apply_info_level_three;
    }

    @Override
    protected void setFunction() {
        OverScrollDecoratorHelper.setUpOverScroll(rootView);//弹性滑动效果
        setTitleText();//设置标题的文字
        setOnClickHandle();//设置点击事件操作
        textInputHandle();//文本输入操作
        checkInputHandle();//选择文本操作
        setDialogHandle();//设置弹窗
    }
    /**
     * 设置弹窗
     */
    private void setDialogHandle() {
        inputSelectVehicleRegistrationDateDialog = new DateDialog(mContext, 0).addDateListener(this);
    }

    /**
     * 设置点击事件操作
     */
    private void setOnClickHandle() {
        inputApplyVehicleRegistration.setOnClickListener(this);
        nextStepBtn.setOnClickListener(this);//设置下一步操作
        lastStepBtn.setOnClickListener(this);//设置上一步操作
    }

    /**
     * 设置标题的文字
     */
    private void setTitleText() {
        titleTvs.get(0).setText(FormatUtils.getHtml(getResources().getString(R.string.level_three_apply_company)));
        titleTvs.get(1).setText(FormatUtils.getHtml(getResources().getString(R.string.level_three_apply_job_title)));
        titleTvs.get(2).setText(FormatUtils.getHtml(getResources().getString(R.string.level_three_apply_company_address)));
        titleTvs.get(3).setText(FormatUtils.getHtml(getResources().getString(R.string.level_three_apply_company_tel)));
        titleTvs.get(4).setText(FormatUtils.getHtml(getResources().getString(R.string.level_three_apply_after_tax_income)));
        titleTvs.get(5).setText(FormatUtils.getHtml(getResources().getString(R.string.level_three_apply_please_select_post_address)));
        titleTvs.get(6).setText(FormatUtils.getHtml(getResources().getString(R.string.level_three_apply_email_address)));
    }

    /**
     * 选择输入操作
     */
    private void checkInputHandle() {
        inputApplyApplyPostTypeHelper = new RadioHelper<>(inputApplyPostType, InformationUtils.getInfo().getMailAddress());
        setRadioCheckListener();//设置监听
    }

    /**
     * 设置单选监听
     */
    private void setRadioCheckListener() {
        inputApplyApplyPostTypeHelper.addCheckedChangeListener(this);
        inputApplyApplyPostTypeHelper.checkedFirst();
    }

    /**
     * 单选事件
     *
     * @param parentID
     * @param values
     */
    @Override
    public void onCheckedChanged(int parentID, String values) {
        switch (parentID) {
            case R.id.post_type://邮寄方式
                setInfo.setPosttype(values);
                break;
        }
        sendToView();
    }

    /**
     * 文本输入操作
     */
    private void textInputHandle() {
        inputApplyEmailAddress.addTextChangedListener(new EditTextHelper(this::setApplyEmailAddress));//设置电子邮箱
        inputApplyCompanyName.addTextChangedListener(new EditTextHelper(this::setApplyCompanyName));//设置单位名称
        inputApplyDepartment.addTextChangedListener(new EditTextHelper(this::setApplyDepartment));//设置部门名称
        inputApplyComProvince.addTextChangedListener(new EditTextHelper(this::setComProvince));//设置单位所在的省
        inputApplyComCity.addTextChangedListener(new EditTextHelper(this::setComCity));//设置单位所在的市
        inputApplyComArea.addTextChangedListener(new EditTextHelper(this::setComArea));//设置单位所在的区
        inputApplyComAddress.addTextChangedListener(new EditTextHelper(this::setComAddress));//设置单位详细地址
        inputApplyCompanyPhoneNumber.addTextChangedListener(new EditTextHelper(this::setCompanyPhoneNumber));//设置单位电话
        inputApplyIncoming.addTextChangedListener(new EditTextHelper(this::setIncoming));//设置税后年收入
    }

    /**
     * 设置税后年收入
     *
     * @param incoming
     */
    private void setIncoming(String incoming) {
        if (null != incoming && !TextUtils.isEmpty(incoming)) {
            int money = Integer.parseInt(incoming);
            setInfo.setIncoming(money);
        } else {
            setInfo.setIncoming(-1);
        }
        sendToView();
    }

    /**
     * 设置单位电话
     *
     * @param phoneNumber
     */
    private void setCompanyPhoneNumber(String phoneNumber) {
        setInfo.setComphone(phoneNumber);
        sendToView();
    }

    /**
     * 设置单位所在的省
     *
     * @param comProvince
     */
    private void setComProvince(String comProvince) {
        setInfo.setComprovince(comProvince);
        sendToView();
    }

    /**
     * 设置单位所在的市
     *
     * @param comCity
     */
    private void setComCity(String comCity) {
        setInfo.setComcity(comCity);
        sendToView();
    }

    /**
     * 设置单位所在的区
     *
     * @param comArea
     */
    private void setComArea(String comArea) {
        setInfo.setComarea(comArea);
        sendToView();
    }

    /**
     * 设置单位详细地址
     *
     * @param comAddress
     */
    private void setComAddress(String comAddress) {
        setInfo.setComaddress(comAddress);
        sendToView();
    }

    /**
     * 设置部门名称
     *
     * @param department
     */
    private void setApplyDepartment(String department) {
        setInfo.setDepartment(department);
        sendToView();
    }

    /**
     * 设置单位名称
     *
     * @param companyName
     */
    private void setApplyCompanyName(String companyName) {
        setInfo.setCompanyname(companyName);
        sendToView();
    }

    /**
     * 设置电子邮箱
     *
     * @param emailAddress
     */
    private void setApplyEmailAddress(String emailAddress) {
        setInfo.setEbilladdr(emailAddress);
        sendToView();
    }


    /**
     * 发送
     */
    private void sendToView() {
        setMessageListener.levelInfoChanged(Level.LEVEL3, setInfo);
    }

    /**
     * 重置
     */
    @Override
    public void resetView() {
        inputApplyApplyPostTypeHelper.checkedFirst();
        //输入域的清空
        ViewTextUtils.setTextViewEmpty(inputApplyEmailAddress);
        ViewTextUtils.setTextViewEmpty(inputApplyComAddress);
        ViewTextUtils.setTextViewEmpty(inputApplyDepartment);
        ViewTextUtils.setTextViewEmpty(inputApplyComProvince);
        ViewTextUtils.setTextViewEmpty(inputApplyComCity);
        ViewTextUtils.setTextViewEmpty(inputApplyComArea);
        ViewTextUtils.setTextViewEmpty(inputApplyComAddress);
        ViewTextUtils.setTextViewEmpty(inputApplyCompanyPhoneNumber);
        ViewTextUtils.setTextViewEmpty(inputApplyIncoming);
        ViewTextUtils.setTextViewEmpty(inputApplyCompanyName);
        //清空日期
        resetDate();
        //刷新
        sendToView();
    }

    /**
     * 清空日期
     */
    private void resetDate() {
        //车辆登记日期(字段未知)
        inputSelectVehicleRegistrationDateDialog.setNowData();//设置日期选择弹窗显示的日期
        ViewTextUtils.setTextViewEmpty(vehicleRegistrationTv);//制空
    }

    /**
     * 点击事件
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.input_next_btn://下一步
                applyInfoListener.nextStep(Level.LEVEL4);
                break;
            case R.id.input_last_btn://上一步
                applyInfoListener.lastStep(Level.LEVEL2);
                break;
            case R.id.level3_apply_vehicle_registration://车辆登记日期选择
                inputSelectVehicleRegistrationDateDialog.show();//开启弹窗
                break;
        }
    }

    /**
     * 选择日期回调
     * @param id
     * @param selectDate
     */
    @Override
    public void afterDateSelected(int id, Date selectDate) {
        String dateStr = DateUtils.getDateTimeStr(selectDate, DateUtils.FORMAT_DATE);
        switch (id) {
            case 0://车辆登记日期(字段未知)
                ViewTextUtils.setDateToView(dateStr, vehicleRegistrationTv);
                break;
        }
        sendToView();
    }
}

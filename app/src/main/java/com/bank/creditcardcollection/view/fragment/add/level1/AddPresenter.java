package com.bank.creditcardcollection.view.fragment.add.level1;

import android.app.Activity;
import android.content.Context;
import android.util.SparseIntArray;

import com.bank.creditcardcollection.R;
import com.bank.creditcardcollection.base.BaseRxPresenter;
import com.bank.creditcardcollection.constant.Constant;
import com.bank.creditcardcollection.model.entity.ApplyInfo;
import com.bank.creditcardcollection.model.now.request.ReferreRequest;
import com.bank.creditcardcollection.net.retrofit.HttpUtils;
import com.bank.creditcardcollection.weight.view.apply.level.Level;
import com.bank.creditcardcollection.weight.view.apply.level.LevelFiveView;
import com.bank.creditcardcollection.weight.view.apply.level.LevelFourView;
import com.bank.creditcardcollection.weight.view.apply.level.LevelOneView;
import com.bank.creditcardcollection.weight.view.apply.level.LevelThreeView;
import com.bank.creditcardcollection.weight.view.apply.level.LevelTwoView;
import com.bank.creditcardcollection.weight.view.apply.level.LevelView;
import com.bank.creditcardcollection.weight.view.apply.listener.LevelApplyInfoListener;
import com.bank.creditcardcollection.weight.view.apply.listener.LevelSetMessageListener;
import com.bank.creditcardcollection.weight.view.apply.listener.LevelStartActivityListener;
import com.google.gson.Gson;
import com.lyan.tools.utils.DateUtils;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

/**
 * MVP
 * Created by liyanjun on 2017/7/27.
 */

public class AddPresenter extends BaseRxPresenter<AddContract.View> implements AddContract.Presenter,
        LevelApplyInfoListener, LevelSetMessageListener, LevelStartActivityListener ,LevelFiveView.SelectReferrerMessageFromServiceListener{

    private final ApplyInfo commitInfo = new ApplyInfo();
    private LevelView levelOneView, levelTwoView, levelThreeView, levelFourView, levelFiveView;
    private final SparseIntArray drawResID = new SparseIntArray(5);//设置容器为5;
    private ArrayList<LevelView> levelViews = new ArrayList<>(5);//数据源

    public AddPresenter(AddContract.View view) {
        super(view);
    }

    /**
     * 初始化
     */
    @Override
    public void initData() {
        initTabImage();//初始化指示器图片
        initItemView(mView.provideContext());//初始化选项
        setItemViewListener();//设置选项界面的监听事件
        mView.getLevelPageTabs(drawResID);
        mView.getLevelPageViews(levelViews);
    }

    /**
     * 设置监听事件
     */
    private void setItemViewListener() {
        //用来控制翻页
        levelOneView.setApplyInfoListener(this);
        levelTwoView.setApplyInfoListener(this);
        levelThreeView.setApplyInfoListener(this);
        levelFourView.setApplyInfoListener(this);
        levelFiveView.setApplyInfoListener(this);
        //用来监听页面展示信息的变化
        levelOneView.setSetMessageListener(this);
        levelTwoView.setSetMessageListener(this);
        levelThreeView.setSetMessageListener(this);
        levelFourView.setSetMessageListener(this);
        levelFiveView.setSetMessageListener(this);
        //跳转界面
        levelFourView.setStartActivityListener(this);
        ((LevelFiveView)levelFiveView).addSelectReferrerMessageFromServiceListener(this);
    }

    /**
     * 初始化指示器图片
     */
    private void initTabImage() {
        drawResID.append(0, R.drawable.apply_info_level_one);
        drawResID.append(1, R.drawable.apply_info_level_two);
        drawResID.append(2, R.drawable.apply_info_level_three);
        drawResID.append(3, R.drawable.apply_info_level_four);
        drawResID.append(4, R.drawable.apply_info_level_five);
    }

    /**
     * 初始化填写步骤的控件
     */
    private void initItemView(Context mContext) {
        levelViews.add(levelOneView = new LevelOneView(mContext));
        levelViews.add(levelTwoView = new LevelTwoView(mContext));
        levelViews.add(levelThreeView = new LevelThreeView(mContext));
        levelViews.add(levelFourView = new LevelFourView(mContext));
        levelViews.add(levelFiveView = new LevelFiveView(mContext));
    }

    @Override
    public void nextStep(int position) {
        mView.nextStep(position);
    }

    @Override
    public void lastStep(int position) {
        mView.lastStep(position);
    }

    /**
     * 提交
     */
    @Override
    public void commit() {
        commitInfo.setCreatetime(DateUtils.getTodayStr(DateUtils.FORMAT_DATE_TIME));
        commitInfo.setStatus(Constant.STATE_1);
        commitInfo.setUsercode("fanjian");
        Logger.t("提交的内容").i(commitInfo.toString());
        addSubscribe(HttpUtils.postAddApplyResult(new Gson().toJson(commitInfo))
//                .doFinally(this::resetApplyData)//订阅事件结束后重置
                .subscribe(s -> Logger.t("成功").w(s),
                        throwable -> Logger.t("失败").w(throwable.getMessage())));
//        resetApplyData();
    }

    /**
     * 组合选中的数据
     *
     * @param pageNo
     * @param changeInfo
     */
    @Override
    public void levelInfoChanged(int pageNo, ApplyInfo changeInfo) {
        switch (pageNo) {
            case Level.LEVEL1://第一页
                break;
            case Level.LEVEL2://第二页
                commitInfo.setLevel2Info(changeInfo);
                break;
            case Level.LEVEL3://第三页
                commitInfo.setLevel3Info(changeInfo);
                break;
            case Level.LEVEL4://第四页
                break;
            case Level.LEVEL5://第五页
                break;
        }
    }

    /**
     * 清空数据（重置前:null,重置后:""）
     */
    @Override
    public void resetApplyData() {
        for (int i = 0; i < levelViews.size(); i++) {
            levelViews.get(i).resetView();//清空填写的数据
        }
    }

    /**
     * 释放资源
     */
    @Override
    public void onDestroy() {
        for (int i = 0; i < levelViews.size(); i++) {
            levelViews.get(i).onDestroy();//销毁资源
        }
        super.onDestroy();
    }

    /**
     * 启动界面
     *
     * @param clazz
     * @param requestCode
     * @param <T>
     */
    @Override
    public <T extends Activity> void startActivity(Class<T> clazz, int requestCode) {
        mView.startActivityForResultInLevel(clazz, requestCode);
    }

    /**
     * 进行网络请求（根据员工号查询员工信息）
     * @param selectCode
     */
    @Override
    public void selectReferrerEmployeeNumberInfo(String selectCode) {
        Logger.t("查询的员工编码").i(selectCode);
        String requestJson = new Gson().toJson(new ReferreRequest(selectCode));
        Logger.t("请求参数").w(requestJson);
        addSubscribe(HttpUtils.postAddReferrerInfoRequest(requestJson)
        .subscribe(s -> Logger.t("返回结果").json(s) ,throwable ->  Logger.t("错误结果").i(throwable.getMessage()) ));
    }
    //{"success":1,"msg":"登录成功","data":"{"dataRange":"","id":"2017072400000573","imeicode":"","ismessage":"","jigouleibie":"1","loginName":"fanjian","loginpwd":"111111","mac":"","menuList":[],"menuMap":null,"moduleDataRange":{"1003":{"address":"/xxgl/xmgl/xmgl.jsp","childModule":[],"dataRange":"2017072400000571","funcPermission":"1","id":"1003","module_isleaf":"0","module_type":"2","name":"模块管理","orderNum":"2","pid":"1001","systemid":"mkgl","urls":"","viewDateRrange":"0"},"1004":{"address":"/system/person/lygbperson.jsp","childModule":[],"dataRange":"2017072400000571","funcPermission":"1","id":"1004","module_isleaf":"0","module_type":"2","name":"人员变更管理","orderNum":"3","pid":"1001","systemid":"rybggl","urls":"","viewDateRrange":"0"},"1001":{"address":"/system/organize/zuzhijigou.jsp","childModule":[{"address":"/xxgl/xmgl/xmgl.jsp","childModule":[],"dataRange":"2017072400000571","funcPermission":"1","id":"1003","module_isleaf":"0","module_type":"2","name":"模块管理","orderNum":"2","pid":"1001","systemid":"mkgl","urls":"","viewDateRrange":"0"},{"address":"/system/person/lygbperson.jsp","childModule":[],"dataRange":"2017072400000571","funcPermission":"1","id":"1004","module_isleaf":"0","module_type":"2","name":"人员变更管理","orderNum":"3","pid":"1001","systemid":"rybggl","urls":"","viewDateRrange":"0"},{"address":"/xxgl/xmgl/xmgl.jsp","childModule":[],"dataRange":"2017072400000571","funcPermission":"1","id":"1002","module_isleaf":"0","module_type":"2","name":"项目管理","orderNum":"1","pid":"1001","systemid":"xmgl","urls":"","viewDateRrange":"0"},{"address":"/system/person/lygbperson.jsp","childModule":[],"dataRange":"2017072400000571","funcPermission":"1","id":"1005","module_isleaf":"0","module_type":"2","name":"审批管理","orderNum":"4","pid":"1001","systemid":"spgl","urls":"","viewDateRrange":"0"},{"address":"/xxgl/ceshiguanli/csxm_list.jsp","childModule":[],"dataRange":"2017072400000571","funcPermission":"1","id":"1006","module_isleaf":"0","module_type":"2","name":"测试管理","orderNum":"5","pid":"1001","systemid":"csgl","urls":"","viewDateRrange":"0"}],"dataRange":"2017072400000571","funcPermission":"1","id":"1001","module_isleaf":"1","module_type":"1","name":"项目设置","orderNum":"1","pid":"0","systemid":"xmsz","urls":"","viewDateRrange":"0"},"1002":{"address":"/xxgl/xmgl/xmgl.jsp","childModule":[],"dataRange":"2017072400000571","funcPermission":"1","id":"1002","module_isleaf":"0","module_type":"2","name":"项目管理","orderNum":"1","pid":"1001","systemid":"xmgl","urls":"","viewDateRrange":"0"},"1005":{"address":"/system/person/lygbperson.jsp","childModule":[],"dataRange":"2017072400000571","funcPermission":"1","id":"1005","module_isleaf":"0","module_type":"2","name":"审批管理","orderNum":"4","pid":"1001","systemid":"spgl","urls":"","viewDateRrange":"0"},"1006":{"address":"/xxgl/ceshiguanli/csxm_list.jsp","childModule":[],"dataRange":"2017072400000571","funcPermission":"1","id":"1006","module_isleaf":"0","module_type":"2","name":"测试管理","orderNum":"5","pid":"1001","systemid":"csgl","urls":"","viewDateRrange":"0"}},"moduleid":"","order_num":"","org_cq":"","org_dt":"","org_range":"","org_scale":"","org_tc":"","roleBean":null,"sheguanZhongxinId":"","sheguanZhongxinName":"","status":"0","systemList":[],"systemid":"","userGangweiId":"2017072400000570","userGangweiName":"信用卡部","userGangweiNum":null,"userJigouId":"2017072400000571","userJigouName":"客户经理","userJigouType":"1","userParentJigouId":"","userParentJigouName":"","userRealName":"范坚","userWangeId":"","userWangeName":"","userWangeProcId":"","userid":"2017072400000572","validateCode":"","wangge_id":"","wangge_name":""}"}
}

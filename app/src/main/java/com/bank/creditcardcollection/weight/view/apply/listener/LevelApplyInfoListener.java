package com.bank.creditcardcollection.weight.view.apply.listener;

/**
 * 提交信用卡申请信息
 * Created by liyanjun on 2017/7/27.
 */

public interface LevelApplyInfoListener {
    void nextStep(int position);//下一步
    void lastStep(int position);//上一步
    void commit();//提交
}

package com.bank.creditcardcollection.weight.view.apply.listener;

import com.bank.creditcardcollection.model.entity.ApplyInfo;

/**
 * 数据发生改变
 * Created by liyanjun on 2017/7/31.
 */

public interface LevelSetMessageListener {
    void levelInfoChanged(int pageNo, ApplyInfo changeInfo);
}

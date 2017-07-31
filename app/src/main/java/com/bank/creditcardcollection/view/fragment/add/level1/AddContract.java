package com.bank.creditcardcollection.view.fragment.add.level1;

import android.content.Context;
import android.util.SparseIntArray;

import com.bank.creditcardcollection.base.BaseView;
import com.bank.creditcardcollection.base.activity.LifePresenter;
import com.bank.creditcardcollection.model.entity.ApplyInfo;
import com.bank.creditcardcollection.weight.view.apply.level.LevelView;

import java.util.ArrayList;

/**
 * Created by liyanjun on 2017/7/27.
 */

public interface AddContract {
    /**
     * 视图
     */
    interface View<P extends AddContract.Presenter> extends BaseView<P> {
        void setCommitApplyInfo(ApplyInfo applyInfo);//设置提交的选项
        void nextStep(int position);//下一页
        void lastStep(int position);//上一页
        void commit();//提交
        Context provideContext();//提供一个上下文
        void getLevelPageViews(ArrayList<LevelView> views);//页面的控件
        void getLevelPageTabs(SparseIntArray resIDs);//指示器图片
    }

    /**
     * 控制器
     */
    interface Presenter extends LifePresenter<AddContract.View> {

    }
}

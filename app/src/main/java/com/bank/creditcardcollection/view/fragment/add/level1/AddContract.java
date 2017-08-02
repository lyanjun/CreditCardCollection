package com.bank.creditcardcollection.view.fragment.add.level1;

import android.app.Activity;
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
        void nextStep(int position);//下一页

        void lastStep(int position);//上一页

        Context provideContext();//提供一个上下文

        void getLevelPageViews(ArrayList<LevelView> views);//页面的控件

        void getLevelPageTabs(SparseIntArray resIDs);//指示器图片

        <T extends Activity> void startActivityForResultInLevel(Class<T> clazz , int requestCode);//带有返回参数的界面跳转
    }

    /**
     * 控制器
     */
    interface Presenter extends LifePresenter<AddContract.View> {
        void resetApplyData();//清空数据
    }
}

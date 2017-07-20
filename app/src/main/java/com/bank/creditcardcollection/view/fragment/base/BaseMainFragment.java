package com.bank.creditcardcollection.view.fragment.base;


import com.lyan.tools.utils.ToastUtils;

/**
 * 模块级别的Fragment(容器)
 * Created by liyanjun on 2017/7/19.
 */

public abstract class BaseMainFragment extends BaseFragment{
    // 再点一次退出程序时间设置
    private static final long WAIT_TIME = 2000L;
    private long TOUCH_TIME = 0;

    /**
     * 处理回退事件
     *
     * @return
     */
    @Override
    public boolean onBackPressedSupport() {
        if (getChildFragmentManager().getBackStackEntryCount() > 1) {
            popChild();
        }else if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
            mActivity.finish();
        } else {
            TOUCH_TIME = System.currentTimeMillis();
            ToastUtils.shortToast(mActivity,"再按一次退出程序");
        }
        return true;
    }
}

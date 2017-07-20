package com.bank.creditcardcollection.app;


import com.bank.creditcardcollection.BuildConfig;
import com.lyan.tools.app.MineApplication;
import com.lyan.tools.utils.SystemUtil;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

/**
 * 入口
 * Created by liyanjun on 2017/7/18.
 */

public class App extends MineApplication {
    /**
     * 初始化
     */
    @Override
    public void onCreate() {
        super.onCreate();
        initLogger();//初始化日志工具
        printUsePhoneMessage();//打印手机信息
    }

    /**
     * 初始化Log工具
     */
    private void initLogger() {
        Logger.addLogAdapter(new AndroidLogAdapter(PrettyFormatStrategy.newBuilder().tag("多银行").build()) {
            @Override
            public boolean isLoggable(int priority, String tag) {
                return BuildConfig.DEBUG;//调试模式下开启Log输出
            }
        });
    }

    /**
     * 打印手机信息
     */
    private void printUsePhoneMessage() {
        Logger.t("用户手机信息").i("手机厂商：%s\n手机系统版本：%s\n手机型号：%s",
                SystemUtil.getDeviceBrand(), SystemUtil.getSystemVersion(), SystemUtil.getSystemModel());
    }
}

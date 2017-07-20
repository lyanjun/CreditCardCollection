package com.lyan.tools.app;

import android.app.Application;
import android.util.Log;

import com.lyan.tools.utils.SystemUtil;


/**
 * 入口
 * Created by liyanjun on 2017/6/29.
 */

public class MineApplication extends Application{
    public static String DEVICE_BRAND ;
    @Override
    public void onCreate() {
        super.onCreate();
        DEVICE_BRAND = SystemUtil.getDeviceBrand();
    }
}

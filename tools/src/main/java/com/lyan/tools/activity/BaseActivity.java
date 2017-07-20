package com.lyan.tools.activity;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;


import com.lyan.tools.app.MineApplication;
import com.lyan.tools.constant.BandName;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


/**
 * 作者： LYJ
 * 功能： Activity基类
 * 创建日期： 2017/5/2
 */

public abstract class BaseActivity extends MPermissionsActivity {
    protected final int WRITE_SETTINGS = 99;
    protected final int SYSTEM_ALERT_WINDOW = 100;
    protected Bundle savedInstanceState;

    /**
     * 初始化
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.savedInstanceState = savedInstanceState;
        //设置状态栏透明
        setTranslucentStatus(true);
        //设置状态栏文字为黑色
        if (BandName.XIAO_MI.equals(MineApplication.DEVICE_BRAND) && Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setStatusBarDarkMode(true, this);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    /**
     * MIUI6以上的状态栏字体适配
     *
     * @param darkmode
     * @param activity
     */
    public void setStatusBarDarkMode(boolean darkmode, Activity activity) {
        Class<? extends Window> clazz = activity.getWindow().getClass();
        try {
            int darkModeFlag;
            Class<?> layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
            darkModeFlag = field.getInt(layoutParams);
            Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
            extraFlagField.invoke(activity.getWindow(), darkmode ? darkModeFlag : 0, darkModeFlag);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置状态栏透明
     * @param on
     */
    protected void setTranslucentStatus(boolean on) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window win = getWindow();
            WindowManager.LayoutParams winParams = win.getAttributes();
            final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            if (on) {
                winParams.flags |= bits;
            } else {
                winParams.flags &= ~bits;
            }
            win.setAttributes(winParams);
        }
    }
    /**
     * 跳转界面
     *
     * @param clazz
     * @return
     */
    protected final <T extends Activity> Intent activity(Class<T> clazz) {
        return new Intent(this, clazz);
    }

    /**
     * 管理服务
     *
     * @param clazz
     * @param <T>
     * @return
     */
    protected final <T extends Service> Intent service(Class<T> clazz) {
        return new Intent(this, clazz);
    }

    /**
     * WRITE_SETTINGS权限检查
     */
    protected final void checkedWriteSettings() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.System.canWrite(this)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS,
                        Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, WRITE_SETTINGS);
            }
        }
    }

    /**
     * SYSTEM_ALERT_WINDOW权限检查
     */
    protected final void checkedSystemAlertWindow() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, SYSTEM_ALERT_WINDOW);
            }
        }
    }
}

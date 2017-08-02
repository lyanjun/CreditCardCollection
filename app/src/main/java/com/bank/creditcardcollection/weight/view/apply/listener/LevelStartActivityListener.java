package com.bank.creditcardcollection.weight.view.apply.listener;

import android.app.Activity;

/**
 * Created by liyanjun on 2017/8/2.
 */

public interface LevelStartActivityListener {

    <T extends Activity> void startActivity(Class<T> clazz , int requestCode);//带有返回参数的界面跳转
}

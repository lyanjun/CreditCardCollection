package com.bank.creditcardcollection.weight.dialog;

import android.content.Context;
import android.view.WindowManager;

import com.bank.creditcardcollection.R;
import com.lyan.tools.weight.base.CustomDialog;


/**
 * 加载等待弹窗
 * Created by liyanjun on 2017/6/16.
 */

public class LoadingDialog extends CustomDialog {

    public LoadingDialog(Context context) {
        this(context, false);
    }

    public LoadingDialog(Context context, boolean dimEnabled) {
        super(context, dimEnabled);
    }

    @Override
    protected void onCreateView(WindowManager windowManager) {
        setDialogWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        setDialogHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        setContentView(R.layout.dialog_loding);
        setCanceledOnTouchOutside(false);
    }
}

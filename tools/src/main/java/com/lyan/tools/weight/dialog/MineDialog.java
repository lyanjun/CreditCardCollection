package com.lyan.tools.weight.dialog;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lyan.tools.R;
import com.lyan.tools.view.InputBox;
import com.lyan.tools.weight.base.CustomDialog;

/**
 * 带有确认和取消的按钮
 * Created by liyanjun on 2017/8/3.
 */

public class MineDialog extends CustomDialog implements View.OnClickListener {
    //接口回调
    public interface OnConfirmListener {
        void getInputMessage(String inputText);
    }

    //属性
    private OnConfirmListener confirmListener;
    private TextView customTitle;//标题
    private Button cancel, confirm;//取消、提交
    private InputBox inputBox;//输入框

    public MineDialog(Context context) {
        super(context, true);
        setContentView(R.layout.custom_dialog_body);
        //初始化控件
        customTitle = (TextView) findViewById(R.id.custom_mine_dialog_title);
        cancel = (Button) findViewById(R.id.iv_cancel);
        confirm = (Button) findViewById(R.id.tv_finish);
        inputBox = (InputBox) findViewById(R.id.custom_mine_input_box);
        cancel.setOnClickListener(this);
        confirm.setOnClickListener(this);
    }

    @Override
    protected void onCreateView(WindowManager windowManager) {
        DisplayMetrics outMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(outMetrics);
        setDialogWidth((int) (outMetrics.widthPixels * 0.9f));
        setDialogHeight(WindowManager.LayoutParams.WRAP_CONTENT);
    }

    /**
     * 点击事件
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_finish) {
            if (null != confirmListener) confirmListener.getInputMessage(inputBox.getInputText());
        }
        dismiss();
    }

    /**
     * 设置标题内容
     *
     * @param titleText
     */
    public void setTitleText(String titleText) {
        customTitle.setText(titleText);//设置文本
        customTitle.setVisibility(View.VISIBLE);
    }

    public MineDialog addConfirmListener(OnConfirmListener confirmListener) {
        this.confirmListener = confirmListener;
        return this;
    }

    /**
     * 清空文本
     */
    public void clearText(){
        inputBox.setTextEmpty();
    }
}

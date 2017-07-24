package com.lyan.tools.view;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 *
 * Created by liyanjun on 2017/7/24.
 */
public final class BoxEditText extends AppCompatEditText {
    private InputMethodManager imm;
    public BoxEditText(Context context) {
        super(context);
        init();
    }

    private void init() {
        imm = (InputMethodManager)getContext(). getSystemService(INPUT_METHOD_SERVICE);
//        setSelectAllOnFocus(false);
        setLongClickable(false);
        setTextIsSelectable(false);
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        if(event.getAction()==MotionEvent.ACTION_UP)
//
//            imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
//
//        return true;
//    }

    public BoxEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BoxEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onSelectionChanged(int selStart, int selEnd) {
        super.onSelectionChanged(selStart, selEnd);
        //保证光标始终在最后面
        if (selStart == selEnd) {//防止不能多选
            setSelection(getText().length());
        }
    }

}

package com.bank.creditcardcollection.weight.view.apply.help;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * 输入框帮助类
 * Created by liyanjun on 2017/7/31.
 */

public class EditTextHelper implements TextWatcher {
    public interface InputTextChangedListener{
        void onTextChanged(String message);
    }
    private InputTextChangedListener changedListener;

    public EditTextHelper(InputTextChangedListener changedListener) {
        this.changedListener = changedListener;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        changedListener.onTextChanged(s.toString());
    }
}

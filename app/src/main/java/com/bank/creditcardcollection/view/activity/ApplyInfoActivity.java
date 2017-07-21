package com.bank.creditcardcollection.view.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;

import com.bank.creditcardcollection.R;
import com.bank.creditcardcollection.base.activity.MineBaseActivity;

import butterknife.BindView;

public class ApplyInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_info);
    }
}

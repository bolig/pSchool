package com.peoit.android.online.pschool.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.ui.Base.BaseActivity;

/**
 * 银行卡绑定
 * Created by zyz on 2015/7/16.
 */
public class BankCardActivity extends BaseActivity{
    private TextView tv_bank,tv_name,tv_num;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bankcard);
        getPsActionBar().settitle("银行卡绑定");
    }
    public static void startThisActivity(Activity mAc){
        Intent intent = new Intent(mAc, BankCardActivity.class);
        mAc.startActivity(intent);
    }
    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        tv_bank = (TextView) findViewById(R.id.bankcard1);
        tv_name = (TextView) findViewById(R.id.bankcard2);
        tv_num = (TextView) findViewById(R.id.bankcard3);
    }

    @Override
    public void initListener() {

    }

}

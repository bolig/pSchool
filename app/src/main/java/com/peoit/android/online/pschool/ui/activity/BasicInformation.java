package com.peoit.android.online.pschool.ui.activity;

import android.os.Bundle;

import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.ui.Base.BaseActivity;

/**
 * Created by ZYZ on 2015/7/16.
 */
public class BasicInformation extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basicinformation);
        getPsActionBar().settitle("基本资料");

    }


    @Override
    public void initData() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void responseSuccess(Object responses) {

    }
}

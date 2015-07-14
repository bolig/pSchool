package com.peoit.android.online.pschool.ui.activity;

import android.os.Bundle;

import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.ui.Base.BaseActivity;

import java.util.Timer;
import java.util.TimerTask;


public class WelcomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isMainUI = false;
        setContentView(R.layout.act_home);
    }

    @Override
    public void initData() {
        final Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {

            }
        };
    }

    @Override
    public void initView() {

    }

    @Override
    public void initListener() {

    }

}

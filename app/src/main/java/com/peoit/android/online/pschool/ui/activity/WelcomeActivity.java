package com.peoit.android.online.pschool.ui.activity;

import android.os.Bundle;
import android.os.Handler;

import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.ui.Base.BaseActivity;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 引导界面
 *
 * author:libo
 * time:2015/7/9
 * E-mail:boli_android@163.com
 * last: ...
 */
public class WelcomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isMainUI = false;
        setContentView(R.layout.act_wel);
    }

    @Override
    public void initData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                HomeActivity.startThisActivity(mContext);
                finish();
            }
        }, 2000);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initListener() {

    }

}

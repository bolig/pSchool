package com.peoit.android.online.pschool.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.ui.Base.BaseActivity;

/**
 * 在线支付
 * <p/>
 * author:libo
 * time:2015/8/24
 * E-mail:boli_android@163.com
 * last: ...
 */
public class ApplyOnlineActivity extends BaseActivity {

    public static void startThisActivity(Activity mAc) {
        Intent intent = new Intent(mAc, ApplyOnlineActivity.class);
        mAc.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_apply_online);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        getPsActionBar().settitle("在线报名");
    }

    @Override
    public void initListener() {

    }
}

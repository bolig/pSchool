package com.peoit.android.online.pschool.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.ui.Base.BaseActivity;

/**
 * 网上商城
 *
 * author:libo
 * time:2015/7/9
 * E-mail:boli_android@163.com
 * last: ...
 */
public class ShopOnlineActivity extends BaseActivity {

    public static void startThisActivity(Activity mAc){
        Intent intent = new Intent(mAc, ShopOnlineActivity.class);
        mAc.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_shop_online);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        getPsActionBar().settitle("在线商城");

    }

    @Override
    public void initListener() {

    }
}

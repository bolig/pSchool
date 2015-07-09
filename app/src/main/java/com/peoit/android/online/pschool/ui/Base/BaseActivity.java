package com.peoit.android.online.pschool.ui.Base;

import android.app.Activity;
import android.os.Bundle;

import com.peoit.android.online.pschool.ui.BaseUiAct;

/**
 * author:libo
 * time:2015/7/9
 * E-mail:boli_android@163.com
 * last: ...
 */
public abstract class BaseActivity extends Activity implements BaseUiAct {

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        initData();
        initView();
        initListener();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

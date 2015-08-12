package com.peoit.android.online.pschool.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;

import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.ui.Base.BaseActivity;

public class PushActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_push);
    }

    @Override
    public void initData() {
        String data = getIntent().getStringExtra("data");
        String msg = getIntent().getStringExtra("msg");
        if (TextUtils.isEmpty(data) || TextUtils.isEmpty(msg)) {
            showToast("数据传输错误");
            finish();
        }
    }

    @Override
    public void initView() {

    }

    @Override
    public void initListener() {

    }
}

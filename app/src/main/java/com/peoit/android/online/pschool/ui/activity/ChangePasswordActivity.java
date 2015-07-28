package com.peoit.android.online.pschool.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.ui.Base.BaseActivity;

/**
 * 修改密码
 * Created by zyz on 2015/7/24.
 */
public class ChangePasswordActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepassword);
        getPsActionBar().settitle("修改密码");

    }
    public static void startThisActivity(Activity mAc){
        Intent intent = new Intent(mAc, ChangePasswordActivity.class);
        mAc.startActivity(intent);

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

}

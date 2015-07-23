package com.peoit.android.online.pschool.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.entity.UserInfo;
import com.peoit.android.online.pschool.ui.Base.BaseActivity;
import com.peoit.android.online.pschool.ui.Presenter.LoginPresenter;

import java.util.Map;

/**
 * 登陆界面
 *
 * author:libo
 * time:2015/7/9
 * E-mail:boli_android@163.com
 * last: ...
 */
public class LoginActivity extends BaseActivity<UserInfo> implements View.OnClickListener{

    private EditText et_user;
    private EditText et_pass;
    private TextView btn_login;
    private TextView tv_find;
    private String password;
    private String userName;
    private LoginPresenter<UserInfo> presenter;

    public static void startThisActivity(Activity mAc){
        Intent intent = new Intent(mAc, LoginActivity.class);
        mAc.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isMainUI = false;
        setContentView(R.layout.act_login);
        presenter = new LoginPresenter<UserInfo>(this) {
            @Override
            protected void getUserNameAndPassword(Map<String, String> params) {
                LoginActivity.this.getUserNameAndPassword(params);
            }
        };
    }

    private void getUserNameAndPassword(Map<String, String> params) {
        if (match()){
            params.put("phoneNumber", "15025496981");
            params.put("password", "123456");
        }
    }

    private boolean match() {
        userName = et_user.getText().toString();
        if (TextUtils.isEmpty(userName)){
            showToast("请输入用户名");
            return false;
        }
        password = et_pass.getText().toString();
        if (TextUtils.isEmpty(password)){
            showToast("请输入密码");
            return false;
        }
        return true;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        et_user = (EditText) findViewById(R.id.loge_et_username);
        et_pass = (EditText) findViewById(R.id.loge_et_password);
        btn_login = (TextView) findViewById(R.id.logb_btn_login);
        tv_find = (TextView) findViewById(R.id.logt_tv_find);
    }

    @Override
    public void initListener() {
        btn_login.setOnClickListener(this);
        tv_find.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btn_login){
            presenter.toLogin();
        } else if (v == tv_find){
            showToast("忘记密码");
        }
    }
}

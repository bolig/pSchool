package com.peoit.android.online.pschool.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.ui.Base.BaseActivity;
import com.peoit.android.online.pschool.ui.Presenter.ModifyPasswordPersenter;

import java.util.Map;

/**
 * 修改密码
 * Created by zyz on 2015/7/24.
 */
public class ChangePasswordActivity extends BaseActivity {

    private TextInputLayout inputOldpass;
    private TextInputLayout inputNewpass;
    private EditText etNewpass;
    private TextInputLayout inputRepass;
    private EditText etRepass;

    private TextView tvShow;
    private TextView tvSubmit;

    private ModifyPasswordPersenter mPersenter;

    private String oldPassword;
    private String newPassword;
    private EditText etOldpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepassword);
        getPsActionBar().settitle("修改密码");
    }

    public static void startThisActivity(Activity mAc) {
        Intent intent = new Intent(mAc, ChangePasswordActivity.class);
        mAc.startActivity(intent);
    }

    @Override
    public void initData() {
        mPersenter = new ModifyPasswordPersenter(this) {
            @Override
            protected Map<String, String> getNewPassword(Map<String, String> params) {
                params.put("oldpassword", oldPassword);
                params.put("newpassword", newPassword);
                return params;
            }
        };
    }



    @Override
    public void initView() {
        inputOldpass = (TextInputLayout) findViewById(R.id.input_oldpass);
        etOldpass = (EditText) findViewById(R.id.et_oldpass);

        inputNewpass = (TextInputLayout) findViewById(R.id.input_newpass);
        etNewpass = (EditText) findViewById(R.id.et_newpass);

        inputRepass = (TextInputLayout) findViewById(R.id.input_repass);
        etRepass = (EditText) findViewById(R.id.et_repass);

        tvShow = (TextView) findViewById(R.id.tv_show);
        tvSubmit = (TextView) findViewById(R.id.tv_submit);

    }

    @Override
    public void initListener() {
        tvShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isCheck = !tvShow.isSelected();
                tvShow.setSelected(isCheck);
                if (isCheck) {
                    etOldpass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    etNewpass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    etRepass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    etOldpass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    etNewpass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    etRepass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
        tvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (match()){
                    mPersenter.doModify();
                }
            }
        });
    }

    private boolean match() {
        oldPassword = etOldpass.getText().toString();
        if (TextUtils.isEmpty(oldPassword)) {
            showToast("请输入当前密码");
            return false;
        }
        newPassword = etNewpass.getText().toString();
        if (TextUtils.isEmpty(newPassword)) {
            showToast("请输入新的密码");
            return false;
        }
        if (!newPassword.equals(etRepass.getText().toString())){
            showToast("两次密码不一致");
            return false;
        }
        return true;
    }
}

package com.peoit.android.online.pschool.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.ui.Base.BaseActivity;

/**
 * 修改密码
 * Created by zyz on 2015/7/24.
 */
public class ChangePasswordActivity extends BaseActivity {
    private EditText et_oldpwd;
    private ImageView iv_oldpwd;
    private EditText et_newpwd;
    private ImageView tv_newpwd;
    private EditText et_newpwd1;
    private ImageView iv_newpwd1;
    private EditText et_phone;
    private TextView tv_phone;
    private EditText et_num;
    private ImageView iv_num;
    private TextView tv_show;
    private TextView tv_confirm;
    boolean isChecked = false;

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

    }

    @Override
    public void initView() {
        et_oldpwd = (EditText) findViewById(R.id.changepassword_et1);//旧密码
        et_newpwd = (EditText) findViewById(R.id.changepassword_et2);//新密码
        et_newpwd1 = (EditText) findViewById(R.id.changepassword_et3);//确认新密码
        et_phone = (EditText) findViewById(R.id.changepassword_et4);//电话
        et_num = (EditText) findViewById(R.id.changepassword_et5);//验证码
        tv_show = (TextView) findViewById(R.id.changepassword_tv2);//显示密码
        tv_phone = (TextView) findViewById(R.id.changepassword_tv1);//发送验证码
        tv_confirm = (TextView) findViewById(R.id.changepassword_tv3);//确认
    }

    @Override
    public void initListener() {

        tv_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isChecked = !isChecked;
                //显示密码
                if (!isChecked) {
                    //隐藏
                    et_oldpwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    et_newpwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    et_newpwd1.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    Drawable drawable = getResources().getDrawable(R.mipmap.hidepassword);
                    /// 这一步必须要做,否则不会显示.
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    tv_show.setCompoundDrawables(drawable, null, null, null);
                } else {
                    //显示
                    et_oldpwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    et_newpwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    et_newpwd1.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    Drawable drawable = getResources().getDrawable(R.mipmap.showpassword);
                    /// 这一步必须要做,否则不会显示.
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    tv_show.setCompoundDrawables(drawable, null, null, null);
                }
            }
        });
        tv_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //发送验证码
            }
        });
        tv_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //确认

            }
        });

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.changepassword_iv1:
                //旧密码
                et_oldpwd.setText("");
                break;
            case R.id.changepassword_iv2:
                //新密码
                et_newpwd.setText("");
                break;
            case R.id.changepassword_iv3:
                //确认新密码
                et_newpwd1.setText("");
                break;
            case R.id.changepassword_iv4:
                //验证码
                et_num.setText("");
                break;
            default:
                break;
        }
    }
}

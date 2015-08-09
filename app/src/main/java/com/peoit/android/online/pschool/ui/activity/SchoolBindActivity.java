package com.peoit.android.online.pschool.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.entity.UserInfo;
import com.peoit.android.online.pschool.ui.Base.BaseActivity;

/**
 * 学校绑定
 *
 * author:libo
 * time:2015/7/9
 * E-mail:boli_android@163.com
 * last: ...
 */
public class SchoolBindActivity extends BaseActivity {

    private TextView tv_student;
    private TextView tv_class;
    private TextView tv_school;
    private UserInfo user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_school_bind);
        getPsActionBar().settitle("学校绑定");
    }

    public static void startThisActivity(Activity mAc){
        Intent intent = new Intent(mAc, SchoolBindActivity.class);
        mAc.startActivity(intent);
    }

    @Override
    public void initData() {
        user = getCurrentUser();
    }

    @Override
    public void initView() {
        tv_student = (TextView) findViewById(R.id.bint_tv_student);
        tv_class = (TextView) findViewById(R.id.bint_tv_class);
        tv_school = (TextView) findViewById(R.id.bint_tv_school);

        //所属班主任
        tv_student.setText(user.getNickname());
        //所在班级
        tv_class.setText(user.getStuclassname());
        //所在学校
        tv_school.setText(user.getStuschoolname());
    }

    @Override
    public void initListener() {

    }
}

package com.peoit.android.online.pschool.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.ui.Base.BaseFragment;
import com.peoit.android.online.pschool.ui.activity.CheckInActivity;
import com.peoit.android.online.pschool.ui.activity.QueryGradeActivity;

/**
 * 金融IC卡(教师界面...)
 *
 * <p/>
 * author:libo
 * time:2015/7/30
 * E-mail:boli_android@163.com
 * last: ...
 */
public class BankIC_teacher_Fragment extends BaseFragment implements View.OnClickListener{
    private LinearLayout ll_check_in;
    private LinearLayout ll_grade;

    @Override
    protected View getCurrentLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.act_bank_ic_teacher_fragemt, container, false);
    }

    @Override
    public void onClick(View v) {
        if (v == ll_check_in){
            //考勤查询
            CheckInActivity.startThisActivity(getActivity());
        } else if (v == ll_grade){
            //成绩查询
            QueryGradeActivity.startThisActivity(getActivity());
        }
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        ll_check_in = (LinearLayout) findViewById(R.id.bankl_ll_check_in);
        ll_grade = (LinearLayout) findViewById(R.id.bankl_ll_grade);
    }

    @Override
    public void initListener() {
        ll_check_in.setOnClickListener(this);
        ll_grade.setOnClickListener(this);
    }
}

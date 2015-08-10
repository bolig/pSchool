package com.peoit.android.online.pschool.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.ui.Base.BaseFragment;
import com.peoit.android.online.pschool.ui.activity.GradeInfoActivity;

/**
 * 金融IC卡(家长端)
 *
 * <p/>
 * author:libo
 * time:2015/7/30
 * E-mail:boli_android@163.com
 * last: ...
 */
public class BankIC_parent_Fragment extends BaseFragment implements View.OnClickListener {

    TextView tvQueryCheckIn;
    TextView tvExamMonth;
    TextView tvExamMid;
    TextView tvExamEnd;
    TextView tvSignUp;

    @Override
    protected View getCurrentLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.act_bank_ic_parent_fragment, container, false);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        tvQueryCheckIn = (TextView) findViewById(R.id.tv_query_check_in);
        tvExamMonth = (TextView) findViewById(R.id.tv_exam_month);
        tvExamMid = (TextView) findViewById(R.id.tv_exam_mid);
        tvExamEnd = (TextView) findViewById(R.id.tv_exam_end);
        tvSignUp = (TextView) findViewById(R.id.tv_sign_up);
    }

    @Override
    public void initListener() {
        tvExamMonth.setOnClickListener(this);
        tvExamMid.setOnClickListener(this);
        tvExamEnd.setOnClickListener(this);

        tvQueryCheckIn.setOnClickListener(this);

        tvSignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == tvExamMonth) {
            GradeInfoActivity.startThisActivity(getActivity(), GradeInfoActivity.GRADE_MONTH);
        } else if (v == tvExamMid) {
            GradeInfoActivity.startThisActivity(getActivity(), GradeInfoActivity.GRADE_MID);
        } else if (v == tvExamEnd) {
            GradeInfoActivity.startThisActivity(getActivity(), GradeInfoActivity.GRADE_END);
        } else if (v == tvQueryCheckIn) {

        } else if (v == tvSignUp) {

        }
    }
}

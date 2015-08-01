package com.peoit.android.online.pschool.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.ui.Base.BaseActivity;

public class QueryGradeActivity extends BaseActivity implements View.OnClickListener {
    EditText etStuname;
    TextView tvSearch;
    LinearLayout llExamZero;
    LinearLayout llExamMonth;
    LinearLayout llExamMid;
    LinearLayout llExamEnd;

    public static void startThisActivity(Activity mAc) {
        Intent intent = new Intent(mAc, QueryGradeActivity.class);
        mAc.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_query_grade);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        getPsActionBar().settitle(getString(R.string.query_grade_title));

        etStuname = (EditText) findViewById(R.id.et_stuname);
        tvSearch = (TextView) findViewById(R.id.tv_search);
        llExamZero = (LinearLayout) findViewById(R.id.ll_exam_zero);
        llExamMonth = (LinearLayout) findViewById(R.id.ll_exam_month);
        llExamMid = (LinearLayout) findViewById(R.id.ll_exam_mid);
        llExamEnd = (LinearLayout) findViewById(R.id.ll_exam_end);
    }

    @Override
    public void initListener() {
        llExamZero.setOnClickListener(this);
        llExamMonth.setOnClickListener(this);
        llExamMid.setOnClickListener(this);
        llExamEnd.setOnClickListener(this);

        tvSearch.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == llExamZero) {
            showToast(getString(R.string.query_grade_exam_zero));
        } else if (v == llExamMonth) {
            showToast(getString(R.string.query_grade_exam_month));
        } else if (v == llExamMid) {
            showToast(getString(R.string.query_grade_exam_mid));
        } else if (v == llExamEnd) {
            showToast(getString(R.string.query_grade_exam_end));
        } else if (v == tvSearch) {
            showToast(getString(R.string.query_txt));
        }
    }
}

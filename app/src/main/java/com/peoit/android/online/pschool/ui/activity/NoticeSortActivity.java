package com.peoit.android.online.pschool.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.ui.Base.BaseActivity;

/**
 * 通知排序
 * <p/>
 * author:libo
 * time:2015/8/24
 * E-mail:boli_android@163.com
 * last: ...
 */
public class NoticeSortActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout llClassNotice;
    private LinearLayout llOnlineSchoolNotice;
    private LinearLayout llSchoolNotice;

    public static void startThisActivity(Activity mAc) {
        Intent intent = new Intent(mAc, NoticeSortActivity.class);
        mAc.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_notice_sort);
    }

    @Override
    public void initData() {


    }

    @Override
    public void initView() {
        getPsActionBar().settitle("通知");

        llClassNotice = (LinearLayout) findViewById(R.id.ll_class_notice);
        llOnlineSchoolNotice = (LinearLayout) findViewById(R.id.ll_online_school_notice);
        llSchoolNotice = (LinearLayout) findViewById(R.id.ll_school_notice);
    }

    @Override
    public void initListener() {
        llClassNotice.setOnClickListener(this);
        llOnlineSchoolNotice.setOnClickListener(this);
        llSchoolNotice.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == llClassNotice) {
            NoticeActivity.startThisActivity(mContext, 1);
        } else if (v == llOnlineSchoolNotice) {
            NoticeActivity.startThisActivity(mContext, 2);
        } else if (v == llSchoolNotice) {
            NoticeActivity.startThisActivity(mContext, 3);
        }
    }

}

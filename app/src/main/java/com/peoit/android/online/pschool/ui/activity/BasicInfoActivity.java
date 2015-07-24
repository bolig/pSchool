package com.peoit.android.online.pschool.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.ui.Base.BaseActivity;

/**
 * 基本资料
 * Created by ZYZ on 2015/7/16.
 */
public class BasicInfoActivity extends BaseActivity {
    private TextView tv_parentsname,tv_phonenum,tv_name,tv_girl,tv_boy,tv_num,tv_atschool,tv_card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basicinformation);
        getPsActionBar().settitle("基本资料").addRightText("编辑", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("编辑");
            }
        });
    }

    public static void startThisActivity(Activity mAc){
        Intent intent = new Intent(mAc, BasicInfoActivity.class);
        mAc.startActivity(intent);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        tv_parentsname = (TextView) findViewById(R.id.basicinformation_tv1);
        tv_phonenum = (TextView) findViewById(R.id.basicinformation_tv2);
        tv_name = (TextView) findViewById(R.id.basicinformation_tv3);
        tv_girl = (TextView) findViewById(R.id.basicinformation_tv4);
        tv_boy = (TextView) findViewById(R.id.basicinformation_tv5);
        tv_num = (TextView) findViewById(R.id.basicinformation_tv6);
        tv_atschool = (TextView) findViewById(R.id.basicinformation_tv7);
        tv_card = (TextView) findViewById(R.id.basicinformation_tv8);
    }

    @Override
    public void initListener() {

    }
}

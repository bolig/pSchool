package com.peoit.android.online.pschool.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.entity.UserInfo;
import com.peoit.android.online.pschool.ui.Base.BaseActivity;

/**
 * 基本资料
 * Created by ZYZ on 2015/7/16.
 */
public class BasicInfoActivity extends BaseActivity {
    private TextView tv_parentsname,tv_phonenum,tv_name,tv_girl,tv_boy,tv_num,tv_atschool,tv_card;
    private UserInfo user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basicinformation);
        getPsActionBar().settitle("基本资料");

    }

    public static void startThisActivity(Activity mAc){
        Intent intent = new Intent(mAc, BasicInfoActivity.class);
        mAc.startActivity(intent);
    }

    @Override
    public void initData() {

        //获取当前用户的信息
//        user = CommonUtil.getCurrentUser();
        user = getCurrentUser();

    }

    @Override
    public void initView() {
        tv_parentsname = (TextView) findViewById(R.id.basicinformation_tv1);
        tv_phonenum = (TextView) findViewById(R.id.basicinformation_tv2);
        tv_name = (TextView) findViewById(R.id.basicinformation_tv3);
        tv_girl = (TextView) findViewById(R.id.basicinformation_tv4);
//        tv_boy = (TextView) findViewById(R.id.basicinformation_tv5);
        tv_num = (TextView) findViewById(R.id.basicinformation_tv6);
        tv_atschool = (TextView) findViewById(R.id.basicinformation_tv7);
        tv_card = (TextView) findViewById(R.id.basicinformation_tv8);

        tv_parentsname.setText(user.getFatname());//家长姓名
        tv_phonenum.setText(user.getFatmobile());//联系方式（家长）
        tv_name.setText(user.getStuname());//学生姓名
        //学生性别
        if (user.getStusex() == 0)
            tv_girl.setText("男");
        else if (user.getStusex() == 1)
            tv_girl.setText("女");
        //学生编号
        tv_num.setText(user.getStuno());
        //学籍状态
         if (user.getStatus() == 1)
             tv_atschool.setText("入学");
         else if(user.getStatus() == 2)
             tv_atschool.setText("转出");
         else if(user.getStatus() == 3)
             tv_atschool.setText("转入");
         else if(user.getStatus() == 4)
             tv_atschool.setText("退学");
         else if(user.getStatus() == 5)
             tv_atschool.setText("留级");
         else if(user.getStatus() == 6)
             tv_atschool.setText("结业");
         else if(user.getStatus() == 7)
             tv_atschool.setText("毕业");
         else if(user.getStatus() == 8)
             tv_atschool.setText("身亡");
        //卡状态
        if (user.getCardstatus() == 0)
            tv_atschool.setText("正常");
        else if(user.getCardstatus() == 1)
            tv_atschool.setText("挂失");
        else if(user.getCardstatus() == 2)
            tv_atschool.setText("办卡");
    }

    @Override
    public void initListener() {


    }
}

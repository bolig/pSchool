package com.peoit.android.online.pschool.ui.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;

import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.ui.Base.BaseActivity;
import com.peoit.android.online.pschool.ui.fragment.BankIC_parent_Fragment;
import com.peoit.android.online.pschool.ui.fragment.BankIC_teacher_Fragment;

/**
 * 金融IC卡
 *
 * author:libo
 * time:2015/7/9
 * E-mail:boli_android@163.com
 * last: ...
 */
public class BankICActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_bank_ic);
    }

    public static void startThisActivity(Activity mAc){
        Intent intent = new Intent(mAc, BankICActivity.class);
        mAc.startActivity(intent);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        getPsActionBar().settitle("金融IC一卡通");

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ftrans = fm.beginTransaction();
        Fragment fragment = getCurrentFragment();
        ftrans.replace(R.id.fragment, fragment);
        ftrans.commit();
    }

    boolean isParent = false;

    private Fragment getCurrentFragment() {
        return isParent ? new BankIC_parent_Fragment() : new BankIC_teacher_Fragment();
    }

    @Override
    public void initListener() {

    }

}

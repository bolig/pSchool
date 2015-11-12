package com.peoit.android.online.pschool.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.ui.Base.BaseActivity;
import com.peoit.android.online.pschool.ui.Presenter.GroupPresenter;

public class GroupActivity extends BaseActivity implements View.OnClickListener {
    private ListView lvUsers;
    private GroupPresenter mPresenter;

    public static void startThisActivity(Activity mAc){
        Intent intent = new Intent(mAc, GroupActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        mAc.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_group);
        initData();
        initView();
        initListener();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        getPsActionBar().settitle("成员信息")/*.addRightBtn(R.drawable.chat_sel, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        })*/;
        lvUsers = (ListView) findViewById(R.id.lv_users);
        mPresenter = new GroupPresenter(this);
        lvUsers.setAdapter(mPresenter.getAdapter());
        mPresenter.doLoadUserGroup();
    }

    @Override
    public void initListener() {
    }

    @Override
    public void onClick(View v) {
    }
}

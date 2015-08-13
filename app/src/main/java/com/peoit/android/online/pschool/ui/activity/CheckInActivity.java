package com.peoit.android.online.pschool.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.ui.Base.BaseActivity;
import com.peoit.android.online.pschool.ui.Presenter.CheckInPresenter;

/**
 * 考勤查询
 */
public class CheckInActivity extends BaseActivity {

    private ListView list;
    private CheckInPresenter mPresenter;

    public static void startThisActivity(Activity mAc){
        Intent intent = new Intent(mAc, CheckInActivity.class);
        mAc.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_list_layout_nopadding);
    }

    @Override
    public void initData() {
        mPresenter = new CheckInPresenter(this);
    }

    @Override
    public void initView() {
        getPsActionBar().settitle("考勤查询");

        list = (ListView) findViewById(R.id.list);
        list.setAdapter(mPresenter.getAdapter());
        mPresenter.doLoadCheckIn();
    }

    @Override
    public void initListener() {

    }
}

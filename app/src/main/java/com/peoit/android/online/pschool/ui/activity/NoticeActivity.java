package com.peoit.android.online.pschool.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.ui.Base.BaseActivity;
import com.peoit.android.online.pschool.ui.Presenter.NoticePersenter;
import com.peoit.android.online.pschool.ui.adapter.NoticeAdapter;
import com.peoit.android.online.pschool.ui.view.PullToRefreshLayout;
import com.peoit.android.online.pschool.ui.view.PullableListView;

public class NoticeActivity extends BaseActivity {

    private PullToRefreshLayout pullLayout;
    private PullableListView pullList;

    private NoticeAdapter adapter;
    private NoticePersenter mPersenter;

    private int type;

    public static void startThisActivity(Activity mAc, int type){
        Intent intent = new Intent(mAc, NoticeActivity.class);
        intent.putExtra("type", type);
        mAc.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_pulllist_layout_nopadding);

    }

    @Override
    public void initData() {
        type = getIntent().getIntExtra("type",-1);

        mPersenter = new NoticePersenter(this);
        adapter = mPersenter.getNoticeAdapter();

    }

    @Override
    public void initView() {
        pullLayout = (PullToRefreshLayout) findViewById(R.id.pull_layout);
        pullList = (PullableListView) findViewById(R.id.pull_list);
        pullList.setAdapter(adapter);
        if (type == 1) {
            getPsActionBar().settitle("班级通知");
            mPersenter.load("2");
        }
        else if (type == 2) {
            getPsActionBar().settitle("网校通知");
            mPersenter.load("1");
        }
        else {
            getPsActionBar().settitle("学校通知");
            mPersenter.load("3");
        }

    }

    @Override
    public void initListener() {
        pullLayout.setOnRefreshListener(mPersenter);
    }
}

package com.peoit.android.online.pschool.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.ui.Base.BaseActivity;
import com.peoit.android.online.pschool.ui.Presenter.ExpertListPresenter;
import com.peoit.android.online.pschool.ui.view.PullToRefreshLayout;
import com.peoit.android.online.pschool.ui.view.PullableListView;

public class ExpertListActivity extends BaseActivity {
    private PullToRefreshLayout pullLayout;
    private PullableListView pullList;
    private ExpertListPresenter mExpertListPresenter;

    private void assignViews() {
        pullLayout = (PullToRefreshLayout) findViewById(R.id.pull_layout);
        pullList = (PullableListView) findViewById(R.id.pull_list);
    }

    public static void startThisActivity(Activity mAc){
        Intent intent = new Intent(mAc, ExpertListActivity.class);
        mAc.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_pulllist_layout_nopadding);
    }

    @Override
    public void initData() {
        mExpertListPresenter = new ExpertListPresenter(this);
    }

    @Override
    public void initView() {
        assignViews();
        getPsActionBar().settitle("专家列表");
        pullLayout.setBackgroundColor(getResources().getColor(R.color.white_));
        pullList.setAdapter(mExpertListPresenter.getAdapter());
        mExpertListPresenter.loadExpertList(null);
        pullLayout.setOnRefreshListener(mExpertListPresenter);
    }

    @Override
    public void initListener() {

    }
}

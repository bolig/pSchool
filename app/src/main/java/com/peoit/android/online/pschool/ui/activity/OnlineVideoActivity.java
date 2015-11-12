package com.peoit.android.online.pschool.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.ui.Base.BaseActivity;
import com.peoit.android.online.pschool.ui.Presenter.VideoListPresenter;
import com.peoit.android.online.pschool.ui.view.PullToRefreshLayout;
import com.peoit.android.online.pschool.ui.view.PullableListView;

public class OnlineVideoActivity extends BaseActivity {
    private PullToRefreshLayout pullLayout;
    private PullableListView pullList;
    private VideoListPresenter mVideoPresenter;

    public static void startThisActivity(Activity mAc){
        Intent intent = new Intent(mAc, OnlineVideoActivity.class);
        mAc.startActivity(intent);
    }

    private void assignViews() {
        pullLayout = (PullToRefreshLayout) findViewById(R.id.pull_layout);
        pullList = (PullableListView) findViewById(R.id.pull_list);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_pulllist_layout_nopadding);
    }

    @Override
    public void initData() {
        mVideoPresenter = new VideoListPresenter(this);
    }

    @Override
    public void initView() {
        assignViews();
        getPsActionBar().settitle("网校视频");
        pullList.setAdapter(mVideoPresenter.getVideoAdapter());
        mVideoPresenter.loadVedio(null);
    }

    @Override
    public void initListener() {
        pullLayout.setOnRefreshListener(mVideoPresenter);
    }
}

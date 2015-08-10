package com.peoit.android.online.pschool.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.entity.NoticeInfo;
import com.peoit.android.online.pschool.ui.Base.BaseActivity;
import com.peoit.android.online.pschool.ui.adapter.NoticeAdapter;
import com.peoit.android.online.pschool.ui.view.PullToRefreshLayout;
import com.peoit.android.online.pschool.ui.view.PullableListView;

import java.util.ArrayList;
import java.util.List;

/**
 *校园通知
 */
public class NoticeActivity extends BaseActivity {
    private PullToRefreshLayout pullLayout;
    private PullableListView pullList;
    private NoticeAdapter adapter;

    public static void startThisActivity(Activity mAc){
        Intent intent = new Intent(mAc, NoticeActivity.class);
        mAc.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_pulllist_layout_nopadding);
        getPsActionBar().settitle("网校通知");
    }

    private List<NoticeInfo> infos = new ArrayList<>();

    @Override
    public void initData() {
        infos.add(new NoticeInfo("123", "123123123123123123123123123123123123123123123123123123123123123123123123123123123213123123", "2015-8-7"));
        infos.add(new NoticeInfo("123", "123123123123123123123123123123123123123123123123123123123123123123123123123123123213123123", "2015-8-7"));
        infos.add(new NoticeInfo("123", "123123123123123123123123123123123123123123123123123123123123123123123123123123123213123123", "2015-8-7"));
        infos.add(new NoticeInfo("123", "123123123123123123123123123123123123123123123123123123123123123123123123123123123213123123", "2015-8-7"));
        infos.add(new NoticeInfo("123", "123123123123123123123123123123123123123123123123123123123123123123123123123123123213123123", "2015-8-7"));
        infos.add(new NoticeInfo("123", "123123123123123123123123123123123123123123123123123123123123123123123123123123123213123123", "2015-8-7"));
        infos.add(new NoticeInfo("123", "123123123123123123123123123123123123123123123123123123123123123123123123123123123213123123", "2015-8-7"));
        infos.add(new NoticeInfo("123", "123123123123123123123123123123123123123123123123123123123123123123123123123123123213123123", "2015-8-7"));
        infos.add(new NoticeInfo("123", "123123123123123123123123123123123123123123123123123123123123123123123123123123123213123123", "2015-8-7"));
        infos.add(new NoticeInfo("123", "123123123123123123123123123123123123123123123123123123123123123123123123123123123213123123", "2015-8-7"));
        infos.add(new NoticeInfo("123", "123123123123123123123123123123123123123123123123123123123123123123123123123123123213123123", "2015-8-7"));
        infos.add(new NoticeInfo("123", "123123123123123123123123123123123123123123123123123123123123123123123123123123123213123123", "2015-8-7"));
    }

    @Override
    public void initView() {
        pullLayout = (PullToRefreshLayout) findViewById(R.id.pull_layout);
        pullList = (PullableListView) findViewById(R.id.pull_list);
        adapter = new NoticeAdapter(mContext, R.layout.act_notice_item, infos);
        pullList.setAdapter(adapter);
    }

    @Override
    public void initListener() {
        pullLayout.setOnRefreshListener(new PullToRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {

            }

            @Override
            public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {

            }
        });
    }
}

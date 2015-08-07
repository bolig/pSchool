package com.peoit.android.online.pschool.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.entity.SchoolDataInfo;
import com.peoit.android.online.pschool.ui.Base.BaseActivity;
import com.peoit.android.online.pschool.ui.adapter.SchoolInfoAdapter;
import com.peoit.android.online.pschool.ui.view.PullToRefreshLayout;
import com.peoit.android.online.pschool.ui.view.PullableListView;

import java.util.ArrayList;
import java.util.List;

/**
 * 登陆界面
 *
 * author:libo
 * time:2015/7/9
 * E-mail:boli_android@163.com
 * last: ...
 */
public class SchoolInfoActivity extends BaseActivity {

    PullToRefreshLayout pullLayout;
    PullableListView pullList;

    public List<SchoolDataInfo> dataInfos = new ArrayList<>();
    private SchoolInfoAdapter schoolInfoAdapter;

    public static void startThisActivity(Activity mAc){
        Intent intent = new Intent(mAc, SchoolInfoActivity.class);
        mAc.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_pulllist_layout);
    }

    @Override
    public void initData() {
        dataInfos.add(new SchoolDataInfo());
        dataInfos.add(new SchoolDataInfo());
        dataInfos.add(new SchoolDataInfo());
        dataInfos.add(new SchoolDataInfo());
        dataInfos.add(new SchoolDataInfo());
        dataInfos.add(new SchoolDataInfo());
        dataInfos.add(new SchoolDataInfo());
        dataInfos.add(new SchoolDataInfo());
        dataInfos.add(new SchoolDataInfo());
        dataInfos.add(new SchoolDataInfo());
        dataInfos.add(new SchoolDataInfo());
    }

    @Override
    public void initView() {
        getPsActionBar().settitle("校园信息");

        pullLayout = (PullToRefreshLayout) findViewById(R.id.pull_layout);
        pullList = (PullableListView) findViewById(R.id.pull_list);

        schoolInfoAdapter = new SchoolInfoAdapter(mContext, R.layout.act_school_info_item, dataInfos);
        pullList.setAdapter(schoolInfoAdapter);
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

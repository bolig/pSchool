package com.peoit.android.online.pschool.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.ui.Base.BaseActivity;
import com.peoit.android.online.pschool.ui.Presenter.ParentClassroomPresenter;
import com.peoit.android.online.pschool.ui.view.PullToRefreshLayout;
import com.peoit.android.online.pschool.ui.view.PullableListView;

/**
 * 亲子活动
 * Created by zyz on 2015/8/11.
 */
public class FamilyActivitiy extends BaseActivity{
    private PullableListView list;
    private PullToRefreshLayout refreshLayout;
    private ParentClassroomPresenter featurePersenter;
    public static void startThisActivity(Activity mAc){
        Intent intent = new Intent(mAc, FamilyActivitiy.class);
        mAc.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_pulllist_layout_nopadding);
        getPsActionBar().settitle("亲子活动");
    }
    @Override
    public void initData() {
        featurePersenter = new ParentClassroomPresenter(this,"亲子活动");
        featurePersenter.load();
    }

    @Override
    public void initView() {
        list = (PullableListView) findViewById(R.id.pull_list);
        refreshLayout = (PullToRefreshLayout) findViewById(R.id.pull_layout);
        list.setAdapter(featurePersenter.getAdapter());
    }

    @Override
    public void initListener() {
        refreshLayout.setOnRefreshListener(featurePersenter);
    }
}

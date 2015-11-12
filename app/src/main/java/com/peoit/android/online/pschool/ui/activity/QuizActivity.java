package com.peoit.android.online.pschool.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.config.CommonUtil;
import com.peoit.android.online.pschool.ui.Base.BaseActivity;
import com.peoit.android.online.pschool.ui.Presenter.ExpertsOnlinePresenter;
import com.peoit.android.online.pschool.ui.view.PullToRefreshLayout;
import com.peoit.android.online.pschool.ui.view.PullableListView;

public class QuizActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout llExpert;
    private LinearLayout llMyQ;
    private LinearLayout llClassNotice;
    private TextView tv_myQ;

    private PullableListView list;
    private PullToRefreshLayout refreshLayout;
    private ExpertsOnlinePresenter featurePersenter;

    private void assignViews() {
        list = (PullableListView) findViewById(R.id.pull_list);
        refreshLayout = (PullToRefreshLayout) findViewById(R.id.pull_layout);
    }

    public static void startThisActivity(Activity mAc) {
        Intent intent = new Intent(mAc, QuizActivity.class);
        mAc.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_pulllist_layout_nopadding);
    }

    @Override
    public void initData() {
        featurePersenter = new ExpertsOnlinePresenter(this, "专家在线");
    }

    @Override
    public void initView() {
        assignViews();
        addListFooter();
        getPsActionBar().settitle("专家提问");
        if (CommonUtil.getIdEntityType() == 3) {
            tv_myQ.setText("我的问题");
            llClassNotice.setVisibility(View.GONE);
        } else {
            tv_myQ.setText("我的提问");
            llClassNotice.setVisibility(View.VISIBLE);
        }
        list.setAdapter(featurePersenter.getAdapter());
        featurePersenter.load();
    }

    private void addListFooter() {
        View view = getLayoutInflater().inflate(R.layout.act_quiz_list_header, null);
        llExpert = (LinearLayout) view.findViewById(R.id.ll_expert);
        llMyQ = (LinearLayout) view.findViewById(R.id.ll_myQ);
        llClassNotice = (LinearLayout) view.findViewById(R.id.ll_class_notice);

        tv_myQ = (TextView) view.findViewById(R.id.tv_myQ);
        list.addHeaderView(view);
    }

    @Override
    public void initListener() {
        llExpert.setOnClickListener(this);
        llMyQ.setOnClickListener(this);
        llClassNotice.setOnClickListener(this);
        refreshLayout.setOnRefreshListener(featurePersenter);
    }

    @Override
    public void onClick(View v) {
        if (v == llExpert) {
            ExpertListActivity.startThisActivity(mContext);
        } else if (v == llMyQ) {
            ExpertsOnlineActivity.startThisActivity(mContext);
        } else if (v == llClassNotice) {
            AddQActivity.startThisActivity(mContext);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            featurePersenter.load();
        }
    }
}

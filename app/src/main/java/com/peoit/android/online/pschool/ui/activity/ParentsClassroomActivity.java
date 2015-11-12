package com.peoit.android.online.pschool.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.ui.Base.BaseActivity;
import com.peoit.android.online.pschool.ui.Presenter.ParentClassPresenter;
import com.peoit.android.online.pschool.ui.view.PullToRefreshLayout;
import com.peoit.android.online.pschool.ui.view.PullableListView;

/**
 * 家长课堂
 * Created by zyz on 2015/8/10.
 */
public class ParentsClassroomActivity extends BaseActivity {
    private PullableListView list;
    private PullToRefreshLayout refreshLayout;

    private ParentClassPresenter featurePersenter;
    private String mClassVersion;

    public static void startThisActivity(Activity mAc, String classVersion){
        Intent intent = new Intent(mAc, ParentsClassroomActivity.class);
        intent.putExtra("ver", classVersion);
        mAc.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_pulllist_layout_nopadding);
        getPsActionBar().settitle("家长课堂");
    }

    @Override
    public void initData() {
        mClassVersion = getIntent().getStringExtra("ver");
        if (TextUtils.isEmpty(mClassVersion)) {
            showToast("数据传输错误");
            finish();
            return;
        }
        featurePersenter = new ParentClassPresenter(this);
        featurePersenter.load(mClassVersion);
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

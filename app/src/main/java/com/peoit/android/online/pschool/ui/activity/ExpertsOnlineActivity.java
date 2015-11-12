package com.peoit.android.online.pschool.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.config.CommonUtil;
import com.peoit.android.online.pschool.config.Constants;
import com.peoit.android.online.pschool.ui.Base.BaseActivity;
import com.peoit.android.online.pschool.ui.Presenter.AddQandAPresenter;
import com.peoit.android.online.pschool.ui.Presenter.ExpertsOnlinePresenter;
import com.peoit.android.online.pschool.ui.view.PullToRefreshLayout;
import com.peoit.android.online.pschool.ui.view.PullableListView;
import com.peoit.android.online.pschool.utils.MyLogger;

/**
 * 专家在线
 * Created by zyz on 2015/8/11.
 */
public class ExpertsOnlineActivity extends BaseActivity {
    private PullableListView list;
    private PullToRefreshLayout refreshLayout;
    private ExpertsOnlinePresenter featurePersenter;

    private AddQandAPresenter mPresenter;//提问

    public static void startThisActivity(Activity mAc) {
        Intent intent = new Intent(mAc, ExpertsOnlineActivity.class);
        mAc.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_pulllist_layout_nopadding);
        mPresenter = new AddQandAPresenter(this);
        int type = CommonUtil.getIdEntityType();
        MyLogger.i(">>>>>>>>>>>" + type);
        if (type == Constants.TYPE_ZHUAN_JIA || type == Constants.TYPE_ZHUAN_JIA1) {
            getPsActionBar().settitle("我的问题");
        } else {
            getPsActionBar().settitle("我的提问")/*.addRightText("提问", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AddQActivity.startThisActivity(mContext);
                }
            })*/;
        }
    }

    @Override
    public void initData() {
        featurePersenter = new ExpertsOnlinePresenter(this, "专家在线");
    }

    @Override
    public void initView() {
        list = (PullableListView) findViewById(R.id.pull_list);
        refreshLayout = (PullToRefreshLayout) findViewById(R.id.pull_layout);
        list.setAdapter(featurePersenter.getAdapter());
        featurePersenter.load();
    }

    @Override
    public void initListener() {
        refreshLayout.setOnRefreshListener(featurePersenter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            featurePersenter.load();
        }
    }
}

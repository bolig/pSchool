package com.peoit.android.online.pschool.ui.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;

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
public class ExpertsOnlineActivity extends BaseActivity implements View.OnClickListener{
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
        if (type == Constants.TYPE_ZHUAN_JIA || type == Constants.TYPE_ZHUAN_JIA1){
            getPsActionBar().settitle("专家在线");
        }else {
            getPsActionBar().settitle("专家在线").addRightText("提问", this);
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
        featurePersenter.load();
        list.setAdapter(featurePersenter.getAdapter());

    }

    @Override
    public void initListener() {
        refreshLayout.setOnRefreshListener(featurePersenter);
    }

/*    @Override
    protected void onResume() {
        super.onResume();
    }*/

    @Override
    public void onClick(View v) {
        final EditText editText=new EditText(mContext);
        new AlertDialog.Builder(mContext).
                setTitle("请输入对专家的问题").
                setIcon(R.mipmap.nocolumquestionimage).
                setView(editText).
                setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if ("".equals(editText.getText().toString())) {
                            showToast("提问内容不能为空");
                        }
                        else {
//                                    showToast("提问确认:" + editText.getText().toString());
                            mPresenter.doAddQ("40", editText.getText().toString());
//                            onResume();
                            initView();
                        }
                    }
                }).show();
    }
}

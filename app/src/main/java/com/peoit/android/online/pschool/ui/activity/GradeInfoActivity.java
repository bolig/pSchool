package com.peoit.android.online.pschool.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.ui.Base.BaseActivity;
import com.peoit.android.online.pschool.ui.Presenter.GradeInfoPersenter;
import com.peoit.android.online.pschool.ui.adapter.GradeStatAdapter;
import com.peoit.android.online.pschool.ui.view.PullToRefreshLayout;
import com.peoit.android.online.pschool.ui.view.PullableListView;

import java.util.Map;

public class GradeInfoActivity extends BaseActivity {

    public static final int GRADE_MONTH = 2;
    public static final int GRADE_MID = 3;
    public static final int GRADE_END = 4;

    private EditText etStart;
    private EditText etEnd;
    private TextView tvSearch;
    private GradeInfoPersenter mPersenter;
    private String start_time;
    private String end_time;

    private PullToRefreshLayout pullLayout;
    private PullableListView pullList;
    private GradeStatAdapter adapter;
    private int type;
    private String title;

    public static void startThisActivity(Activity mAc, int type){
        Intent intent = new Intent(mAc, GradeInfoActivity.class);
        intent.putExtra("type", type);
        mAc.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_grade_info);
    }

    @Override
    public void initData() {

        type = getIntent().getIntExtra("type", -1);

        switch (type) {
            case GRADE_MONTH:
                title = "月考考试";
                break;
            case GRADE_MID:
                title = "期中考试";
                break;
            case GRADE_END:
                title = "期末考试";
                break;
            default: {
                showToast("传输错误");
                finish();
            }
                break;
        }

        mPersenter = new GradeInfoPersenter(this) {
            @Override
            public Map<String, String> getGradeInfoParam(Map<String, String> params) {
                params.put("stime", start_time);
                params.put("endtime", end_time);
                return params;
            }
        };

        this.adapter = mPersenter.getAdapter(title);
    }

    @Override
    public void initView() {
        etStart = (EditText) findViewById(R.id.et_start);
        etEnd = (EditText) findViewById(R.id.et_end);
        tvSearch = (TextView) findViewById(R.id.tv_search);

        pullLayout = (PullToRefreshLayout) findViewById(R.id.pull_layout);
        pullList = (PullableListView) findViewById(R.id.pull_list);
        pullList.setAdapter(adapter);
    }

    private void setEtWidth(EditText et){

    }

    @Override
    public void initListener() {
        tvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (match()){
                    mPersenter.load();
                }
            }
        });
    }

    private boolean match() {
        start_time = etStart.getText().toString();
        if (TextUtils.isEmpty(start_time)) {
            showToast("请输入开始时间");
            return false;
        }
        end_time = etEnd.getText().toString();
        if (TextUtils.isEmpty(end_time)) {
            showToast("请输入结束时间");
            return false;
        }
        return true;
    }
}

package com.peoit.android.online.pschool.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.ui.Base.BaseActivity;
import com.peoit.android.online.pschool.ui.Presenter.PushPresenter;
import com.peoit.android.online.pschool.utils.MyLogger;

import org.json.JSONException;
import org.json.JSONObject;

public class PushActivity extends BaseActivity {

    private TextView tvTitle;
    private TextView tvContent;
    private String data;
    private String msg;
    private String id;
    private PushPresenter mPresenter;
    private TextView tvTime;

    public static void startThisActivity(Context mContext, String msg, String data){
        Intent intent = new Intent(mContext, PushActivity.class);
        intent.putExtra("msg", msg);
        intent.putExtra("data", data);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_push);
    }

    @Override
    public void initData() {
        data = getIntent().getStringExtra("data");
        msg = getIntent().getStringExtra("msg");
        if (TextUtils.isEmpty(data)) {
            showToast("数据传输错误");
            finish();
        }
        MyLogger.i("data = " + data);
        try {
            JSONObject json = new JSONObject(data);
            id = json.getString("id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (TextUtils.isEmpty(id)) {
            showToast("数据传输错误");
            finish();
        }
        mPresenter = new PushPresenter(this) {
            @Override
            protected void onSimpleSuccess(String title, String content, String stimeStr) {
                tvTitle.setText(title);
                tvContent.setText(content);
                tvTime.setText(stimeStr);
            }
        };
    }

    @Override
    public void initView() {
        getPsActionBar().settitle("消息通知");

        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvContent = (TextView) findViewById(R.id.tv_content);
        tvTime = (TextView) findViewById(R.id.tv_time);
        mPresenter.doQueryPushInfo(id);
    }

    @Override
    public void initListener() {

    }
}

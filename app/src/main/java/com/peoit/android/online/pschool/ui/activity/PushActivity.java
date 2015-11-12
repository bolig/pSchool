package com.peoit.android.online.pschool.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.WebView;
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

    public static final int PUSH = 1;
    public static final int NOTICE = 2;

    private int curType = 0;

    private String title;
    private String content;
    private String stimeStr;
    private WebView wv_news;

    public static void startThisActivity(Context mContext, int type, String msg, String data) {
        Intent intent = new Intent(mContext, PushActivity.class);
        intent.putExtra("type", type);
        intent.putExtra("msg", msg);
        intent.putExtra("data", data);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
    }

    public static void startThisActivity(Activity mAc, int type, String title, String content, String stimeStr) {
        Intent intent = new Intent(mAc, PushActivity.class);
        intent.putExtra("type", type);
        intent.putExtra("title", title);
        intent.putExtra("content", content);
        intent.putExtra("stimeStr", stimeStr);
        mAc.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_push);
    }

    @Override
    public void initData() {
        curType = getIntent().getIntExtra("type", 0);
        if (curType == PUSH)
            initPush();
        else if (curType == NOTICE)
            initNotice();
        else {
            showToast("数据传输错误");
            finish();
        }
    }

    private void initNotice() {
        title = getIntent().getStringExtra("title");
        content = getIntent().getStringExtra("content");
        stimeStr = getIntent().getStringExtra("stimeStr");
    }

    private void initPush() {
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
        wv_news = (WebView) findViewById(R.id.wv_news);

        if (curType == PUSH) {
            mPresenter.doQueryPushInfo(id);
        } else if (curType == NOTICE){
            tvTitle.setText(title);
            tvContent.setText(content);
            tvTime.setText(stimeStr);
        }
    }

    @Override
    public void initListener() {

    }
}

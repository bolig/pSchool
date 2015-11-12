package com.peoit.android.online.pschool.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.config.NetConstants;
import com.peoit.android.online.pschool.ui.Base.BaseActivity;

public class ExpertDetActivity extends BaseActivity {
    private WebView wvNews;
    private String mExpectDet;
    private String mUserName;

    private void assignViews() {
        wvNews = (WebView) findViewById(R.id.wv_news);
    }

    public static void startThisActivity(Activity mAc, String content, String name) {
        Intent intent = new Intent(mAc, ExpertDetActivity.class);
        intent.putExtra("det", content);
        intent.putExtra("name", name);
        mAc.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_expert_det);
    }

    @Override
    public void initData() {
        mExpectDet = getIntent().getStringExtra("det");
        mUserName = getIntent().getStringExtra("name");
    }

    @Override
    public void initView() {
        assignViews();
        getPsActionBar().settitle(mUserName + "的简介");
        if (TextUtils.isEmpty(mExpectDet)) {
            getUIShowPresenter().doShowNodata(R.drawable.nocolumimage);
        } else {
            addWebHtml(mExpectDet);
        }
    }

    private void addWebHtml(String html) {
        wvNews.loadDataWithBaseURL(NetConstants.IMAGE_HOST, html, "text/html", "utf-8", null);
        wvNews.getSettings().setJavaScriptEnabled(true);
        wvNews.setWebChromeClient(new WebChromeClient());
    }

    @Override
    public void initListener() {

    }
}

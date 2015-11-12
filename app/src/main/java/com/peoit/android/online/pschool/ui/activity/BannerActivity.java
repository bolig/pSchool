package com.peoit.android.online.pschool.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.WebView;

import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.ui.Base.BaseActivity;
import com.peoit.android.online.pschool.ui.Presenter.BannerPresenter;

public class BannerActivity extends BaseActivity {

//    private TextView tvNews;
    private String mBannerTitle;
    private long mBannerId;
    private BannerPresenter mPresenter;
    private WebView wv_news;

    public static void startThisActivity(Activity mAc, String title, long id){
        Intent intent = new Intent(mAc, BannerActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("id", id);
        mAc.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_banner);
    }

    @Override
    public void initData() {
        mBannerTitle = getIntent().getStringExtra("title");
        mBannerId = getIntent().getLongExtra("id", -1l);
        if (TextUtils.isEmpty(mBannerTitle) || mBannerId == -1) {
            showToast("数据传输错误");
            finish();
            return;
        }
    }

    @Override
    public void initView() {
        getPsActionBar().settitle(mBannerTitle);

//        tvNews = (TextView) findViewById(R.id.tv_news);
//        mPresenter = new BannerPresenter(this, tvNews);

        wv_news = (WebView) findViewById(R.id.wv_news);
        mPresenter = new BannerPresenter(this, wv_news);
        mPresenter.doLoadBannerInfo(mBannerId);
    }

    @Override
    public void initListener() {

    }
}

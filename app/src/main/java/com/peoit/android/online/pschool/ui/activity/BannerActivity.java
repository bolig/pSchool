package com.peoit.android.online.pschool.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.ui.Base.BaseActivity;
import com.peoit.android.online.pschool.ui.Presenter.BannerPresenter;

public class BannerActivity extends BaseActivity {

    private TextView tvNews;
    private String mBannerTitle;
    private int mBannerId;
    private BannerPresenter mPresenter;

    public static void startThisActivity(Activity mAc, String title, int id){
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
        mBannerId = getIntent().getIntExtra("id", -1);
        if (TextUtils.isEmpty(mBannerTitle) || mBannerId == -1) {
            showToast("数据传输错误");
            finish();
            return;
        }
    }

    @Override
    public void initView() {
        getPsActionBar().settitle(mBannerTitle);

        tvNews = (TextView) findViewById(R.id.tv_news);
        mPresenter = new BannerPresenter(this, tvNews);
        mPresenter.doLoadBannerInfo(mBannerId);
    }

    @Override
    public void initListener() {

    }
}

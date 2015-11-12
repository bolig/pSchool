package com.peoit.android.online.pschool.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.config.CommonUtil;
import com.peoit.android.online.pschool.config.NetConstants;
import com.peoit.android.online.pschool.entity.ExpertListInfo;
import com.peoit.android.online.pschool.ui.Base.BaseActivity;
import com.peoit.android.online.pschool.ui.Presenter.ExpertDetsPresenter;
import com.peoit.android.online.pschool.ui.view.CircleImageView;

import java.util.ArrayList;

import me.iwf.photopicker.PhotoPagerActivity;

public class ExpertDetsActivity extends BaseActivity {
    private CircleImageView ivAvater;
    private TextView tvUserName;
    private LinearLayout llTab1;
    private LinearLayout llTab2;
    private WebView wvNews;
    private ListView lvWenz;
    private ExpertListInfo mExpertListInfo;
    private ExpertDetsPresenter mFeaturePersenter;
    private int mTabWidth;
    private Type lastType;

    private void assignViews() {
        ivAvater = (CircleImageView) findViewById(R.id.iv_avater);
        tvUserName = (TextView) findViewById(R.id.tv_userName);
        llTab1 = (LinearLayout) findViewById(R.id.ll_tab1);
        llTab2 = (LinearLayout) findViewById(R.id.ll_tab2);
        wvNews = (WebView) findViewById(R.id.wv_news);
        lvWenz = (ListView) findViewById(R.id.lv_wenz);
    }

    public static void startThisActivity(Activity mAc, ExpertListInfo info) {
        Intent intent = new Intent(mAc, ExpertDetsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("info", info);
        intent.putExtras(bundle);
        mAc.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_expert_dets);
    }

    @Override
    public void initData() {
        mExpertListInfo = (ExpertListInfo) getIntent().getSerializableExtra("info");
        if (mExpertListInfo == null) {
            showToast("数据传输错误");
            finish();
            return;
        }
        mFeaturePersenter = new ExpertDetsPresenter(this);
        mTabWidth = CommonUtil.w_screeen / 2;
    }

    @Override
    public void initView() {
        assignViews();
        getPsActionBar().settitle(mExpertListInfo.getNickname());
        Glide.with(mContext).load(NetConstants.IMAGE_HOST + mExpertListInfo.getZjpic()).error(R.drawable.user_avater).into(ivAvater);
        tvUserName.setText(mExpertListInfo.getNickname());
        lvWenz.setAdapter(mFeaturePersenter.getAdapter());

        mFeaturePersenter.load(mExpertListInfo.getId());

        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) llTab1.getLayoutParams();
        params.width = mTabWidth;
        params = (LinearLayout.LayoutParams) llTab2.getLayoutParams();
        params.width = mTabWidth;

        addWebHtml(mExpertListInfo.getZjjj());
        changeUIShow(Type.tab1);
    }

    private void addWebHtml(String html) {
        wvNews.loadDataWithBaseURL(NetConstants.IMAGE_HOST, html, "text/html", "utf-8", null);
        wvNews.getSettings().setJavaScriptEnabled(true);
        wvNews.getSettings().setBuiltInZoomControls(true);
        wvNews.getSettings().setSupportZoom(true);
        wvNews.setSaveEnabled(true);
        wvNews.setWebChromeClient(new WebChromeClient());
    }

    @Override
    public void initListener() {
        llTab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeUIShow(Type.tab1);
            }
        });
        llTab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeUIShow(Type.tab2);
            }
        });
        ivAvater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(mExpertListInfo.getZjpic())) {
                    showToast("该专家还没有上传头像");
                } else {
//                    ImageBrowActivity.startThisActivity(mContext, NetConstants.IMAGE_HOST + mExpertListInfo.getZjpic(), true);
                    Intent intent = new Intent(mContext, PhotoPagerActivity.class);
                    intent.putExtra(PhotoPagerActivity.EXTRA_CURRENT_ITEM, 0);
                    ArrayList<String> imgs = new ArrayList<String>();
                    imgs.add(NetConstants.IMAGE_HOST + mExpertListInfo.getZjpic());
                    intent.putExtra(PhotoPagerActivity.EXTRA_PHOTOS, imgs);
                    mContext.startActivityForResult(intent, 2);
                }
            }
        });
    }

    private void changeUIShow(Type type) {
        if (type == lastType) {
            return;
        }
        lastType = type;
        llTab1.setSelected(false);
        llTab2.setSelected(false);
        wvNews.setVisibility(View.GONE);
        lvWenz.setVisibility(View.GONE);
        switch (type) {
            case tab1:
                llTab1.setSelected(true);
                wvNews.setVisibility(View.VISIBLE);
                break;
            case tab2:
                llTab2.setSelected(true);
                lvWenz.setVisibility(View.VISIBLE);
                break;
        }
    }

    private enum Type {
        tab1,
        tab2
    }
}

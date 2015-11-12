package com.peoit.android.online.pschool.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.config.NetConstants;
import com.peoit.android.online.pschool.entity.FeatureInfo;
import com.peoit.android.online.pschool.ui.Base.BaseActivity;

public class NewsActivity extends BaseActivity implements View.OnClickListener {

    private FeatureInfo featureInfo;

    private boolean isFeat = false;

    private String data;
    private String title;
    private String newsTitle;
    private int id;

    private TextView tvTitle;
    private TextView tvTime;
    private RelativeLayout rlVideo;
    private ImageView ivVideo;
    private TextView tvNews;
    private EditText etStuname;
    private TextView tvSearch;
    private String time;
    private WebView wv_news;

//    public static void startThisActivity(Activity mAc, int id, String content, String title, String newsTitle, String time) {
//        Intent intent = new Intent(mAc, NewsActivity.class);
//        Bundle bundle = new Bundle();
//        bundle.putString("data", content);
//        bundle.putString("title", title);
//        bundle.putString("newsTitle", newsTitle);
//        bundle.putInt("id", id);
//        bundle.putString("time", time);
//        intent.putExtras(bundle);
//        mAc.startActivity(intent);
//    }

    public static void startThisActivity(Activity mAc, FeatureInfo info) {
        Intent intent = new Intent(mAc, NewsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("info", info);
        bundle.putInt("type", 0);
        mAc.startActivity(intent.putExtras(bundle));
    }

//    public static void startThisActivity(Activity mAc, ExpertsOnlineInfo info){
//        Intent intent = new Intent(mAc, NewsActivity.class);
//        Bundle bundle = new Bundle();
//        bundle.putSerializable("info", info);
//        mAc.startActivity(intent.putExtras(bundle));
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_news);
    }

    @Override
    public void initData() {
        int type = getIntent().getIntExtra("type", -1);
        isFeat = type == 0;
        if (isFeat) {
            featureInfo = (FeatureInfo) getIntent().getSerializableExtra("info");
            if (featureInfo == null) {
                showToast("数据传输错误");
                finish();
                return;
            }
        } else {
            data = getIntent().getStringExtra("data");
            title = getIntent().getStringExtra("title");
            newsTitle = getIntent().getStringExtra("newsTitle");
            id = getIntent().getIntExtra("id", -1);
            time = getIntent().getStringExtra("time");
            if (TextUtils.isEmpty(data) || TextUtils.isEmpty(title) || TextUtils.isEmpty(newsTitle) || id == -1 || TextUtils.isEmpty(time)) {
                showToast("数据传输错误");
                finish();
                return;
            }
        }
    }

    @Override
    public void initView() {
        if (isFeat) {
            getPsActionBar().settitle(featureInfo.getType())/*.addRightText("提问", new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    AddQActivity.startThisActivity(mContext, 40, featureInfo.getTitle());
                }
            })*/;
        } else {
            getPsActionBar().settitle(title)/*.addRightText("提问", new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    AddQActivity.startThisActivity(mContext, 40, newsTitle);
                }
            })*/;
        }

        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTime = (TextView) findViewById(R.id.tv_time);
        rlVideo = (RelativeLayout) findViewById(R.id.rl_video);
        ivVideo = (ImageView) findViewById(R.id.iv_video);
        tvNews = (TextView) findViewById(R.id.tv_news);
        etStuname = (EditText) findViewById(R.id.et_stuname);
        tvSearch = (TextView) findViewById(R.id.tv_search);

        wv_news = (WebView) findViewById(R.id.wv_news);

        if (isFeat) {
            if (!TextUtils.isEmpty(featureInfo.getContent())) {
//                tvNews.setText(Html.fromHtml(featureInfo.getContent(), new HtmlImageGetter(this, tvNews), null));
                addWebHtml(featureInfo.getContent());
            } else {
                tvNews.setText(featureInfo.getAbs());
                wv_news.setVisibility(View.GONE);
                tvNews.setVisibility(View.VISIBLE);
            }
            if (TextUtils.isEmpty(featureInfo.getVurl()) || TextUtils.isEmpty(featureInfo.getImgurl())) {
                rlVideo.setVisibility(View.GONE);
            } else {
                rlVideo.setVisibility(View.VISIBLE);
                Glide.with(mContext).load(featureInfo.getImgurl()).into(ivVideo);
            }
            tvTitle.setText(featureInfo.getTitle());
            tvTime.setText(featureInfo.getStimeStr());
        } else {
            rlVideo.setVisibility(View.GONE);
//            tvNews.setText(Html.fromHtml(data, new HtmlImageGetter(this, tvNews), null));
            addWebHtml(data);
            tvTime.setText(time);
            tvTitle.setText(newsTitle);
        }
    }

    private void addWebHtml(String html) {
        wv_news.loadDataWithBaseURL(NetConstants.IMAGE_HOST, html, "text/html", "utf-8", null);
        wv_news.getSettings().setJavaScriptEnabled(true);
        wv_news.getSettings().setBuiltInZoomControls(true);
        wv_news.getSettings().setSupportZoom(true);
        wv_news.setSaveEnabled(true);
        wv_news.setWebChromeClient(new WebChromeClient());
    }

    @Override
    public void initListener() {
        rlVideo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == rlVideo) {
            VideoActivity.startThisActivity(mContext, featureInfo.getVurl(), featureInfo.getImgurl());
        }
    }
}

package com.peoit.android.online.pschool.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.ui.Base.BaseActivity;
import com.peoit.android.online.pschool.utils.HtmlImageGetter;

public class NewsActivity extends BaseActivity {

    private TextView tvNews;
    private EditText etStuname;
    private TextView tvSearch;
    private String title;
    private String newsTitle;
    private int id;

    public static void startThisActivity(Activity mAc, int id, String content, String title, String newsTitle){
        Intent intent = new Intent(mAc, NewsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("data", content);
        bundle.putString("title", title);
        bundle.putString("newsTitle", newsTitle);
        bundle.putInt("id", id);
        intent.putExtras(bundle);
        mAc.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_news);
    }

    private String data;

    @Override
    public void initData() {
        data = getIntent().getStringExtra("data");
        title = getIntent().getStringExtra("title");
        newsTitle = getIntent().getStringExtra("newsTitle");
        id = getIntent().getIntExtra("id", -1);

        if (TextUtils.isEmpty(data) || TextUtils.isEmpty(title) || TextUtils.isEmpty(newsTitle) || id == -1) {
            showToast("数据传输错误");
            finish();
            return;
        }
    }

    @Override
    public void initView() {
        getPsActionBar().settitle(title).addRightText("提问", new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                AddQActivity.startThisActivity(mContext, 40, newsTitle);
            }
        });
        tvNews = (TextView) findViewById(R.id.tv_news);
        etStuname = (EditText) findViewById(R.id.et_stuname);
        tvSearch = (TextView) findViewById(R.id.tv_search);

        tvNews.setText(Html.fromHtml(data, new HtmlImageGetter(this, tvNews), null));
    }

    @Override
    public void initListener() {

    }
}

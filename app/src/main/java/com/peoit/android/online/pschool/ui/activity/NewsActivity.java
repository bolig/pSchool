package com.peoit.android.online.pschool.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
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

    public static void startThisActivity(Activity mAc, String content, String title){
        Intent intent = new Intent(mAc, NewsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("data", content);
        bundle.putString("title", title);
        intent.putExtras(bundle);
        mAc.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_news);
    }

    public static String test = "<p>&nbsp;</p><table align=\"center\"><tbody>" +
            "<tr class=\"firstRow\" style=\"font-size: 12px;\">" +
            "<td style=\"font-size: 12px;\"><p><img style=\"margin" +
            ": 5px; border: currentColor; border-image: none; " +
            "vertical-align: top;\" src=\"/gz/upload/image/20150804" +
            "/1438656168448065235.jpg\"/></p></td></tr></tbody>" +
            "</table><table align=\"center\"><tbody><tr class=\"first" +
            "Row\" style=\"font-size: 12px;\"><td style=\"font-size:" +
            " 12px;\"><p><img style=\"margin: 5px; border: currentCol" +
            "or; border-image: none; vertical-align: top;\" src=\"/gz/u" +
            "pload/image/20150804/1438656168549078796.jpg\" nav=\"news;\"" +
            " var=\"\"/></p></td></tr></tbody></table><table align=\"center\"" +
            "><tbody><tr class=\"firstRow\" style=\"font-size: 12px;\"><td st" +
            "yle=\"font-size: 12px;\"><p><img style=\"margin: 5px; border: cu" +
            "rrentColor; border-image: none; vertical-align: top;\" src=\"/gz" +
            "/upload/image/20150804/1438656168649001232.jpg\" nav=\"news;\" " +
            "var=\"\"/></p></td></tr></tbody></table><table align=\"center\"><" +
            "tbody><tr class=\"firstRow\" style=\"font-size: 12px;\"><td styl" +
            "e=\"font-size: 12px;\"><p><img style=\"margin: 5px; border: curr" +
            "entColor; border-image: none; vertical-align: top;\" src=\"/gz/up" +
            "load/image/20150804/1438656168685098850.jpg\" nav=\"news;\" var=\"\"" +
            "/></p></td></tr></tbody></table><table align=\"center\"><tbody><tr" +
            " class=\"firstRow\" style=\"font-size: 12px;\"><td style=\"font-siz" +
            "e: 12px;\"><p><img style=\"margin: 5px; border: currentColor; border-im" +
            "age: none; vertical-align: top;\" src=\"/gz/upload/image/20150804/14386561" +
            "68784058288.jpg\" nav=\"news;\" var=\"\"/></p></td></tr></tbody></table><tabl" +
            "e align=\"center\"><tbody><tr class=\"firstRow\" style=\"font-size: 12px;\"><td" +
            " style=\"font-size: 12px;\"><p><img style=\"margin: 5px; border: currentColor; " +
            "border-image: none; vertical-align: top;\" src=\"/gz/upload/image/20150804/143" +
            "8656168852044419.jpg\" nav=\"news;\" var=\"\"/></p></td></tr></tbody></table><t" +
            "able align=\"center\"><tbody><tr class=\"firstRow\" style=\"font-size: 12px;\"><t" +
            "d style=\"font-size: 12px;\"><p><img style=\"margin: 5px; border: currentColor;" +
            " border-image: none; vertical-align: top;\" src=\"/gz/upload/image/20150804/143" +
            "8656168916058034.jpg\" nav=\"news;\" var=\"\"/></p></td></tr></tbody></table><t" +
            "able align=\"center\"><tbody><tr class=\"firstRow\" style=\"font-size: 12px;\"><" +
            "td style=\"font-size: 12px;\"><p><img style=\"margin: 5px; border: currentColor; " +
            "border-image: none; vertical-align: top;\" src=\"/gz/upload/image/20150804/1438656" +
            "168985076305.jpg\" nav=\"news;\" var=\"\"/></p></td></tr></tbody></table><table ali" +
            "gn=\"center\"><tbody><tr class=\"firstRow\" style=\"font-size: 12px;\"><td styl" +
            "e=\"font-size: 12px;\"><p><img style=\"margin: 5px; border: currentColor; border-i" +
            "mage: none; vertical-align: top;\" src=\"/gz/upload/image/20150804/143865616904909" +
            "2047.jpg\" nav=\"news;\" var=\"\"/></p></td></tr></tbody></table><p><br style=\"fon" +
            "t: 16px/28px 宋体, sans-serif; text-align: justify; color: rgb(37, 37, 37); text-trans" +
            "form: none; text-indent: 0px; letter-spacing: normal; word-spacing: 0px; white-space: no" +
            "rmal; widows: 1; font-size-adjust: none; font-stretch: normal; background-color: rgb(255," +
            " 255, 255); -webkit-text-stroke-width: 0px;\"/><span style=\"font: 16px/28px 宋体, sans-s" +
            "erif; text-align: justify; color: rgb(37, 37, 37); text-transform: none; text-indent: 0p" +
            "x; letter-spacing: normal; word-spacing: 0px; float: none; display: inline !important;" +
            " white-space: normal; widows: 1; font-size-adjust: none; font-stretch: normal; backgro" +
            "und-color: rgb(255, 255, 255); -webkit-text-stroke-width: 0px;\">　　年轻的你不必着急，努" +
            "力一些，你想要的时间都会给你。</span></p><p>&nbsp;</p>";

    private String data;

    @Override
    public void initData() {
        data = getIntent().getStringExtra("data");
        title = getIntent().getStringExtra("title");
        if (TextUtils.isEmpty(data) || TextUtils.isEmpty(title)) {
            showToast("数据传输错误");
            finish();
        }
    }

    @Override
    public void initView() {
        getPsActionBar().settitle(title);

        tvNews = (TextView) findViewById(R.id.tv_news);
        etStuname = (EditText) findViewById(R.id.et_stuname);
        tvSearch = (TextView) findViewById(R.id.tv_search);

        tvNews.setText(Html.fromHtml(data, new HtmlImageGetter(this, tvNews), null));
    }

    @Override
    public void initListener() {

    }
}

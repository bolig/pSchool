package com.peoit.android.online.pschool.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.config.CommonUtil;
import com.peoit.android.online.pschool.entity.ExpertsOnlineInfo;
import com.peoit.android.online.pschool.ui.Base.BaseActivity;

/**
 * 专家在线详情界面
 * Created by zyz on 2015/9/1.
 */
public class ExpertsOnlineContentActivity extends BaseActivity {
    private String title;
    private long id;
    private String content;
    private TextView tv_wenti, tv_huifu, tv_huifu1;
    private ExpertsOnlineInfo data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_expertsonlinecontent);
    }

    public static void startThisActivity(Activity mAc, long id, String content, String title, ExpertsOnlineInfo info) {
        Intent intent = new Intent(mAc, ExpertsOnlineContentActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("data", content);
        bundle.putString("title", title);
        bundle.putLong("id", id);
        bundle.putSerializable("info", info);
        intent.putExtras(bundle);
        mAc.startActivityForResult(intent, 1);
    }

    @Override
    public void initData() {
        content = getIntent().getStringExtra("data");
        title = getIntent().getStringExtra("title");
        id = getIntent().getLongExtra("id", -1l);
        data = (ExpertsOnlineInfo) getIntent().getSerializableExtra("info");
        if (TextUtils.isEmpty(content) || TextUtils.isEmpty(title) || id == -1) {
            showToast("数据传输错误");
            finish();
            return;
        }
    }

    @Override
    public void initView() {
        tv_wenti = (TextView) findViewById(R.id.expertsonlinecontent_tv1);
        tv_huifu1 = (TextView) findViewById(R.id.item_parentsclassroom_tv2);
        getPsActionBar().settitle(title + "的提问").addRightText(CommonUtil.isZhuanJia() ? "回复" : "追问", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddQActivity.startThisActivity(mContext, id);
            }
        });
        tv_wenti.setText(getContent(true, "", "问题:", content));
        tv_huifu1.setText(data.getStimeStr() + "提问");
    }

    @Override
    protected void onResume() {
        super.onResume();
        onRefresh();
    }

    private void onRefresh() {
        //回复
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.item_parentsclassroom_ll);
        linearLayout.removeAllViews();
        if (data.getDis() != null && data.getDis().size() != 0) {
            for (int i = 0; i < data.getDis().size(); i++) {
                View view = getLayoutInflater().inflate(R.layout.addq_item, null);
                TextView text = (TextView) view.findViewById(R.id.tv1);
                TextView text1 = (TextView) view.findViewById(R.id.tv2);
                text.setText(getContent(false,
                        data.getDis().get(i).getUsertype(),
                        data.getDis().get(i).getUsername() + ":",
                        data.getDis().get(i).getText()));
                text1.setText(data.getDis().get(i).getStimeStr() + getText1(data.getDis().get(i).getUsertype()));
                linearLayout.addView(view);
            }
        }
    }

    private String getText1(String type) {
        return ("3".equals(type) || "4".equals(type)) ? "回复" : "追加";
    }

    private SpannableStringBuilder getContent(boolean isWenti, String type, String s, String text) {
        String msg = s + text;
        SpannableStringBuilder builder = new SpannableStringBuilder(msg);

        ForegroundColorSpan colorSpan_R = new ForegroundColorSpan(getResources().getColor(R.color.md_green_300));
        ForegroundColorSpan colorSpan_Q = new ForegroundColorSpan(getResources().getColor(R.color.md_red_300));
        ForegroundColorSpan colorSpan_Q1 = new ForegroundColorSpan(getResources().getColor(R.color.md_blue_500));

        builder.setSpan(isWenti ? colorSpan_Q1 :
                ("3".equals(type) || "4".equals(type)) ? colorSpan_Q :
                        colorSpan_R, 0, s.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return builder;
    }

    @Override
    public void initListener() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            setResult(RESULT_OK, new Intent());
            finish();
        }
    }
}

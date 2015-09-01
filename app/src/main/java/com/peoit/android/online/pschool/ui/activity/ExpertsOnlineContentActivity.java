package com.peoit.android.online.pschool.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.entity.ExpertsOnlineInfo;
import com.peoit.android.online.pschool.ui.Base.BaseActivity;

/**
 * 专家在线详情界面
 * Created by zyz on 2015/9/1.
 */
public class ExpertsOnlineContentActivity extends BaseActivity{
    private String title;
    private int id;
    private String content;
    private TextView tv_wenti,tv_huifu,tv_huifu1;
    private ExpertsOnlineInfo data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_expertsonlinecontent);
    }
    public static void startThisActivity(Activity mAc, int id, String content, String title , ExpertsOnlineInfo info){
        Intent intent = new Intent(mAc, ExpertsOnlineContentActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("data", content);
        bundle.putString("title", title);
        bundle.putInt("id", id);
        bundle.putSerializable("info",info);
        intent.putExtras(bundle);
        mAc.startActivity(intent);
    }
    @Override
    public void initData() {
        content = getIntent().getStringExtra("data");
        title = getIntent().getStringExtra("title");
        id = getIntent().getIntExtra("id", -1);
        data = (ExpertsOnlineInfo) getIntent().getSerializableExtra("info");


        if (TextUtils.isEmpty(content) || TextUtils.isEmpty(title)  || id == -1) {
            showToast("数据传输错误");
            finish();
            return;
        }
    }

    @Override
    public void initView() {
        tv_wenti = (TextView) findViewById(R.id.expertsonlinecontent_tv1);
//        tv_huifu = (TextView) findViewById(R.id.item_parentsclassroom_tv3);
        tv_huifu1 = (TextView) findViewById(R.id.item_parentsclassroom_tv2);
        getPsActionBar().settitle(title).addRightText("回复", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddQActivity.startThisActivity(mContext, id, title);
            }
        });
        tv_wenti.setText("问题：" + content);
        tv_huifu1.setText(data.getStimeStr() + "提问");
    }
    @Override
    protected void onResume() {
        super.onResume();
        //回复
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.item_parentsclassroom_ll);
        linearLayout.removeAllViews();
        if (data.getDis() != null && data.getDis().size() != 0) {
            for (int i = 0; i < data.getDis().size(); i++) {
                //动态添加
                TextView text = new TextView(getContext());
                text.setTextSize(20);
                text.setText(data.getDis().get(i).getUsername() + "回复:" + data.getDis().get(i).getText());

//                tv_huifu.setText(data.getDis().get(i).getStimeStr() + "回复");

                TextView text1 = new TextView(getContext());
                text1.setTextSize(14);
                text1.setGravity(Gravity.RIGHT);
                text1.setText(data.getDis().get(i).getStimeStr() + "回复");
                linearLayout.addView(text);
                linearLayout.addView(text1);
            }
        }
//            tv_huifu.setVisibility(View.GONE);
    }

    @Override
    public void initListener() {

    }
}

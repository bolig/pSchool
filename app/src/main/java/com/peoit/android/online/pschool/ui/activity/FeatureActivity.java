package com.peoit.android.online.pschool.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.ui.Base.BaseActivity;
import com.peoit.android.online.pschool.ui.Presenter.FeaturePersenter;
import com.peoit.android.online.pschool.ui.adapter.FeatureAdapter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * 专栏界面...学校专栏
 * <p/>
 * <p/>
 * author:libo
 * time:2015/7/14
 * E-mail:boli_android@163.com
 * last: ...
 */
public class FeatureActivity extends BaseActivity implements View.OnClickListener{
    private ListView list;

    private TextView tvItem1;
    private TextView tvItem2;
    private TextView tvItem3;
    private TextView tvItem4;

    private View pullList_header;

    private FeaturePersenter featurePersenter;
    private FeatureAdapter featureAdapter;

    public static void startThisActivity(Activity mAc){
        Intent intent = new Intent(mAc, FeatureActivity.class);
        mAc.startActivity(intent);
        try {
            FileInputStream is = new FileInputStream(new File(""));
            Bitmap mp = BitmapFactory.decodeStream(is);
            BitmapFactory.decodeFile("");
        } catch (FileNotFoundException e) {

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_list_layout_nopadding);
    }

    @Override
    public void initData() {
        featureAdapter = new FeatureAdapter(mContext, R.layout.act_feature_item, null);
        featurePersenter = new FeaturePersenter(this, featureAdapter);
    }

    @Override
    public void initView() {
        getPsActionBar().settitle("网校专栏");

        list = (ListView) findViewById(R.id.list);
        list.setAdapter(featureAdapter);

        addHeaderView();

        featurePersenter.loadData();
    }

    /**
     * 添加listViewHeader
     *
     */
    private void addHeaderView() {
        pullList_header = getLayoutInflater().inflate(R.layout.act_feature_header, null);

        tvItem1 = (TextView) pullList_header.findViewById(R.id.tv_item1);
        tvItem2 = (TextView) pullList_header.findViewById(R.id.tv_item2);
        tvItem3 = (TextView) pullList_header.findViewById(R.id.tv_item3);
        tvItem4 = (TextView) pullList_header.findViewById(R.id.tv_item4);

        list.addHeaderView(pullList_header, null, true);
        list.setHeaderDividersEnabled(false);
    }

    @Override
    public void initListener() {
        tvItem1.setOnClickListener(this);
        tvItem2.setOnClickListener(this);
        tvItem3.setOnClickListener(this);
        tvItem4.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_item1:
                //家长课堂
                ParentsClassroomActivity.startThisActivity(mContext);
                break;
            case R.id.tv_item2:
                //健康顾问
                HealthEducationActivity.startThisActivity(mContext);
                break;
            case R.id.tv_item3:
                //亲子活动
                FamilyActivitiy.startThisActivity(mContext);
                break;
            case R.id.tv_item4:
                //专家在线
                ExpertsOnlineActivity.startThisActivity(mContext);
                break;
        }
    }
}

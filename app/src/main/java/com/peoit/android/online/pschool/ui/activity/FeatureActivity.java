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
 * 专栏界面...
 * <p/>
 * <p/>
 * author:libo
 * time:2015/7/14
 * E-mail:boli_android@163.com
 * last: ...
 */
public class FeatureActivity extends BaseActivity implements View.OnClickListener{
    private ListView list;

    private TextView llItem1;
    private TextView llItem2;
    private TextView llItem3;
    private TextView llItem4;
    private TextView llItem5;

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

        llItem1 = (TextView) pullList_header.findViewById(R.id.ll_item1);
        llItem2 = (TextView) pullList_header.findViewById(R.id.ll_item2);
        llItem3 = (TextView) pullList_header.findViewById(R.id.ll_item3);
        llItem4 = (TextView) pullList_header.findViewById(R.id.ll_item4);
        llItem5 = (TextView) pullList_header.findViewById(R.id.ll_item5);

        list.addHeaderView(pullList_header, null, true);
        list.setHeaderDividersEnabled(false);
    }


    @Override
    public void initListener() {
        llItem1.setOnClickListener(this);
        llItem2.setOnClickListener(this);
        llItem3.setOnClickListener(this);
        llItem4.setOnClickListener(this);
        llItem5.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_item1:

                break;
            case R.id.ll_item2:

                break;
            case R.id.ll_item3:

                break;
            case R.id.ll_item4:

                break;
            case R.id.ll_item5:

                break;
        }
    }
}

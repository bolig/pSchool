package com.peoit.android.online.pschool.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.config.Constants;
import com.peoit.android.online.pschool.ui.Base.BaseActivity;
import com.peoit.android.online.pschool.ui.adapter.ParentChlidAreaAdapter;

public class ParentChildAreaActivity extends BaseActivity {

    private ListView pslvArea;

    public static void startThisActivity(Activity mAc){
        Intent intent = new Intent(mAc, ParentChildAreaActivity.class);
        mAc.startActivity(intent);
    }

    private void assignViews() {
        pslvArea = (ListView) findViewById(R.id.pslv_area);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_parent_child_area);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        assignViews();
        getPsActionBar().settitle("亲子活动区域");
        pslvArea.setAdapter(new ParentChlidAreaAdapter(mContext, Constants.mParentChildAreas));
    }

    @Override
    public void initListener() {

    }
}

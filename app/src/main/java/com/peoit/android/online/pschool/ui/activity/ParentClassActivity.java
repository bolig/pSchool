package com.peoit.android.online.pschool.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.ui.Base.BaseActivity;
import com.peoit.android.online.pschool.ui.adapter.ParentClassAdapter;

public class ParentClassActivity extends BaseActivity {
    private ListView lvItem;

    private void assignViews() {
        lvItem = (ListView) findViewById(R.id.lv_item);
    }

    public static void startThisActivity(Activity mAc) {
        Intent intent = new Intent(mAc, ParentClassActivity.class);
        mAc.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_parent_class);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        assignViews();
        getPsActionBar().settitle("选择年级");
        lvItem.setAdapter(new ParentClassAdapter(mContext));
    }

    @Override
    public void initListener() {

    }
}

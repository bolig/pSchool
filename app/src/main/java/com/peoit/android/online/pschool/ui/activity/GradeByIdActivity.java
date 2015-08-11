package com.peoit.android.online.pschool.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.peoit.android.libview.list.InterceptScrollContainer;
import com.peoit.android.libview.list.MyHScrollView;
import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.ui.Base.BaseActivity;
import com.peoit.android.online.pschool.ui.Presenter.GradeByidPersenter;

/**
 * 首页
 * <p/>
 * author:libo
 * time:2015/7/14
 * E-mail:boli_android@163.com
 * last: ...
 */
public class GradeByIdActivity extends BaseActivity {

    private ListView lvInfo;

    private InterceptScrollContainer iscScroll;
    private MyHScrollView scScroll;

    private TextView tvItem1;
    private TextView tvItem2;
    private TextView tvItem3;
    private TextView tvItem4;
    private TextView tvItem5;
    private TextView tvItem6;
    private TextView tvItem7;
    private TextView tvItem8;
    private View rowView;
    private View headView;
    private GradeByidPersenter mPersenter;

    public static void startThisActivity(Activity mAc){
        Intent intent = new Intent(mAc, GradeByIdActivity.class);
        mAc.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_grade_by_id);
    }

    @Override
    public void initData() {
        mPersenter = new GradeByidPersenter(this, 0);
    }

    @Override
    public void initView() {

        headView = findViewById(R.id.head);
        headView.setClickable(true);
        headView.setFocusable(true);

        lvInfo = (ListView) findViewById(R.id.lv_info);
        lvInfo.setAdapter(mPersenter.getAdapter());
        iscScroll = (InterceptScrollContainer) findViewById(R.id.isc_scroll);
        scScroll = (MyHScrollView) findViewById(R.id.sc_scroll);

        addRowItem();

        mPersenter.toGetGradeByIdList();
    }

    private void addRowItem() {

        rowView = getLayoutInflater().inflate(R.layout.act_grade_by_id_row_item, null);

        tvItem1 = (TextView) rowView.findViewById(R.id.tv_item1);
        tvItem2 = (TextView) rowView.findViewById(R.id.tv_item2);
        tvItem3 = (TextView) rowView.findViewById(R.id.tv_item3);
        tvItem4 = (TextView) rowView.findViewById(R.id.tv_item4);
        tvItem5 = (TextView) rowView.findViewById(R.id.tv_item5);
        tvItem6 = (TextView) rowView.findViewById(R.id.tv_item6);
        tvItem7 = (TextView) rowView.findViewById(R.id.tv_item7);
        tvItem8 = (TextView) rowView.findViewById(R.id.tv_item8);

        scScroll.addView(rowView);
    }

    @Override
    public void initListener() {
        headView.setOnTouchListener(new ListViewAndHeadViewTouchLinstener());
        lvInfo.setOnTouchListener(new ListViewAndHeadViewTouchLinstener());
    }

    class ListViewAndHeadViewTouchLinstener implements View.OnTouchListener {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            scScroll.onTouchEvent(event);
            return false;
        }
    }
}

package com.peoit.android.online.pschool.ui.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;

import com.peoit.android.libview.list.InterceptScrollContainer;
import com.peoit.android.libview.list.MyHScrollView;
import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.ui.Base.BaseActivity;
import com.peoit.android.online.pschool.ui.Presenter.CheckedInPresenter;
import com.peoit.android.online.pschool.ui.adapter.CheckedInAdapter;
import com.peoit.android.online.pschool.ui.adapter.PopupAdapter;
import com.peoit.android.online.pschool.ui.dialog.SelectCheckInTypeDialog;

import java.util.ArrayList;
import java.util.Calendar;

public class CheckedInActivity extends BaseActivity {
    private ListView lvInfo;
    private View headView;
    private InterceptScrollContainer iscScroll;
    private MyHScrollView scScroll;
    private View rowView;
    private CheckedInPresenter mPresenter;

    private TextView tv_item6;
    private TextView tv_pupo;
    private ListView lv_popup;
    private ArrayList<String> titles;
    private PopupAdapter popupAdapter;
    private FloatingActionButton ftb_filter;
    private CheckedInAdapter adapter;
    private Animation anim_in;
    private Animation anim_out;
    private SelectCheckInTypeDialog typeDialog;

    /**
     * 启动界面的方法
     *
     * @param mAc
     */
    public static void startThisActivity(Activity mAc){
        Intent intent = new Intent(mAc, CheckedInActivity.class);
        mAc.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_checked_in);
    }

    @Override
    public void initData() {
        mPresenter = new CheckedInPresenter(this);

        anim_in = AnimationUtils.loadAnimation(mContext, R.anim.anim_scalce_in);
        anim_out = AnimationUtils.loadAnimation(mContext, R.anim.anim_scalce_out);
    }

    @Override
    public void initView() {
        getPsActionBar().settitle("考勤查询").addRightBtn(R.drawable.rili, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 选择日期
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                View view = View.inflate(mContext, R.layout.dialog_date_time, null);
                final DatePicker datePicker = (DatePicker) view.findViewById(R.id.date_picker);
                builder.setView(view);

                Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis(System.currentTimeMillis());
                datePicker.init(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), null);

                builder.setTitle("选取查询日期");
                builder.setPositiveButton("查询", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        StringBuffer sb = new StringBuffer();
                        sb.append(String.format("%d-%02d-%02d",
                                datePicker.getYear(),
                                datePicker.getMonth() + 1,
                                datePicker.getDayOfMonth()));
                        //查询
                        mPresenter.doLoadCheckIn(sb.toString());

                        dialog.cancel();
                    }
                });
                Dialog dialog = builder.create();
                dialog.show();
            }
        });

        lvInfo = (ListView) findViewById(R.id.lv_info);

        headView = findViewById(R.id.head);
        headView.setClickable(true);
        headView.setFocusable(true);

        iscScroll = (InterceptScrollContainer) findViewById(R.id.isc_scroll);

        scScroll = (MyHScrollView) findViewById(R.id.sc_scroll);

        ftb_filter = (FloatingActionButton) findViewById(R.id.fab_filter);

        addRowItem();

        adapter = mPresenter.getAdapter(headView);

        lvInfo.setAdapter(adapter);
    }

    private void addRowItem() {

        rowView = getLayoutInflater().inflate(R.layout.act_check_row_item, null);

        tv_item6 = (TextView) rowView.findViewById(R.id.tv_item6);
        tv_pupo = (TextView) rowView.findViewById(R.id.tv_popu);

        scScroll.addView(rowView);

        typeDialog = new SelectCheckInTypeDialog(mContext);
        typeDialog.setCanceledOnTouchOutside(true);
        typeDialog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mPresenter.changeAdapterShow(position);
                typeDialog.dismiss();
            }
        });
        typeDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                ftb_filter.startAnimation(anim_in);
                ftb_filter.setVisibility(View.VISIBLE);
                tv_pupo.setSelected(false);
            }
        });
    }

    @Override
    public void initListener() {
        headView.setOnTouchListener(new ListViewAndHeadViewTouchLinstener());
        lvInfo.setOnTouchListener(new ListViewAndHeadViewTouchLinstener());

        ftb_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CheckedInPresenter.mCheckInInfos != null && CheckedInPresenter.mCheckInInfos.size() > 0){
                    ftb_filter.startAnimation(anim_out);
                    ftb_filter.setVisibility(View.GONE);
                    tv_pupo.setSelected(true);
                    typeDialog.show();
                } else {
                    showToast("当前没有可选择的考勤");
                }
            }
        });
    }

    class ListViewAndHeadViewTouchLinstener implements View.OnTouchListener {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            scScroll.onTouchEvent(event);
            return false;
        }
    }

 //   private PopupWindow mPopupWindow;

//    /**
//     * 显示popupwindow
//     *
//     */
//    private void showPupoWindow(){
//        if (mPopupWindow == null) {
//            View popupView = getLayoutInflater().inflate(R.layout.pupo_list, null);
//            lv_popup = (ListView) popupView.findViewById(R.id.lv_info);
//
//            popupAdapter = new PopupAdapter(mContext, titles);
//            lv_popup.setAdapter(popupAdapter);
//            mPopupWindow = new PopupWindow(popupView, CommonUtil.w_screeen, ViewGroup.LayoutParams.WRAP_CONTENT);
//        }
//
//        mPopupWindow.setFocusable(true);
//        mPopupWindow.setOutsideTouchable(true);
//        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
//        mPopupWindow.showAsDropDown(lvInfo, 0, 0);
//
//        lv_popup.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                mPopupWindow.dismiss();
//                mPresenter.changeAdapterShow(position);
//            }
//        });
//
//        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
//            @Override
//            public void onDismiss() {
//                ftb_filter.startAnimation(anim_in);
//                ftb_filter.setVisibility(View.VISIBLE);
//                tv_pupo.setSelected(false);
//            }
//        });
//    }
}

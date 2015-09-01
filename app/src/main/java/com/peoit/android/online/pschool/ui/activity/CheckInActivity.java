package com.peoit.android.online.pschool.ui.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;

import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.ui.Base.BaseActivity;
import com.peoit.android.online.pschool.ui.Presenter.CheckInPresenter;

import java.util.Calendar;
import java.util.Map;

/**
 * 考勤查询
 */
public class CheckInActivity extends BaseActivity {

    private TextView etStart;
    private TextView etEnd;
    private TextView tvSearch;
    private ListView list;
    private CheckInPresenter mPresenter;
    private String start_time;
    private String end_time;

    public static void startThisActivity(Activity mAc){
        Intent intent = new Intent(mAc, CheckInActivity.class);
        mAc.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_list_layout_nopadding);
    }

    @Override
    public void initData() {
        mPresenter = new CheckInPresenter(this){
            @Override
            public Map<String, String> getCheckInPresenter(Map<String, String> params) {
                return params;
            }
        };

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
//                etStart.setInputType(InputType.TYPE_NULL);

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
                            list.setAdapter(mPresenter.getAdapter());

//                        etStart.setText(sb);
                        dialog.cancel();
                    }
                });
                Dialog dialog = builder.create();
                dialog.show();
            }
        });
//        findViewById(R.id.search).setVisibility(View.VISIBLE);
        etStart = (TextView) findViewById(R.id.et_start);
        etEnd = (TextView) findViewById(R.id.et_end);
        tvSearch = (TextView) findViewById(R.id.tv_search);
        list = (ListView) findViewById(R.id.list);


    }

    @Override
    public void initListener() {
        /*etStart.setOnClickListener(new View.OnClickListener() {
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
                etStart.setInputType(InputType.TYPE_NULL);

                builder.setTitle("选取开始查询日期");
                builder.setPositiveButton("确  定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        StringBuffer sb = new StringBuffer();
                        sb.append(String.format("%d-%02d-%02d",
                                datePicker.getYear(),
                                datePicker.getMonth() + 1,
                                datePicker.getDayOfMonth()));
                        etStart.setText(sb);
                        dialog.cancel();
                    }
                });
                Dialog dialog = builder.create();
                dialog.show();
            }
        });
        etEnd.setOnClickListener(new View.OnClickListener() {
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
                etEnd.setInputType(InputType.TYPE_NULL);

                builder.setTitle("选取结束查询日期");
                builder.setPositiveButton("确  定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        StringBuffer sb = new StringBuffer();
                        sb.append(String.format("%d-%02d-%02d",
                                datePicker.getYear(),
                                datePicker.getMonth() + 1,
                                datePicker.getDayOfMonth()));
                        etEnd.setText(sb);
                        dialog.cancel();
                    }
                });
                Dialog dialog = builder.create();
                dialog.show();
            }
        });
        tvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //查询
                if (match()) {
                    mPresenter.doLoadCheckIn();
                    list.setAdapter(mPresenter.getAdapter());
                }

            }
        });*/
    }
    public boolean match() {
        start_time = etStart.getText().toString();
        if (TextUtils.isEmpty(start_time)) {
            showToast("请输入开始时间");
            return false;
        }
        end_time = etEnd.getText().toString();
        if (TextUtils.isEmpty(end_time)) {
            showToast("请输入结束时间");
            return false;
        }
        return true;
    }
}

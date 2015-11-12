package com.peoit.android.online.pschool.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.config.CommonUtil;
import com.peoit.android.online.pschool.ui.Base.BaseActivity;
import com.peoit.android.online.pschool.ui.Presenter.AddQandAPresenter;

public class AddQActivity extends BaseActivity {
    private LinearLayout llPublic;
    private TextView tvTitle;
    private View line;
    private EditText etOldpass;
    private LinearLayout llError;
    private TextView tvError;
    private boolean isPublic = false;
    private boolean isAddQ;
    private long mId;
    private CheckBox cbOk;
    private CheckBox cbNo;

    private void assignViews() {
        llPublic = (LinearLayout) findViewById(R.id.ll_public);
//        cbPublic = (CheckBox) findViewById(R.id.cb_public);
        cbOk = (CheckBox) findViewById(R.id.cb_ok);
        cbNo = (CheckBox) findViewById(R.id.cb_no);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        line = findViewById(R.id.line);
        etOldpass = (EditText) findViewById(R.id.et_oldpass);
        llError = (LinearLayout) findViewById(R.id.ll_error);
        tvError = (TextView) findViewById(R.id.tv_error);
    }

    private int maxEms = 1000;
    private int curEms = 0;
    private AddQandAPresenter mPresenter;
    private String text;

    public static void startThisActivity(Activity mAc, long id) {
        Intent intent = new Intent(mAc, AddQActivity.class);
        intent.putExtra("isAddQ", false);
        intent.putExtra("id", id);
        mAc.startActivityForResult(intent, 1);
    }

    public static void startThisActivity(Activity mAc) {
        Intent intent = new Intent(mAc, AddQActivity.class);
        intent.putExtra("isAddQ", true);
        mAc.startActivityForResult(intent, 1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE |
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setContentView(R.layout.act_add_q);
    }

    @Override
    public void initData() {
        isAddQ = getIntent().getBooleanExtra("isAddQ", false);
        mId = getIntent().getLongExtra("id", -1l);
        mPresenter = new AddQandAPresenter(this);
    }

    @Override
    public void initView() {
        assignViews();
        getPsActionBar().settitle(isAddQ ? "我的提问" : CommonUtil.isZhuanJia() ? "我的追问" : "我的回复").addRightText("提交", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (match()) {
                    if (isAddQ) {
                        mPresenter.doAddQ("40", text, isPublic);
                    } else {
                        mPresenter.doAddR(mId + "", text);
                    }
                }
            }
        });
        if (!isAddQ) {
            llPublic.setVisibility(View.GONE);
        }
        tvError.setSelected(true);
    }

    private boolean match() {
        text = etOldpass.getText().toString();
        if (TextUtils.isEmpty(text)) {
            showToast("请输入问题内容");
            return false;
        }
        return true;
    }

    private void showError() {
        if (curEms > maxEms || curEms < 20) {
            tvError.setSelected(true);
        } else {
            tvError.setSelected(false);
        }
        tvError.setText(curEms + "/" + maxEms);
        //cbPublic.setChecked(isPublic);
        cbOk.setChecked(isPublic);
        cbNo.setChecked(!isPublic);
        cbOk.setSelected(isPublic);
        cbNo.setSelected(!isPublic);
    }

    @Override
    public void initListener() {
        etOldpass.addTextChangedListener(new MyTextWatcher());
//        cbOk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                isPublic = isChecked;
//            }
//        });
//        cbNo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                isPublic =
//            }
//        });
//        llPublic.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                isPublic =
//            }
//        });
        cbOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isPublic = true;
                cbOk.setChecked(isPublic);
                cbNo.setChecked(!isPublic);
                cbOk.setSelected(isPublic);
                cbNo.setSelected(!isPublic);
            }
        });
        cbNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isPublic = false;
                cbOk.setChecked(isPublic);
                cbNo.setChecked(!isPublic);
                cbOk.setSelected(isPublic);
                cbNo.setSelected(!isPublic);
            }
        });
    }

    private class MyTextWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (TextUtils.isEmpty(s))
                curEms = 0;
            else
                curEms = s.length();
            showError();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }
}

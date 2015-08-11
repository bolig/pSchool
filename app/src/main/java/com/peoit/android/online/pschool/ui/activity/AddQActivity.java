package com.peoit.android.online.pschool.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.ui.Base.BaseActivity;
import com.peoit.android.online.pschool.ui.Presenter.AddQandAPresenter;

public class AddQActivity extends BaseActivity {

    private TextView tvTitle;
    private TextInputLayout inputOldpass;
    private EditText etOldpass;

    private int maxEms = 1000;
    private int curEms = 0;

    private TextView tvError;
    private AddQandAPresenter mPresenter;
    private int id;
    private String title;
    private String text;

    public static void startThisActivity(Activity mAc, int id, String title){
        Intent intent = new Intent(mAc, AddQActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("title", title);
        mAc.startActivity(intent);
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
        id = getIntent().getIntExtra("id", -1);
        title = getIntent().getStringExtra("title");
        if (id == -1 || TextUtils.isEmpty(title)){
            showToast("数据传输错误");
            finish();
        }
        mPresenter = new AddQandAPresenter(this);
    }

    @Override
    public void initView() {
        getPsActionBar().settitle("问题反馈").addRightText("提交", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (match()){
                    mPresenter.doAddQ(id + "", text);
                }
            }
        });

        tvTitle = (TextView) findViewById(R.id.tv_title);
        inputOldpass = (TextInputLayout) findViewById(R.id.input_oldpass);
        etOldpass = (EditText) findViewById(R.id.et_oldpass);
        tvError = (TextView) findViewById(R.id.tv_error);

        tvTitle.setText("对" + title + "的提问");
    }

    private boolean match() {
        text = etOldpass.getText().toString();
        if (TextUtils.isEmpty(text)){
            showToast("请输入问题内容");
            return false;
        }
        return true;
    }

    private void showError(){
        if (curEms > maxEms || curEms < 20) {
            tvError.setSelected(true);
        } else {
            tvError.setSelected(false);
        }
        tvError.setText(curEms + "/" + maxEms);
    }

    @Override
    public void initListener() {
        etOldpass.addTextChangedListener(new MyTextWatcher());
    }

    private class MyTextWatcher implements TextWatcher{

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

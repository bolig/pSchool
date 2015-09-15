package com.peoit.android.online.pschool.ui.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ListView;

import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.ui.adapter.PopupAdapter;

import java.util.ArrayList;

/**
 * author:libo
 * time:2015/9/6
 * E-mail:boli_android@163.com
 * last: ...
 */
public class SelectCheckInTypeDialog extends Dialog {

    private final Activity mAc;
    private Animation anim_in;
    private Animation anim_out;
    private ListView lv_popup;
    private PopupAdapter popupAdapter;
    private ArrayList<String> titles;
    private AdapterView.OnItemClickListener mOnItemClickListener;
    private View view;

    public SelectCheckInTypeDialog(Activity mAc) {
        super(mAc, R.style.dialog);
        this.mAc = mAc;
        initData();
    }

    private void initData() {
        anim_in = AnimationUtils.loadAnimation(getContext(), R.anim.abc_slide_in_bottom);
        anim_out = AnimationUtils.loadAnimation(getContext(), R.anim.abc_slide_out_bottom);

        titles = new ArrayList<String>();
        titles.add("所有");
        titles.add("正常打卡");
        titles.add("迟到");
        titles.add("未签到");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.pupo_list);

        lv_popup = (ListView) findViewById(R.id.lv_info);
        popupAdapter = new PopupAdapter(mAc, titles);
        lv_popup.setAdapter(popupAdapter);

        view = findViewById(R.id.view);
        initListener();
    }

    private void initListener() {
        lv_popup.setOnItemClickListener(mOnItemClickListener);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener listener) {
        if (lv_popup != null) {
            mOnItemClickListener = listener;
            lv_popup.setOnItemClickListener(mOnItemClickListener);
        } else {
            mOnItemClickListener = listener;
        }
    }

    @Override
    public void show() {
        super.show();
        lv_popup.startAnimation(anim_in);
    }

    @Override
    public void dismiss() {
        super.dismiss();
        lv_popup.startAnimation(anim_in);
        anim_out.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                SelectCheckInTypeDialog.super.dismiss();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }
}

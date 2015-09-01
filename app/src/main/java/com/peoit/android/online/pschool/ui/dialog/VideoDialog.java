package com.peoit.android.online.pschool.ui.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.peoit.android.online.pschool.R;

/**
 * author:libo
 * time:2015/8/31
 * E-mail:boli_android@163.com
 * last: ...
 */
public class VideoDialog extends Dialog implements View.OnClickListener{

    private TextView tvNative;
    private TextView tvTake;
    private TextView tvCancel;
    private Animation anim_out;
    private Animation anim_in;
    private LinearLayout ll_tv;
    private View.OnClickListener mNativeListener;
    private View.OnClickListener mTakeListener;

    private void assignViews() {
        tvNative = (TextView) findViewById(R.id.tv_native);
        tvTake = (TextView) findViewById(R.id.tv_take);
        tvCancel = (TextView) findViewById(R.id.tv_cancel);

        ll_tv = (LinearLayout) findViewById(R.id.ll_tv);
    }


    public VideoDialog(Activity mAc) {
        super(mAc, R.style.dialog);
        initData();
    }

    private void initData() {
        anim_in = AnimationUtils.loadAnimation(getContext(), R.anim.abc_slide_in_bottom);
        anim_out = AnimationUtils.loadAnimation(getContext(), R.anim.abc_slide_out_bottom);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_video);
        assignViews();
        initListener();
    }

    private void initListener() {
        tvNative.setOnClickListener(mNativeListener);
        tvTake.setOnClickListener(mTakeListener);
        tvCancel.setOnClickListener(this);
    }

    public void setNativeListener(View.OnClickListener listener){
        this.mNativeListener = listener;
        if (tvNative != null) {
            tvNative.setOnClickListener(mNativeListener);
        }
    }

    public void setTakeListener(View.OnClickListener listener){
        this.mTakeListener = listener;
        if (tvTake != null){
            tvTake.setOnClickListener(mTakeListener);
        }
    }

    @Override
    public void onClick(View v) {
        dismiss();
    }

    @Override
    public void show() {
        super.show();
        ll_tv.startAnimation(anim_in);
    }

    @Override
    public void dismiss() {
        super.dismiss();
        ll_tv.startAnimation(anim_in);
        anim_out.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}
            @Override
            public void onAnimationEnd(Animation animation) {
                VideoDialog.super.dismiss();
            }
            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
    }
}

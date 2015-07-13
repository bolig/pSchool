package com.peoit.android.online.pschool.ui.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.andexert.library.RippleView;
import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.ui.Base.CommonUtil;

/**
 * author:libo
 * time:2015/7/10
 * E-mail:boli_android@163.com
 * last: ...
 */
public class PsActionBar extends RelativeLayout {
    private View action_view;
    private TextView action_title;
    private RippleView action_r;
    private RippleView action_l;
    private ImageView action_ir;
    private ImageView action_il;

    public PsActionBar(Context context) {
        super(context);
        if(isInEditMode()){
            return ;
        }
        init();
    }

    public PsActionBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        if(isInEditMode()){
            return ;
        }
        init();
    }

    private void init() {
        if (CommonUtil.mContext != null) {
            action_view = LayoutInflater.from(CommonUtil.mContext).inflate(R.layout.layout_actionbar, null);
            action_l = (RippleView) action_view.findViewById(R.id.action_l);
            action_title = (TextView) action_view.findViewById(R.id.action_title);
            action_r = (RippleView) action_view.findViewById(R.id.action_r);
            action_il = (ImageView) action_view.findViewById(R.id.action_il);
            action_ir = (ImageView) action_view.findViewById(R.id.action_ir);

            action_l.setVisibility(GONE);
            action_title.setVisibility(GONE);
            action_r.setVisibility(GONE);

            addView(action_view, CommonUtil.PARAM_MP_WC);

            action_view.post(new Runnable() {
                @Override
                public void run() {
                    LayoutParams params = (LayoutParams) action_view.getLayoutParams();
                    params.height = CommonUtil.dip2px(CommonUtil.mContext, 48);
                    params.width = LayoutParams.MATCH_PARENT;
                    action_view.setLayoutParams(params);
                }
            });
        }
    }

    /**
     * 设置左边按键单击事件
     *
     * @param onClickListener
     */
    public PsActionBar addLeftBtn(OnClickListener onClickListener) {
        addLeftBtn(-1, onClickListener);
        return this;
    }

    /**
     * 设置左边按键点击事件和图片资源
     *
     * @param drawResId
     * @param onClickListener
     */
    public PsActionBar addLeftBtn(int drawResId, OnClickListener onClickListener) {
        if (action_il == null || action_l == null)
            return this;
        action_l.setVisibility(CommonUtil.VISIBLE);
        if (drawResId > 0)
            action_il.setImageResource(drawResId);
        action_l.setOnClickListener(onClickListener);
        return this;
    }

    /**
     * 设置title
     *
     * @param title
     */
    public PsActionBar settitle(CharSequence title) {
        if (action_title != null) {
            action_title.setText(title);
            action_title.setVisibility(CommonUtil.VISIBLE);
        }
        return this;
    }

    /**
     * 设置右边按键单击事件
     *
     * @param onClickListener
     */
    public PsActionBar addRightBtn(OnClickListener onClickListener) {
        addRightBtn(-1, onClickListener);
        return this;
    }

    /**
     * 设置右边按键点击事件和图片资源
     *
     * @param drawResId
     * @param onClickListener
     */
    public PsActionBar addRightBtn(int drawResId, OnClickListener onClickListener){
        if (action_r == null || action_ir == null)
            return this;
        action_r.setVisibility(CommonUtil.VISIBLE);
        if (drawResId > 0)
            action_ir.setImageResource(drawResId);
        action_r.setOnClickListener(onClickListener);
        return this;
    }
}
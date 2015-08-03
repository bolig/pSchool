package com.peoit.android.online.pschool.ui.adapter;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.entity.SchoolDataInfo;

import java.util.List;

/**
 * author:libo
 * time:2015/7/31
 * E-mail:boli_android@163.com
 * last: ...
 */
public class SchoolInfoAdapter extends EntityAdapter<SchoolDataInfo>{

    public SchoolInfoAdapter(Activity mAc, int layoutId, List<SchoolDataInfo> dates) {
        super(mAc, layoutId, dates);
    }

    @Override
    protected ViewHolderBase getViewHolder() {
        return new ViewHolder();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void initView(int position, SchoolDataInfo data, ViewHolderBase holderBase, View convertView) {
        ViewHolder holder = (ViewHolder) holderBase;
        holder.tvTitle.setText(data.getTitle());
        holder.tvContent.setText(data.getContent());
        holder.tvTime.setText(data.getTime());
        holder.imageLoader(data.getImageUrl());

//        int height = CommonUtil.dip2px(mAc, 96);
//        int realHeight = CommonUtil.dip2px(mAc, 46);
//
//        ViewGroup.LayoutParams params = holder.tvContent.getLayoutParams();
//        ViewGroup.LayoutParams params1 = holder.tvTitle.getLayoutParams();
//
//        realHeight += params.height + params1.height;
//
//        if (height < realHeight){
//            FrameLayout.LayoutParams realParams = (FrameLayout.LayoutParams) convertView.getLayoutParams();
//            realParams.height = realHeight;
//            CardView view = (CardView) convertView;
//            view.measure(realParams.width, height);
//        }

//      int backgroundColor, float radius,
//      float shadowSize, float maxShadowSize
        //convertView.setBackground(new ShadowDrawable(mAc.getResources(), mAc.getResources().getColor(R.color.white_), 2f, 2f, 4f));
    }

    private class ViewHolder implements ViewHolderBase{

        ImageView ivIcon;
        TextView tvTitle;
        TextView tvContent;
        TextView tvTime;

        @Override
        public void inflaer() {
            ivIcon = (ImageView) findViewById(R.id.iv_icon);
            tvTitle = (TextView) findViewById(R.id.tv_title);
            tvContent = (TextView) findViewById(R.id.tv_content);
            tvTime = (TextView) findViewById(R.id.tv_time);
        }

        public void imageLoader(String url){
            Glide.with(mAc).load(url).centerCrop().crossFade().into(ivIcon);
        }
    }
}

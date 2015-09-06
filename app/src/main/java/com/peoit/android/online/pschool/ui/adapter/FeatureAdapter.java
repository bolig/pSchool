package com.peoit.android.online.pschool.ui.adapter;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.config.NetConstants;
import com.peoit.android.online.pschool.entity.FeatureInfo;
import com.peoit.android.online.pschool.ui.activity.NewsActivity;

import java.util.List;

/**
 * 专栏 item adapter
 * <p/>
 * author:libo
 * time:2015/8/4
 * E-mail:boli_android@163.com
 * last: ...
 */
public class FeatureAdapter extends EntityAdapter<FeatureInfo> {

    public FeatureAdapter(Activity mAc, int layoutId, List<FeatureInfo> dates) {
        super(mAc, layoutId, dates);
    }

    @Override
    protected ViewHolderBase getViewHolder() {
        return new ViewHolder();
    }

    @Override
    protected void initView(int position, final FeatureInfo data, ViewHolderBase holderBase, View convertView) {
        ViewHolder holder = (ViewHolder) holderBase;
        if (TextUtils.isEmpty(data.getVurl()) && TextUtils.isEmpty(data.getImgurl())){
            showImg(holder.ivIcon, data.getType());
        } else {
            Glide.with(mAc).load(TextUtils.isEmpty(data.getVurl()) ? NetConstants.IMAGE_HOST + data.getImgurl() : data.getImgurl()).error(getDrawable(data.getType())).into(holder.ivIcon);
        }
        holder.tvTitle.setText(data.getTitle());
        holder.tvContent.setText(data.getAbs());
        holder.tvTime.setText(data.getStimeStr());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewsActivity.startThisActivity(mAc,data);
            }
        });
    }

    private int getDrawable(String type) {
        if ("专家在线".equals(type)) {
            return  R.drawable.zhuanjiadefault;
        } else if ("亲子活动".equals(type)) {
            return R.drawable.qinz;
        } else if ("家长课堂".equals(type)) {
            return R.drawable.jianzhang;
        } else if ("健康顾问".equals(type)) {
            return R.drawable.jiank;
        } else if ("学习指导".equals(type)) {
            return R.drawable.jiaz;
        }
        return R.drawable.qinz;
    }

    private void showImg(ImageView ivIcon, String type) {
        if ("专家在线".equals(type)) {
            ivIcon.setImageResource(R.drawable.zhuanjiadefault);
        } else if ("亲子活动".equals(type)) {
            ivIcon.setImageResource(R.drawable.qinz);
        } else if ("家长课堂".equals(type)) {
            ivIcon.setImageResource(R.drawable.jianzhang);
        } else if ("健康顾问".equals(type)) {
            ivIcon.setImageResource(R.drawable.jiank);
        } else if ("学习指导".equals(type)) {
            ivIcon.setImageResource(R.drawable.jiaz);
        }
    }

    private class ViewHolder implements ViewHolderBase {

        private ImageView ivIcon;
        private TextView tvTitle;
        private TextView tvContent;
        private TextView tvTime;

        @Override
        public void inflaer(View convertView) {
            ivIcon = (ImageView) convertView.findViewById(R.id.iv_icon);
            tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
            tvContent = (TextView) convertView.findViewById(R.id.tv_content);
            tvTime = (TextView) convertView.findViewById(R.id.tv_time);
        }
    }
}

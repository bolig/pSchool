package com.peoit.android.online.pschool.ui.adapter;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.config.CommonUtil;
import com.peoit.android.online.pschool.entity.GradeStatInfo;

import java.util.List;

/**
 * author:libo
 * time:2015/8/9
 * E-mail:boli_android@163.com
 * last: ...
 */
public class GradeStatAdapter extends EntityAdapter<GradeStatInfo> {

    private final String title;

    public GradeStatAdapter(Activity mAc, int layoutId, List<GradeStatInfo> dates, String title) {
        super(mAc, layoutId, dates);
        this.title = title;
    }

    @Override
    protected ViewHolderBase getViewHolder() {
        return new ViewHolder();
    }

    @Override
    protected void initView(int position, GradeStatInfo data, ViewHolderBase holderBase, View convertView) {
        ViewHolder holder = (ViewHolder) holderBase;
        holder.tvTitle.setText(title);
        holder.tvContent.setText(data.getName());
        holder.tvTime.setText(data.getStartdateStr());
    }

    private class ViewHolder implements ViewHolderBase {

        @Override
        public void inflaer(View convertView) {
            ivIcon = (ImageView) convertView.findViewById(R.id.iv_icon);

            ivIcon.setVisibility(CommonUtil.GONE);

            tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
            tvContent = (TextView) convertView.findViewById(R.id.tv_content);
            tvTime = (TextView) convertView.findViewById(R.id.tv_time);
        }

        private ImageView ivIcon;
        private TextView tvTitle;
        private TextView tvContent;
        private TextView tvTime;

    }
}

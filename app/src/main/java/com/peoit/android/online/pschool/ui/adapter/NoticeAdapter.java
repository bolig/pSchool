package com.peoit.android.online.pschool.ui.adapter;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.entity.NoticeInfo;
import com.peoit.android.online.pschool.ui.activity.PushActivity;

import java.util.List;

/**
 * author:libo
 * time:2015/8/7
 * E-mail:boli_android@163.com
 * last: ...
 */
public class NoticeAdapter extends EntityAdapter<NoticeInfo> {

    public NoticeAdapter(Activity mAc, int layoutId, List<NoticeInfo> dates) {
        super(mAc, layoutId, dates);
    }

    @Override
    protected ViewHolderBase getViewHolder() {
        return new ViewHolder();
    }

    @Override
    protected void initView(int position, final NoticeInfo data, ViewHolderBase holderBase, View convertView) {
        ViewHolder holder = (ViewHolder) holderBase;
//        Glide.with(mAc).load(NetConstants.IMAGE_HOST + data.get).into(holder.ivIcon);//加载图片
        holder.tvTitle.setText(data.getTitle());
        holder.tvContent.setText(data.getContent());
        holder.tvTime.setText(data.getStimeStr());
        if (data.isAdd()) {
            holder.tvContent.setSingleLine(false);
        } else {
            holder.tvContent.setSingleLine(true);
        }
        holder.ivAdd.setSelected(data.isAdd());
        holder.ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.setIsAdd(!data.isAdd());
                notifyDataSetChanged();
            }
        });
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PushActivity.startThisActivity(mAc, PushActivity.NOTICE, data.getTitle(), data.getContent(), data.getStimeStr());
            }
        });
    }

    private class ViewHolder implements EntityAdapter.ViewHolderBase {
        private TextView tvTitle;
        private TextView tvContent;
        private TextView tvTime;
        private ImageView ivAdd;
        private ImageView ivIcon;

        @Override
        public void inflaer(View convertView) {
            ivIcon = (ImageView) convertView.findViewById(R.id.iv_icon);
            tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
            tvContent = (TextView) convertView.findViewById(R.id.tv_content);
            tvTime = (TextView) convertView.findViewById(R.id.tv_time);
            ivAdd = (ImageView) convertView.findViewById(R.id.iv_add);
        }
    }
}

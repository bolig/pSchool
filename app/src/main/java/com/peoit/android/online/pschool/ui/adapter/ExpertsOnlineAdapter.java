package com.peoit.android.online.pschool.ui.adapter;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.entity.FeatureInfo;
import com.peoit.android.online.pschool.ui.activity.NewsActivity;

import java.util.List;

/**
 * 专家在线
 *
 *
 */
public class ExpertsOnlineAdapter extends EntityAdapter<FeatureInfo>{

    public ExpertsOnlineAdapter(Activity mAc, int layoutId, List<FeatureInfo> dates) {
        super(mAc, layoutId, dates);
    }

    @Override
    protected ViewHolderBase getViewHolder() {
        return new ViewHolder();
    }

    @Override
    protected void initView(int position, final FeatureInfo data, ViewHolderBase holderBase, View convertView) {
        ViewHolder holder = (ViewHolder) holderBase;
        holder.tvTitle.setText(data.getTitle());
//        holder.tvContent.setText(data.getAbs());
        holder.tvTime.setText(data.getStimeStr()+"回复");


        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewsActivity.startThisActivity(mAc, data.getId(), data.getContent(), data.getType(), data.getTitle());
            }
        });
    }

    private class ViewHolder implements ViewHolderBase{

        private ImageView ivIcon;
        private TextView tvTitle;
        private TextView tvContent;
        private TextView tvTime;




        @Override
        public void inflaer(View convertView) {
//            ivIcon = (ImageView) convertView.findViewById(R.id.iv_icon);
            tvTitle = (TextView) convertView.findViewById(R.id.item_parentsclassroom_tv2);
//            tvContent = (TextView) convertView.findViewById(R.id.item_parentsclassroom_tv2);
            tvTime = (TextView) convertView.findViewById(R.id.item_parentsclassroom_tv3);
        }
    }
}

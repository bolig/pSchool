package com.peoit.android.online.pschool.ui.adapter;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.entity.ExpertsOnlineInfo;
import com.peoit.android.online.pschool.ui.activity.ExpertsOnlineContentActivity;

import java.util.List;

/**
 * 专家在线
 */
public class ExpertsOnlineAdapter extends EntityAdapter<ExpertsOnlineInfo> {

    public ExpertsOnlineAdapter(Activity mAc, int layoutId, List<ExpertsOnlineInfo> dates) {
        super(mAc, layoutId, dates);
    }

    @Override
    protected ViewHolderBase getViewHolder() {
        return new ViewHolder();
    }

    @Override
    protected void initView(int position, final ExpertsOnlineInfo data, ViewHolderBase holderBase, View convertView) {
        ViewHolder holder = (ViewHolder) holderBase;

        holder.tvTitle.setText(data.getUsername() + ":" + data.getText());
//        holder.tvContent.setText(data.getAbs());
        LinearLayout linearLayout = (LinearLayout) convertView.findViewById(R.id.item_parentsclassroom_ll);
        linearLayout.removeAllViews();
        if (data.getDis() != null && data.getDis().size() != 0) {
            for (int i = 0; i < data.getDis().size(); i++) {
                //动态添加
                TextView text = new TextView(mAc);
                text.setTextSize(18);
                text.setText(data.getDis().get(i).getUsername() + ":" + data.getDis().get(i).getText());
                linearLayout.addView(text);
            }
        }
        holder.tvTime.setText(data.getStimeStr() + "回复");

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                NewsActivity.startThisActivity(mAc, data.getId(), data.getText(), data.getUsername(), data.getUsername());
                ExpertsOnlineContentActivity.startThisActivity(mAc, data.getId(), data.getText(), data.getUsername(), data);
            }
        });
    }

    private class ViewHolder implements ViewHolderBase {

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

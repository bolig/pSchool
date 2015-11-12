package com.peoit.android.online.pschool.ui.adapter;

import android.app.Activity;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
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
        SpannableStringBuilder content1 = getContent(data.getUsertype(), data.getUsername() + ":", data.getText());
        holder.tvTitle.setText(content1);
        LinearLayout linearLayout = (LinearLayout) convertView.findViewById(R.id.item_parentsclassroom_ll);
        linearLayout.removeAllViews();
        if (data.getDis() != null && data.getDis().size() != 0) {
            for (int i = 0; i < data.getDis().size(); i++) {
                //动态添加
                TextView text = new TextView(mAc);
                text.setTextSize(16);
                SpannableStringBuilder content = getContent(data.getDis().get(i).getUsertype(), data.getDis().get(i).getUsername() + ":", data.getDis().get(i).getText());
                if (content == null)
                    continue;
                text.setText(content);
                linearLayout.addView(text);
            }
        }
        holder.tvTime.setText(data.getStimeStr() + "回复");
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExpertsOnlineContentActivity.startThisActivity(mAc, data.getId(), data.getText(), data.getUsername(), data);
            }
        });
        convertView.setBackgroundResource(R.drawable.list_item_sel);
    }

    private SpannableStringBuilder getContent(String type, String s, String text) {
        String msg = s + text;
        SpannableStringBuilder builder = new SpannableStringBuilder(msg);

        ForegroundColorSpan colorSpan_R = new ForegroundColorSpan(mAc.getResources().getColor(R.color.md_green_300));
        ForegroundColorSpan colorSpan_Q = new ForegroundColorSpan(mAc.getResources().getColor(R.color.md_red_300));

        builder.setSpan(("3".equals(type) || "4".equals(type)) ? colorSpan_Q : colorSpan_R, 0, s.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return builder;
    }

    private class ViewHolder implements ViewHolderBase {
        private ImageView ivIcon;
        private TextView tvTitle;
        private TextView tvContent;
        private TextView tvTime;

        @Override
        public void inflaer(View convertView) {
            tvTitle = (TextView) convertView.findViewById(R.id.item_parentsclassroom_tv2);
            tvTime = (TextView) convertView.findViewById(R.id.item_parentsclassroom_tv3);
        }
    }
}

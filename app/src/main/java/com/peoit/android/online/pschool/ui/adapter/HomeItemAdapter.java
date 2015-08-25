package com.peoit.android.online.pschool.ui.adapter;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.config.CommonUtil;
import com.peoit.android.online.pschool.entity.HomeItemInfo;

import java.util.List;

/**
 * author:libo
 * time:2015/8/25
 * E-mail:boli_android@163.com
 * last: ...
 */
public class HomeItemAdapter extends EntityAdapter<HomeItemInfo> {

    private final int childWitd;
    private OnItemListener mListener;

    public HomeItemAdapter(Activity mAc, int layoutId, List<HomeItemInfo> dates) {
        super(mAc, layoutId, dates);
        childWitd = (CommonUtil.w_screeen - CommonUtil.dip2px(mAc, 2)) / 3;
    }

    @Override
    protected ViewHolderBase getViewHolder() {
        return new ViewHolder();
    }

    @Override
    protected void initView(final int position, HomeItemInfo data, ViewHolderBase holderBase, View convertView) {
        ViewHolder holder = (ViewHolder) holderBase;
        holder.ivIcon.setImageResource(data.getRes_icon());
        holder.tvTitle.setText(data.getTitle());
        if (data.isAddMark() && markCount > 0) {
            holder.tvMarkCount.setVisibility(View.VISIBLE);
            holder.tvMarkCount.setText(getMarkCount());
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    markCount = 0;
                    notifyDataSetChanged();
                    if (mListener != null)
                        mListener.onItem(position);
                }
            });
        } else {
            holder.tvMarkCount.setVisibility(View.GONE);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null)
                        mListener.onItem(position);
                }
            });
        }

        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) holder.ll_item.getLayoutParams();
        layoutParams.width = childWitd;
        layoutParams.height = childWitd;
        holder.ll_item.setLayoutParams(layoutParams);
    }

    private int markCount = 0;

    public String getMarkCount() {
        String markCountStr = markCount > 100 ? "99+" : markCount + "";
        return markCountStr;
    }

    public void changeMarkCount(int markCount) {
        if (markCount > 0) {
            this.markCount = markCount;
            notifyDataSetChanged();
        }
    }

    private class ViewHolder implements ViewHolderBase {

        private ImageView ivIcon;
        private TextView tvMarkCount;
        private TextView tvTitle;
        private LinearLayout ll_item;

        @Override
        public void inflaer(View convertView) {
            ivIcon = (ImageView) convertView.findViewById(R.id.iv_icon);
            tvMarkCount = (TextView) convertView.findViewById(R.id.tv_mark_count);
            tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
            ll_item = (LinearLayout) convertView.findViewById(R.id.ll_item);
        }
    }

    public void setOnItemListener(OnItemListener listener){
        this.mListener = listener;
    }

    public interface OnItemListener{
        void onItem(int position);
    }
}

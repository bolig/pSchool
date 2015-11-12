package com.peoit.android.online.pschool.ui.adapter;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.config.CommonUtil;
import com.peoit.android.online.pschool.config.NetConstants;
import com.peoit.android.online.pschool.entity.ExpertListInfo;
import com.peoit.android.online.pschool.ui.activity.ExpertDetsActivity;

/**
 * author:libo
 * time:2015/10/13
 * E-mail:boli_android@163.com
 * last: ...
 */
public class ExpertListAdapter1 extends EntityAdapter<ExpertListInfo> {
    private final int mItemWidth;

    public ExpertListAdapter1(Activity mAc, int layoutId) {
        super(mAc, layoutId);
        mItemWidth = CommonUtil.w_screeen / 2;
    }

    @Override
    protected ViewHolderBase getViewHolder() {
        return new ViewHolder();
    }

    @Override
    public int getCount() {
        int count = getDates().size();
        if (count % 2 == 0) {
            return count / 2;
        } else {
            return count / 2 + 1;
        }
    }

    @Override
    protected void initView(int position, final ExpertListInfo data, ViewHolderBase holderBase, View convertView) {
        ViewHolder mHolder = (ViewHolder) holderBase;
        int index = position * 2;
        final ExpertListInfo info1 = getItem(index);
        mHolder.rlItem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExpertDetsActivity.startThisActivity(mAc, info1);
            }
        });
        Glide.with(mAc).load(NetConstants.IMAGE_HOST + info1.getZjpic())
                .error(R.drawable.user_avater).
                into(mHolder.ivIcon1);
        mHolder.tvTitle1.setText(info1.getNickname());
        boolean isItem = (index + 1) > getDates().size() - 1;
        if (isItem) {
            mHolder.rlItem2.setVisibility(View.INVISIBLE);
        } else {
            mHolder.rlItem2.setVisibility(View.VISIBLE);
            final ExpertListInfo info2 = getItem(index + 1);
            mHolder.rlItem2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ExpertDetsActivity.startThisActivity(mAc, info2);
                }
            });
            mHolder.tvTitle2.setText(info2.getNickname());
            Glide.with(mAc).load(NetConstants.IMAGE_HOST + info2.getZjpic())
                    .error(R.drawable.user_avater).
                    into(mHolder.ivIcon2);
        }
    }

    public class ViewHolder implements ViewHolderBase {
        private RelativeLayout rlItem1;
        private ImageView ivIcon1;
        private TextView tvTitle1;
        private RelativeLayout rlItem2;
        private ImageView ivIcon2;
        private TextView tvTitle2;

        @Override
        public void inflaer(View convertView) {
            rlItem1 = (RelativeLayout) convertView.findViewById(R.id.rl_item1);
            ivIcon1 = (ImageView) convertView.findViewById(R.id.iv_icon1);
            tvTitle1 = (TextView) convertView.findViewById(R.id.tv_title1);

            rlItem2 = (RelativeLayout) convertView.findViewById(R.id.rl_item2);
            ivIcon2 = (ImageView) convertView.findViewById(R.id.iv_icon2);
            tvTitle2 = (TextView) convertView.findViewById(R.id.tv_title2);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(mItemWidth, mItemWidth);
            LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(mItemWidth, mItemWidth);

            rlItem1.setLayoutParams(params);
            rlItem2.setLayoutParams(params1);
        }
    }
}

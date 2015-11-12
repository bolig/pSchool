package com.peoit.android.online.pschool.ui.adapter;

import android.app.Activity;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.config.NetConstants;
import com.peoit.android.online.pschool.entity.ExpertListInfo;
import com.peoit.android.online.pschool.ui.activity.ExpertDetActivity;

/**
 * author:libo
 * time:2015/10/13
 * E-mail:boli_android@163.com
 * last: ...
 */
public class ExpertListAdapter extends EntityAdapter<ExpertListInfo> {
    public ExpertListAdapter(Activity mAc, int layoutId) {
        super(mAc, layoutId);
    }

    @Override
    protected ViewHolderBase getViewHolder() {
        return new ViewHolder();
    }

    @Override
    protected void initView(int position, final ExpertListInfo data, ViewHolderBase holderBase, View convertView) {
        ViewHolder mHolder = (ViewHolder) holderBase;
        mHolder.tvTitle.setText(
                data.getNickname());
        Glide.with(mAc).load(NetConstants.IMAGE_HOST + data.getZjpic())
                .error(R.drawable.user_avater).
                into(mHolder.ivVideo);
        mHolder.tvContent.setText(TextUtils.isEmpty(data.getZjjj()) ? "" : Html.fromHtml(data.getZjjj()));
        mHolder.tvdet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExpertDetActivity.startThisActivity(mAc,
                        data.getZjjj(),
                        data.getNickname());
            }
        });
    }

    public class ViewHolder implements ViewHolderBase {
        private RelativeLayout rlImg;
        private ImageView ivVideo;
        private TextView tvTitle;
        private TextView tvContent;
        private TextView tvdet;

        @Override
        public void inflaer(View convertView) {
            rlImg = (RelativeLayout) convertView.findViewById(R.id.rl_img);
            ivVideo = (ImageView) convertView.findViewById(R.id.iv_video);
            tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
            tvContent = (TextView) convertView.findViewById(R.id.tv_content);
            tvdet = (TextView) convertView.findViewById(R.id.tv_det);
        }
    }
}

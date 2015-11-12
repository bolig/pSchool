package com.peoit.android.online.pschool.ui.adapter;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.entity.VideoInfo;
import com.peoit.android.online.pschool.ui.activity.VideoActivity;

/**
 * author:libo
 * time:2015/10/12
 * E-mail:boli_android@163.com
 * last: ...
 */
public class VideoAdapter extends EntityAdapter<VideoInfo> {
    public VideoAdapter(Activity mAc, int layoutId) {
        super(mAc, layoutId);
    }

    @Override
    protected ViewHolderBase getViewHolder() {
        return new ViewHolder();
    }

    @Override
    protected void initView(int position, final VideoInfo data, ViewHolderBase holderBase, View convertView) {
        ViewHolder mHolder = (ViewHolder) holderBase;
        Glide.with(mAc).load(data.getImgurl()).into(mHolder.ivVideo);
        mHolder.tvTitle.setText(data.getTitle());
        mHolder.tvContent.setText(data.getAbs());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VideoActivity.startThisActivity(mAc, data.getVideourl(), data.getImgurl());
            }
        });
    }

    private class ViewHolder implements ViewHolderBase {
        private RelativeLayout rlImg;
        private ImageView ivVideo;
        private TextView tvTitle;
        private TextView tvContent;

        @Override
        public void inflaer(View convertView) {
            rlImg = (RelativeLayout) convertView.findViewById(R.id.rl_img);
            ivVideo = (ImageView) convertView.findViewById(R.id.iv_video);
            tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
            tvContent = (TextView) convertView.findViewById(R.id.tv_content);
        }
    }
}

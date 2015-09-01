package com.peoit.android.online.pschool.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.ui.Base.BaseActivity;

public class VideoActivity extends BaseActivity {

    private VideoView vv_video;
    private Uri mVideoUri;
    private ImageView iv_play;
    private Animation anim_in;
    private Animation anim_out;
    private ImageView iv_img;
    private ProgressBar pb_loading;
    private String imageUrl;
    private boolean isNetWork = false;
    private String uri;
    private Bitmap mVideoBitmap;

    public static void startThisActivity(Activity mAc, String videoUri, String img) {
        Intent intent = new Intent(mAc, VideoActivity.class);
        intent.putExtra("uri", videoUri);
        intent.putExtra("img", img);
        mAc.startActivity(intent);
    }

    public static void startThisActivity(Activity mAc, String videoUri) {
        Intent intent = new Intent(mAc, VideoActivity.class);
        intent.putExtra("uri", videoUri);
        mAc.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isMainUI = false;
        setContentView(R.layout.act_video);
    }

    @Override
    public void initData() {
        // 经测试在代码里直接声明透明状态栏更有效
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
        uri = getIntent().getStringExtra("uri");
        if (TextUtils.isEmpty(uri)) {
            showToast("数据传输错误");
            finish();
            return;
        }

        imageUrl = getIntent().getStringExtra("img");
        if (TextUtils.isEmpty(imageUrl)) {
            mVideoBitmap = ThumbnailUtils.createVideoThumbnail(uri, MediaStore.Video.Thumbnails.MICRO_KIND);
        } else {
            isNetWork = true;
        }

        mVideoUri = Uri.parse(uri);

        anim_in = AnimationUtils.loadAnimation(mContext, R.anim.anim_play_btn_in);
        anim_out = AnimationUtils.loadAnimation(mContext, R.anim.anim_play_btn_out);
    }

    @Override
    public void initView() {
        vv_video = (VideoView) findViewById(R.id.vv_video);
        iv_play = (ImageView) findViewById(R.id.iv_paly);
        iv_img = (ImageView) findViewById(R.id.iv_img);
        pb_loading = (ProgressBar) findViewById(R.id.pb_loading);

        changeViewDisplay(false);

        if (isNetWork) {
            Glide.with(mContext).load(imageUrl).into(iv_img);
        } else {
            iv_img.setImageBitmap(mVideoBitmap);
        }

        vv_video.setMediaController(new MediaController(mContext));
        if (isNetWork){
            vv_video.setVideoURI(mVideoUri);
            vv_video.requestFocus();
        } else {
            vv_video.setVideoPath(uri);
            vv_video.start();
            changeViewDisplay(true);
            iv_img.setVisibility(View.GONE);
            iv_play.setVisibility(View.GONE);
        }
    }

    private void changeViewDisplay(boolean isVideoDisplay) {
        if (isVideoDisplay) {
            iv_play.setVisibility(View.VISIBLE);
            iv_img.setVisibility(View.VISIBLE);
            pb_loading.setVisibility(View.GONE);
        } else {
            iv_play.setVisibility(View.GONE);
            iv_img.setVisibility(View.VISIBLE);
            pb_loading.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void initListener() {
        iv_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vv_video.start();
                iv_img.setVisibility(View.GONE);
                iv_play.startAnimation(anim_out);
                iv_play.setVisibility(View.GONE);
            }
        });

        vv_video.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                finish();
            }
        });

        vv_video.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                return false;
            }
        });

        vv_video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                if (isNetWork)
                    changeViewDisplay(true);
            }
        });
    }
}

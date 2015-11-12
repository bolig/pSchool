package com.peoit.android.online.pschool.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.net.UpYunAsyncTask;
import com.peoit.android.online.pschool.ui.Base.BaseActivity;
import com.peoit.android.online.pschool.ui.Presenter.UploadImgPresenter;
import com.peoit.android.online.pschool.utils.BitmapUtils;
import com.peoit.android.online.pschool.utils.FileUtils;
import com.peoit.android.online.pschool.utils.MyLogger;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import me.iwf.photopicker.PhotoPagerActivity;


public class UploadVideoActivity extends BaseActivity {
    private UpYunAsyncTask upYunAsyncTask;

    private String videoPath;
    private Bitmap mVideoBitmap;

    private RelativeLayout rlVideo;
    private ImageView ivVideo;
    private EditText etTitle;
    private EditText etContent;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                showToast("数据传输有误");
                finish();
                return;
            }
            getUIShowPresenter().doShowData();
        }
    };

    private String title;
    private String content;
    private String mCurentArea;

    public static void startThisActivity(Activity mAc, String videoPath, String area) {
        Intent intent = new Intent(mAc, UploadVideoActivity.class);
        intent.putExtra("path", videoPath);
        intent.putExtra("area", area);
        mAc.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_upload_video);
    }

    @Override
    public void initData() {
        videoPath = getIntent().getStringExtra("path");
        mCurentArea = getIntent().getStringExtra("area");
        if (TextUtils.isEmpty(videoPath) || TextUtils.isEmpty(mCurentArea)) {
            showToast("数据传输错误");
            finish();
            return;
        }
        getUIShowPresenter().doShowloading();
        mVideoBitmap = BitmapUtils.compressBitmap(videoPath);
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (FileUtils.saveBitmap(mVideoBitmap)) {
                    mHandler.sendEmptyMessage(0);
                } else {
                    mHandler.sendEmptyMessage(1);
                }
            }
        }).start();
    }

    @Override
    public void initView() {
        getPsActionBar().settitle("上传图片").addRightBtn(R.drawable.ic_ok, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (match()) {
                    //uploadVideo(videoPath);
                    try {
                        new UploadImgPresenter(UploadVideoActivity.this).uploadImg(videoPath, mCurentArea, title, content);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        showToast("图片解析失败");
                    }
                }
            }
        });
//        rlVideo = (RelativeLayout) findViewById(R.id.rl_video);
//        ivVideo = (ImageView) findViewById(R.id.iv_video);

        rlVideo = (RelativeLayout) findViewById(R.id.rl_video);
        ivVideo = (ImageView) findViewById(R.id.iv_video);
        etTitle = (EditText) findViewById(R.id.et_title);
        etContent = (EditText) findViewById(R.id.et_content);

        ivVideo.setImageBitmap(mVideoBitmap);
    }

    @Override
    public void initListener() {
        ivVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ImageBrowActivity.startThisActivity(mContext, videoPath, false);
                Intent intent = new Intent(mContext, PhotoPagerActivity.class);
                intent.putExtra(PhotoPagerActivity.EXTRA_CURRENT_ITEM, 0);
                ArrayList<String> imgs = new ArrayList<String>();
                imgs.add(videoPath);
                intent.putExtra(PhotoPagerActivity.EXTRA_PHOTOS, imgs);
                mContext.startActivityForResult(intent, 2);
            }
        });
    }

//    public void uploadVideo(final String strVideoPath) {
//        upYunAsyncTask = new UpYunAsyncTask(true);
//        upYunAsyncTask.execute(strVideoPath);
//        upYunAsyncTask.setmListener(new UpYunAsyncTask.OnProgressListener() {
//
//            @Override
//            public void onProgresss(long curProgress, long totalProgress) {
//                showLoadingDialog("上传进度: " + getProgress(curProgress, totalProgress) + "/100");
//            }
//
//            @Override
//            public void onSuccess(boolean isComplete, String result, String error) {
//                showLoadingDialog("正在保存...");
//                MyLogger.e("result = " + result);
//                MyLogger.e("error = " + error);
//                if (!TextUtils.isEmpty(result) && TextUtils.isEmpty(error)) {
//                    try {
//                        JSONObject json = new JSONObject(result);
//                        String arg = json.getString("args");
//                        VideoSuccessInfo info = new Gson().fromJson(arg, VideoSuccessInfo.class);
//
//                        String videoOnServerUrl = info.getPath();
//
//                        if (!TextUtils.isEmpty(videoOnServerUrl)) {
//                            uploadPhoto(videoOnServerUrl);
//                        } else {
//                            hideLoadingDialog();
//                            showToast("上传失败");
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                        hideLoadingDialog();
//                        showToast("上传失败");
//                    }
//                } else {
//                    hideLoadingDialog();
//                    showToast("上传失败");
//                }
//            }
//        });
//    }
//
//    private void uploadPhoto(final String videoOnServerUrl) {
//        UpYunAsyncTask photoAsyncTask = new UpYunAsyncTask(false);
//        photoAsyncTask.execute(FileUtils.FILE_PATH);
//        photoAsyncTask.setmListener(new UpYunAsyncTask.OnProgressListener() {
//            @Override
//            public void onProgresss(long curProgress, long totalProgress) {
//                showLoadingDialog("正在保存...");
//            }
//
//            @Override
//            public void onSuccess(boolean isComplete, String result, String error) {
//                showLoadingDialog("正在保存...");
//                MyLogger.e("result = " + result);
//                MyLogger.e("error = " + error);
//
//                if (!TextUtils.isEmpty(result) && TextUtils.isEmpty(error)) {
//                    try {
//                        JSONObject json = new JSONObject(result);
//                        String arg = json.getString("args");
//                        VideoSuccessInfo info = new Gson().fromJson(arg, VideoSuccessInfo.class);
//                        String path = info.getPath();
//                        if (!TextUtils.isEmpty(path)) {
//                            uploadToServer(videoPath, path);
//                        } else {
//                            hideLoadingDialog();
//                            showToast("上传失败");
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                        hideLoadingDialog();
//                        showToast("上传失败");
//                    }
//                } else {
//                    hideLoadingDialog();
//                    showToast("上传失败");
//                }
//            }
//        });
//    }

//    private void uploadToServer(String videoUrl, String photoUrl) {
//        VideoPresenter presenter = new VideoPresenter(UploadVideoActivity.this);
//        presenter.doUploadVideo(UpYunAsyncTask.NET_HOST + videoUrl, UpYunAsyncTask.NET_HOST + photoUrl, title, content);
//    }

    private boolean match() {
        title = etTitle.getText().toString();
        if (TextUtils.isEmpty(title)) {
            showToast("请输入标题");
            return false;
        }
        content = etContent.getText().toString();
        if (TextUtils.isEmpty(content)) {
            showToast("说点什么吧");
            return false;
        }
        return true;
    }

    private String getProgress(long curProgress, long totalProgress) {
        double progress = (curProgress * 100d / totalProgress);
        int pro = (int) progress;
        MyLogger.e("pro = " + progress + "," + pro);
        MyLogger.e("progress = " + curProgress + "," + totalProgress);
        return pro + "";
    }
}

package com.peoit.android.online.pschool.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.net.UpYunAsyncTask;
import com.peoit.android.online.pschool.ui.Base.BaseActivity;
import com.peoit.android.online.pschool.ui.Presenter.ParentClassroomPresenter;
import com.peoit.android.online.pschool.ui.dialog.VideoDialog;
import com.peoit.android.online.pschool.ui.view.PullToRefreshLayout;
import com.peoit.android.online.pschool.ui.view.PullableListView;
import com.peoit.android.online.pschool.utils.ImagePhotoUtil;
import com.peoit.android.online.pschool.utils.ImageSelectUtil;

/**
 * 亲子活动
 * Created by zyz on 2015/8/11.
 */
public class FamilyActivitiy extends BaseActivity {

    private static final int REQUEST_CODE_TAKE_VIDEO = 0;
    private static final int REQUEST_CODE_VIDEO = 1;

    private PullableListView list;
    private PullToRefreshLayout refreshLayout;
    private ParentClassroomPresenter featurePersenter;
    private String strVideoPath;
    private UpYunAsyncTask upYunAsyncTask;
    private VideoDialog mVideoDialog;
    private String imgPath;
    private String mCurrentArea;

    public static void startThisActivity(Activity mAc, String area) {
        Intent intent = new Intent(mAc, FamilyActivitiy.class);
        intent.putExtra("area", area);
        mAc.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_pulllist_layout_nopadding);
        getPsActionBar().settitle(getCurTitle()).addRightBtn(R.drawable.ic_vido, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mVideoDialog = new VideoDialog(mContext);
                mVideoDialog.show();
                mVideoDialog.setNativeListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mVideoDialog.dismiss();
                        doTakeVideo();
                    }
                });
                mVideoDialog.setTakeListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mVideoDialog.dismiss();
                        doVideo();
                    }
                });
            }
        });
    }

    private CharSequence getCurTitle() {
        if ("网校亲子活动".equals(mCurrentArea)) {
            return "阳光亲子营";
        } else {
            return mCurrentArea + "阳光亲子营";
        }
    }

    private static final int SELECT_IMG = 0x00000001;
    private static final int PHOTO_IMG = 0x00000002;

    /**
     * 选择本地视频
     */
    private void doTakeVideo() {
        ImageSelectUtil.toSelectImg(mContext, SELECT_IMG);
    }

    /**
     * 启动视频拍摄
     */
    private void doVideo() {
        ImagePhotoUtil.toTakePhoto(mContext, false, PHOTO_IMG);
    }

    @Override
    public void initData() {
        mCurrentArea = getIntent().getStringExtra("area");
        if (TextUtils.isEmpty(mCurrentArea)) {
            showToast("数据传输错误");
            finish();
            return;
        }
        featurePersenter = new ParentClassroomPresenter(this, "亲子活动");
        featurePersenter.load(mCurrentArea);
    }

    @Override
    public void initView() {
        list = (PullableListView) findViewById(R.id.pull_list);
        refreshLayout = (PullToRefreshLayout) findViewById(R.id.pull_layout);
        refreshLayout.setTag("1");
        list.setAdapter(featurePersenter.getAdapter());
    }

    @Override
    public void initListener() {
        refreshLayout.setOnRefreshListener(featurePersenter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK)
            return;
        if (requestCode == SELECT_IMG) {
            imgPath = ImageSelectUtil.getImgPath(mContext, data);
            UploadVideoActivity.startThisActivity(mContext, imgPath, mCurrentArea);
        } else if (requestCode == PHOTO_IMG) {
            imgPath = ImagePhotoUtil.getCurrentPath();
            UploadVideoActivity.startThisActivity(mContext, imgPath, mCurrentArea);
        }
    }
}

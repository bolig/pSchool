package com.peoit.android.online.pschool.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.net.UpYunAsyncTask;
import com.peoit.android.online.pschool.ui.Base.BaseActivity;
import com.peoit.android.online.pschool.ui.Presenter.ParentClassroomPresenter;
import com.peoit.android.online.pschool.ui.dialog.VideoDialog;
import com.peoit.android.online.pschool.ui.view.PullToRefreshLayout;
import com.peoit.android.online.pschool.ui.view.PullableListView;
import com.peoit.android.online.pschool.utils.MyLogger;

/**
 * 亲子活动
 * Created by zyz on 2015/8/11.
 */
public class FamilyActivitiy extends BaseActivity {

    private static final int REQUEST_CODE_TAKE_VIDEO = 0;

    private PullableListView list;
    private PullToRefreshLayout refreshLayout;
    private ParentClassroomPresenter featurePersenter;
    private String strVideoPath;
    private UpYunAsyncTask upYunAsyncTask;
    private VideoDialog mVideoDialog;

    public static void startThisActivity(Activity mAc) {
        Intent intent = new Intent(mAc, FamilyActivitiy.class);
        mAc.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_pulllist_layout_nopadding);
        getPsActionBar().settitle("亲子活动").addRightBtn(R.drawable.ic_vido, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mVideoDialog = new VideoDialog(mContext);
                mVideoDialog.show();
                mVideoDialog.setNativeListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mVideoDialog.dismiss();
                        showToast("暂未开放...");
                    }
                });
                mVideoDialog.setTakeListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mVideoDialog.dismiss();
                        doVideo();
                    }
                });
                //doVideo();
            }
        });
    }

    /**
     * 启动视频拍摄
     */
    private void doVideo() {
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
        startActivityForResult(intent, REQUEST_CODE_TAKE_VIDEO);
    }

    @Override
    public void initData() {
        featurePersenter = new ParentClassroomPresenter(this, "亲子活动");
        featurePersenter.load();
    }

    @Override
    public void initView() {
        list = (PullableListView) findViewById(R.id.pull_list);
        refreshLayout = (PullToRefreshLayout) findViewById(R.id.pull_layout);
        list.setAdapter(featurePersenter.getAdapter());
    }

    @Override
    public void initListener() {
        refreshLayout.setOnRefreshListener(featurePersenter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Uri uriVideo = data.getData();
            Cursor cursor = this.getContentResolver().query(uriVideo, null, null, null, null);
            if (cursor.moveToNext()) {
                /* _data：文件的绝对路径 ，_display_name：文件名 */
                strVideoPath = cursor.getString(cursor.getColumnIndex("_data"));
                MyLogger.e("Video Url Path = " + strVideoPath);
                UploadVideoActivity.startThisActivity(mContext, strVideoPath);
            }
        }
    }
}

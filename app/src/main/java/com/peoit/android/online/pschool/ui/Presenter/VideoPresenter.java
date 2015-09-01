package com.peoit.android.online.pschool.ui.Presenter;

import com.peoit.android.online.pschool.ActBase;
import com.peoit.android.online.pschool.EntityBase;
import com.peoit.android.online.pschool.config.NetConstants;
import com.peoit.android.online.pschool.net.CallBack;
import com.peoit.android.online.pschool.ui.Base.BasePresenter;

import java.util.Map;

/**
 * author:libo
 * time:2015/8/31
 * E-mail:boli_android@163.com
 * last: ...
 */
public class VideoPresenter extends BasePresenter {

    private String abs;
    private String title;
    private String imgUrl;
    private String videoUrl;

    public VideoPresenter(ActBase actBase) {
        super(actBase);
    }

    public void doUploadVideo(String videoUrl, String imgurl, String title, String abs){
        this.videoUrl = videoUrl;
        this.imgUrl = imgurl;
        this.title = title;
        this.abs = abs;
        request(NetConstants.NET_ADDVIDEO, new CallBack() {
            @Override
            public void onSimpleFailure(int error, String errorMsg) {
                mActBase.onResponseFailure(error, errorMsg);
            }

            @Override
            public void onSimpleSuccess(EntityBase result) {
                mActBase.showToast("上传成功");
                mActBase.finish();
            }

            @Override
            public void onFinish() {
                mActBase.hideLoadingDialog();
            }
        });
    }

    @Override
    public Map<String, String> getParams() {
        Map<String, String> params = getSignParams();
        params.put("videourl", videoUrl);
        params.put("imgurl", imgUrl);
        params.put("title", title);
        params.put("abs", abs);
        return params;
    }

    @Override
    public Class getGsonClass() {
        return null;
    }
}

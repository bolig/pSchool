package com.peoit.android.online.pschool.ui.Presenter;

import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.peoit.android.online.pschool.ActBase;
import com.peoit.android.online.pschool.config.NetConstants;
import com.peoit.android.online.pschool.entity.BannerInfo;
import com.peoit.android.online.pschool.net.CallBack;
import com.peoit.android.online.pschool.ui.Base.BasePresenter;

import java.util.Map;

/**
 * author:libo
 * time:2015/9/2
 * E-mail:boli_android@163.com
 * last: ...
 */
public class BannerPresenter extends BasePresenter<BannerInfo> {

    private final WebView wvNews;
    private long id;

    public BannerPresenter(ActBase actBase, WebView tvNews) {
        super(actBase);
        this.wvNews = tvNews;
    }

    public void doLoadBannerInfo(long id) {
        this.id = id;
        mActBase.showLoadingDialog("正在加载...");
        request(NetConstants.NET_BANNER_INFO, new CallBack<BannerInfo>() {
            @Override
            public void onSimpleFailure(int error, String errorMsg) {
                mActBase.onResponseFailure(error, errorMsg);
            }

            @Override
            public void onSimpleSuccess(BannerInfo result) {
                if (result != null && !result.isNull()){
                    addWebHtml(result.getContent());
                }
            }

            @Override
            public void onFinish() {
                mActBase.hideLoadingDialog();
            }
        });
    }

    private void addWebHtml(String html) {
        wvNews.loadDataWithBaseURL(NetConstants.IMAGE_HOST, html, "text/html", "utf-8", null);
        wvNews.getSettings().setJavaScriptEnabled(true);
        wvNews.getSettings().setBuiltInZoomControls(true);
        wvNews.getSettings().setSupportZoom(true);
        wvNews.setSaveEnabled(true);
        wvNews.setWebChromeClient(new WebChromeClient());
    }

    @Override
    public Map<String, String> getParams() {
        Map<String, String> params = getSignParams();
        params.put("id", id + "");
        return params;
    }

    @Override
    public Class<BannerInfo> getGsonClass() {
        return BannerInfo.class;
    }
}

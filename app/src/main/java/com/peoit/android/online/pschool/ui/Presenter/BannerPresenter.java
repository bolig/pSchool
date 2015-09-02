package com.peoit.android.online.pschool.ui.Presenter;

import android.text.Html;
import android.widget.TextView;

import com.peoit.android.online.pschool.ActBase;
import com.peoit.android.online.pschool.config.NetConstants;
import com.peoit.android.online.pschool.entity.BannerInfo;
import com.peoit.android.online.pschool.net.CallBack;
import com.peoit.android.online.pschool.ui.Base.BasePresenter;
import com.peoit.android.online.pschool.utils.HtmlImageGetter;

import java.util.Map;

/**
 * author:libo
 * time:2015/9/2
 * E-mail:boli_android@163.com
 * last: ...
 */
public class BannerPresenter extends BasePresenter<BannerInfo> {

    private final TextView tvNews;
    private int id;

    public BannerPresenter(ActBase actBase, TextView tvNews) {
        super(actBase);
        this.tvNews = tvNews;
    }

    public void doLoadBannerInfo(int id) {
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
                    tvNews.setText(Html.fromHtml(result.getContent(), new HtmlImageGetter(mActBase.getActivity(), tvNews), null));
                }
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
        params.put("id", id + "");
        return params;
    }

    @Override
    public Class<BannerInfo> getGsonClass() {
        return BannerInfo.class;
    }
}

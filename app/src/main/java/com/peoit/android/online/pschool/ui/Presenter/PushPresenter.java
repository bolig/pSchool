package com.peoit.android.online.pschool.ui.Presenter;

import com.peoit.android.online.pschool.ActBase;
import com.peoit.android.online.pschool.config.NetConstants;
import com.peoit.android.online.pschool.entity.NoticeInfo;
import com.peoit.android.online.pschool.net.CallBack;
import com.peoit.android.online.pschool.ui.Base.BasePresenter;

import java.util.Map;

/**
 * author:libo
 * time:2015/8/13
 * E-mail:boli_android@163.com
 * last: ...
 */
public abstract class PushPresenter extends BasePresenter<NoticeInfo> {

    private Map<String, String> params;

    public PushPresenter(ActBase actBase) {
        super(actBase);
    }

    public void doQueryPushInfo(String id){
        mActBase.showLoadingDialog("正在刷新...");
        params = getSignParams();
        params.put("id", id);
        request(NetConstants.NET_PUSH, new CallBack<NoticeInfo>() {
            @Override
            public void onFinish() {
                mActBase.hideLoadingDialog();
            }

            @Override
            public void onSimpleFailure(int error, String errorMsg) {
                mActBase.onResponseFailure(error, errorMsg);
            }

            @Override
            public void onSimpleSuccess(NoticeInfo result) {
                PushPresenter.this.onSimpleSuccess(result.getTitle(), result.getContent(), result.getStimeStr());
            }
        });
    }

    protected abstract void onSimpleSuccess(String title, String content, String stimeStr);

    @Override
    public Map<String, String> getParams() {
        return params;
    }

    @Override
    public Class<NoticeInfo> getGsonClass() {
        return NoticeInfo.class;
    }
}

package com.peoit.android.online.pschool.ui.Presenter;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.peoit.android.online.pschool.ActNetBase;
import com.peoit.android.online.pschool.PresenterNetBase;
import com.peoit.android.online.pschool.config.NetConstants;
import com.peoit.android.online.pschool.entity.UserInfo;
import com.peoit.android.online.pschool.net.CommonServer;
import com.peoit.android.online.pschool.net.base.CallBack;
import com.peoit.android.online.pschool.ui.Base.BasePresenter;

/**
 * author:libo
 * time:2015/7/13
 * E-mail:boli_android@163.com
 * last: ...
 */
public abstract class EntityPresenter extends BasePresenter implements PresenterNetBase {
    private final ActNetBase actNetBase;

    public EntityPresenter(ActNetBase actBase, RequestQueue queue){
        this.actNetBase = actBase;
    }

    @Override
    public void request() {
        Request request = new CommonServer<UserInfo>().getRequest(NetConstants.HOST, UserInfo.class, new CallBack<UserInfo>() {

            @Override
            public void onSimpleSuccess(UserInfo result) {
                actNetBase.responseSuccess(result);
            }

            @Override
            public void onSimpleFailure(VolleyError error) {
                actNetBase.responseFailure(1);
            }
        });
        actNetBase.addRequestToQunue(request);
    }
}

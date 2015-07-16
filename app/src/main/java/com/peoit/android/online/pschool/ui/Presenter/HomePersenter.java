package com.peoit.android.online.pschool.ui.Presenter;

import android.graphics.BitmapFactory;

import com.android.volley.VolleyError;
import com.peoit.android.online.pschool.ActBase;
import com.peoit.android.online.pschool.config.NetConstants;
import com.peoit.android.online.pschool.entity.UserInfo;
import com.peoit.android.online.pschool.net.base.CallBack;
import com.peoit.android.online.pschool.ui.Base.BasePresenter;

import java.util.Map;

/**
 * author:libo
 * time:2015/7/10
 * E-mail:boli_android@163.com
 * last: ...
 */
public class HomePersenter extends BasePresenter<UserInfo>{

    private final ActBase mActBase;

    public HomePersenter(ActBase actBase) {
        super(actBase);
        this.mActBase = actBase;
    }

    public void repuest(){
        request(NetConstants.NET_LOGIN, new CallBack() {

            @Override
            public void onSimpleSuccess(Object result) {

            }

            @Override
            public void onSimpleFailure(VolleyError error) {

            }
        });
    }

    @Override
    public Map<String, String> getParams() {
        return null;
    }

    @Override
    public Class getGsonClass() {
        return null;
    }
}

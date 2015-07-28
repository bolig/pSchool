package com.peoit.android.online.pschool.ui.Presenter;

import com.peoit.android.online.pschool.ActBase;
import com.peoit.android.online.pschool.config.NetConstants;
import com.peoit.android.online.pschool.entity.UserInfo;
import com.peoit.android.online.pschool.net.CallBack;
import com.peoit.android.online.pschool.ui.Base.BasePresenter;
import com.peoit.android.online.pschool.utils.MyLogger;

import java.util.HashMap;
import java.util.Map;

/**
 * author:libo
 * time:2015/7/13
 * E-mail:boli_android@163.com
 * last: ...
 */
public abstract class LoginPresenter extends BasePresenter<UserInfo> {

    public LoginPresenter(ActBase actBase) {
        super(actBase);
    }

    @Override
    public Map<String, String> getParams() {
        Map<String, String> params = new HashMap<>();
        getUserNameAndPassword(params);
        return params;
    }

    /**
     * 获取用户名和密码
     *
     * @return
     */
    protected abstract void getUserNameAndPassword(Map<String, String> params);

    @Override
    public Class getGsonClass() {
        return UserInfo.class;
    }

    public void toLogin(){
        request(NetConstants.NET_LOGIN, new CallBack<UserInfo>() {
            @Override
            public void onSimpleSuccess(UserInfo result) {
                //mActBase.onResponseSuccess(result);
                MyLogger.i("UserInfo ---------- " + result.toString());
            }

            @Override
            public void onSimpleFailure(int error, String errorMsg) {
                mActBase.onResponseFailure(error, errorMsg);
            }
        });
    }
}

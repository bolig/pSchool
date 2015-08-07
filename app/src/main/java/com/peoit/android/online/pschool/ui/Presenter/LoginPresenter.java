package com.peoit.android.online.pschool.ui.Presenter;

import android.text.TextUtils;

import com.peoit.android.online.pschool.ActBase;
import com.peoit.android.online.pschool.config.Constants;
import com.peoit.android.online.pschool.config.NetConstants;
import com.peoit.android.online.pschool.entity.UserInfo;
import com.peoit.android.online.pschool.net.CallBack;
import com.peoit.android.online.pschool.ui.Base.BasePresenter;
import com.peoit.android.online.pschool.utils.MD5;

import java.util.HashMap;
import java.util.Map;

/**
 * author:libo
 * time:2015/7/13
 * E-mail:boli_android@163.com
 * last: ...
 */
public abstract class LoginPresenter extends BasePresenter<UserInfo> {

    private HashMap<String, String> params;
    private String username;

    public LoginPresenter(ActBase actBase) {
        super(actBase);
    }

    @Override
    public Map<String, String> getParams() {
        params = new HashMap<>();
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

    public void toLogin() {
        request(NetConstants.NET_LOGIN, new CallBack<UserInfo>() {

            @Override
            public void onSimpleSuccess(UserInfo result) {
                String sign = getSign();
                if (TextUtils.isEmpty(sign)){
                    mActBase.showToast("登录失败...");
                    return;
                }
                mActBase.getShare().put(Constants.LOGIN_USER_SIGN, sign);
                mActBase.getShare().put(Constants.LOGIN_USER_NAME, username);
                mActBase.getShare().saveCurrentUser(result);
                mActBase.finish();
            }

            @Override
            public void onSimpleFailure(int error, String errorMsg) {
                mActBase.onResponseFailure(error, errorMsg);
            }
        });
    }

    private String getSign() {
        if (params != null && !params.isEmpty()){
            String username = params.get("userno");
            String password = params.get("password");

            this.username = username;

            String sign = username + "|" + password + "|gzxxxx";
            sign = MD5.getMD5Code(sign);
            return sign;
        }
        return null;
    }
}

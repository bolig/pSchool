package com.peoit.android.online.pschool.ui.Presenter;

import com.android.volley.VolleyError;
import com.peoit.android.online.pschool.ActBase;
import com.peoit.android.online.pschool.EntityBase;
import com.peoit.android.online.pschool.config.NetConstants;
import com.peoit.android.online.pschool.entity.UserInfo;
import com.peoit.android.online.pschool.net.base.CallBack;
import com.peoit.android.online.pschool.ui.Base.BasePresenter;

import java.util.HashMap;
import java.util.Map;

/**
 * author:libo
 * time:2015/7/13
 * E-mail:boli_android@163.com
 * last: ...
 */
public abstract class LoginPresenter<U extends EntityBase> extends BasePresenter<U> {

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
        toRequestDataWithUrl(NetConstants.NET_LOGIN);
    }
}

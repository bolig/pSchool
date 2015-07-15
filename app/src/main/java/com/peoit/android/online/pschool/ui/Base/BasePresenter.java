package com.peoit.android.online.pschool.ui.Base;

import android.content.Context;

import com.android.volley.RetryPolicy;
import com.google.gson.Gson;
import com.peoit.android.online.pschool.ActBase;
import com.peoit.android.online.pschool.EntityBase;
import com.peoit.android.online.pschool.PresenterNetBase;
import com.peoit.android.online.pschool.net.base.CallBack;
import com.peoit.android.online.pschool.net.base.GsonRequest;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * author:libo
 * time:2015/7/10
 * E-mail:boli_android@163.com
 * last: ...
 */
public abstract class BasePresenter<T> implements PresenterNetBase<T> {

    private Context mContext;
    protected ActBase<T> mActBase;

    public BasePresenter(ActBase<T> actBase) {
        if (actBase == null)
            throw new NullPointerException("actBase is null at BasePresenter");
        this.mActBase = actBase;
        this.mContext = actBase.getContext();
    }

    @Override
    public void request(String url, CallBack<T> callBack) {
        GsonRequest<T> request = new GsonRequest<T>(url, this, getGsonCondition() == 2 ? getGsonTypetkoen() : getGsonClass(), callBack);
        mActBase.addRequestToQunue(request);
    }

    /**
     * 获取Gson解锁条件
     *
     * @return
     */
    private int getGsonCondition() {
        if (getGsonTypetkoen() != null)
            return 2;
        else if (getGsonClass() != null)
            return 1;
        throw new NullPointerException("Gson 解析条件不能为空");
    }

//    @Override
//    public Map<String, String> getParams() {
//        return null;
//    }

    @Override
    public Map<String, String> getHeaders() {
        return null;
    }

    @Override
    public RetryPolicy getRetryPolicy() {
        return null;
    }

    @Override
    public Gson getCustomGson() {
        return null;
    }

    @Override
    public Byte[] getBodyByte() {
        return new Byte[0];
    }

    @Override
    public Type getGsonTypetkoen() {
        return null;
    }

//    @Override
//    public Class<T> getGsonClass() {
//        return null;
//    }
}

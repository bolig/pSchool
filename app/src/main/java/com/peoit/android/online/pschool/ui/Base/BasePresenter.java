package com.peoit.android.online.pschool.ui.Base;

import android.content.Context;

import com.android.volley.RetryPolicy;
import com.google.gson.Gson;
import com.peoit.android.online.pschool.ActBase;
import com.peoit.android.online.pschool.EntityBase;
import com.peoit.android.online.pschool.PresenterNetBase;
import com.peoit.android.online.pschool.config.CommonUtil;
import com.peoit.android.online.pschool.net.CallBack;
import com.peoit.android.online.pschool.net.GsonRequest;
import com.peoit.android.online.pschool.net.RequestOptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author:libo
 * time:2015/7/10
 * E-mail:boli_android@163.com
 * last: ...
 */
public abstract class BasePresenter<T extends EntityBase> implements PresenterNetBase<T> {

    private Context mContext;
    protected ActBase mActBase;

    public BasePresenter(ActBase actBase) {
        if (actBase == null)
            throw new NullPointerException("actBase is null at BasePresenter");
        this.mActBase = actBase;
        this.mContext = actBase.getContext();
    }

    @Override
    public void request(String url, CallBack<T> callBack) {
        GsonRequest<T> request = new GsonRequest<T>(new RequestOptions(url, getGsonClass(), this), callBack);
        mActBase.addRequestToQunue(request);
    }

    @Override
    public void toRequestDataWithUrl(String url) {
        request(url, new CallBack<T>() {
            @Override
            public void onSimpleSuccessList(List<T> result) {

            }

            @Override
            public void onSimpleSuccess(T result) {

            }

            @Override
            public void onSimpleFailure(int errorCode, String errorMsg) {
                mActBase.onResponseFailure(errorCode, errorMsg);
            }

            @Override
            public void onFinish() {
                mActBase.onResponseFinish();
            }
        });
    }

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

    public Map<String, String> getSignParams(){
        String sign = CommonUtil.getUser_sign();
        String name = CommonUtil.getUser_name();
        Map<String, String> params = new HashMap<>();
        params.put("userno", name);
        params.put("sign", sign);
        System.out.println(">>>>>>>>>>传入的数据："+params);
        return params;
    }

}

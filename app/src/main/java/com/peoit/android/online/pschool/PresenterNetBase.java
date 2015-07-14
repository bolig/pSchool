package com.peoit.android.online.pschool;

import com.android.volley.Request;
import com.android.volley.RetryPolicy;
import com.google.gson.Gson;
import com.peoit.android.online.pschool.net.base.CallBack;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * author:libo
 * time:2015/7/13
 * E-mail:boli_android@163.com
 * last: ...
 */
public interface PresenterNetBase<T extends EntityBase> {
    public final static int GET = Request.Method.GET;
    public final static int POST = Request.Method.POST;
    public final static int PUT = Request.Method.PUT;
    public final static int DELETE = Request.Method.DELETE;
    public final static int HEAD = Request.Method.HEAD;

    void request(String url, CallBack<T> callBack);

    void request(int method, String url, CallBack<T> callBack);

    Map<String, String> getParams();

    Map<String, String> getHeaders();

    RetryPolicy getRetryPolicy();

    Gson getCustomGson();

    Byte[] getBodyByte();

    /**
     * 获取Gson解析条件
     *
     *
     * @return
     */
    Type getGsonTypetkoen();

    /**
     * 获取Gson解析条件
     *
     * @return
     */
    Class<T> getGsonClass();
}

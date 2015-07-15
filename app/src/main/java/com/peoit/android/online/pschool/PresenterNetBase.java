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
public interface PresenterNetBase<T> {

    /**
     *
     *
     * @param url
     * @param callBack
     */
    void request(String url, CallBack<T> callBack);

    /**
     * 获取请求参数
     *
     * @return
     */
    Map<String, String> getParams();

    /**
     * 获取Headers
     *
     * @return
     */
    Map<String, String> getHeaders();

    /**
     * 获取超时
     *
     * @return
     */
    RetryPolicy getRetryPolicy();

    /**
     * 获取自定义Gson
     *
     * @return
     */
    Gson getCustomGson();

    /**
     * 获取请求body
     *
     * @return
     */
    Byte[] getBodyByte();

    /**
     * 获取Gson解析条件
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

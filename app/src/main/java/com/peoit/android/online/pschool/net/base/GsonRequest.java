package com.peoit.android.online.pschool.net.base;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.peoit.android.online.pschool.PresenterNetBase;
import com.peoit.android.online.pschool.config.NetConstants;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * author:libo
 * time:2015/7/9
 * E-mail:boli_android@163.com
 * last: ...
 */
public class GsonRequest<T> extends Request<String> {
    private PresenterNetBase mPresenterNetBase;
    private Gson mGson;
    private Class<T> mClazz;
    private CallBack<T> mCallBack;
    private Type mTypeToken;

    public GsonRequest(String url, PresenterNetBase netBase, Class<?> clazz, CallBack<T> callBack) {
        this(url.contains(NetConstants.BRIDGE) ?
                Integer.valueOf(url.split(NetConstants.BRIDGE)[0]) :
                NetConstants.POST, netBase, url.contains(NetConstants.BRIDGE)
                ? url.split(NetConstants.BRIDGE)[1] : url, clazz, callBack);
    }

    public GsonRequest(String url, PresenterNetBase netBase, Type typeToken, CallBack<T> callBack) {
        this(url.contains(NetConstants.BRIDGE) ?
                Integer.valueOf(url.split(NetConstants.BRIDGE)[0]) :
                NetConstants.POST, netBase, url.contains(NetConstants.BRIDGE)
                ? url.split(NetConstants.BRIDGE)[1] : url, typeToken, callBack);
    }

    public GsonRequest(int method, PresenterNetBase netBase, String url, Class<T> clazz, CallBack<T> callBack) {
        super(method, url, callBack);
        this.mClazz = clazz;
        this.mGson = new Gson();
        this.mCallBack = callBack;
        this.mPresenterNetBase = netBase;
    }

    public GsonRequest(int method, PresenterNetBase netBase, String url, Type typeToken, CallBack<T> callBack) {
        super(method, url, callBack);
        this.mTypeToken = typeToken;
        this.mGson = new Gson();
        this.mCallBack = callBack;
        this.mPresenterNetBase = netBase;
    }

    /**
     * 自定义Gson
     *
     * @return
     */
    public synchronized Gson getGson() {
        if (mGson == null) {
            if (mPresenterNetBase.getCustomGson() != null)
                mGson = mPresenterNetBase.getCustomGson();
            else
                mGson = new Gson();
        }
        return mGson;
    }

    @Override
    public final RetryPolicy getRetryPolicy() {
        return mPresenterNetBase.getRetryPolicy() == null ? super.getRetryPolicy() : mPresenterNetBase.getRetryPolicy();
    }

    @Override
    protected final Map<String, String> getParams() throws AuthFailureError {
        return (mPresenterNetBase.getParams() == null || mPresenterNetBase.getParams().isEmpty()) ? super.getParams() : mPresenterNetBase.getParams();
    }

    @Override
    public final Map<String, String> getHeaders() throws AuthFailureError {
        return (mPresenterNetBase.getHeaders() == null || mPresenterNetBase.getHeaders().isEmpty()) ? super.getHeaders() : mPresenterNetBase.getHeaders();
    }

    @Override
    protected Response parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers));

            T parseJson = getGson().fromJson(jsonString, mTypeToken != null ? mTypeToken : mClazz);

            if (mCallBack != null) {
                mCallBack.onFinish();
                mCallBack.onSimpleSuccess(parseJson);
            }

            return Response.success(parseJson,
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(String response) {
        T parseJson = getGson().fromJson(response, mClazz == null ? mTypeToken : mClazz);
        if (mCallBack != null) {
            mCallBack.onFinish();
            mCallBack.onSimpleSuccess(parseJson);
        }
    }
}

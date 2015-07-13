package com.peoit.android.online.pschool.net.base;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * author:libo
 * time:2015/7/9
 * E-mail:boli_android@163.com
 * last: ...
 */
public class GsonRequest<T> extends Request<String>{
    private Gson mGson;
    private Class<?> mClazz;
    private CallBack<T> mCallBack;
    private Map<String, String> mHeaders;
    private Map<String, String> mParams;
    private RetryPolicy mRetryPolicy;
    private byte[] mBodyBytes;
    private Type mTypeToken;

    public GsonRequest(String url, Class<?> clazz, CallBack<T> callBack) {
        this(Method.GET, url, clazz, callBack);
    }

    public GsonRequest(String url, Type typeToken, CallBack<T> callBack) {
        this(Method.GET, url, typeToken, callBack);
    }

    public GsonRequest(int method, String url, Class<?> clazz, CallBack<T> callBack) {
        super(method, url, callBack);
        this.mClazz = clazz;
        this.mGson = new Gson();
        this.mCallBack = callBack;
    }

    public GsonRequest(int method, String url,Type typeToken, CallBack<T> callBack) {
        super(method, url, callBack);
        this.mTypeToken = typeToken;
        this.mGson = new Gson();
        this.mCallBack = callBack;
    }

    /**
     * 添加请求消息头
     *
     * @param headers
     * @return
     */
    public GsonRequest addHeaders(Map<String, String> headers){
        this.mHeaders = headers;
        return this;
    }

    /**
     * 添加请求参数
     *
     * @param requests
     * @return
     */
    public GsonRequest addRequests(Map<String, String> requests){
        this.mParams = requests;
        return this;
    }

    /**
     * 添加超时
     *
     * @param retryPolicy
     * @return
     */
    public GsonRequest addRetryPolicy(RetryPolicy retryPolicy){
        this.mRetryPolicy = retryPolicy;
        return this;
    }

    public GsonRequest addBpdyByte(byte[] bodyBytes){
        this.mBodyBytes = bodyBytes;
        return this;
    }

    /**
     * 自定义Gson
     *
     * @param gson
     * @return
     */
    public GsonRequest addGson(Gson gson){
        if (gson != null)
            mGson = gson;
        return this;
    }

    @Override
    public final RetryPolicy getRetryPolicy() {
        return mRetryPolicy == null ? super.getRetryPolicy() : mRetryPolicy;
    }

    @Override
    protected final Map<String, String> getParams() throws AuthFailureError {
        return (mParams == null || mParams.isEmpty()) ? super.getParams() : mParams;
    }

    @Override
    public final Map<String, String> getHeaders() throws AuthFailureError {
        return (mHeaders == null || mHeaders.isEmpty()) ? super.getHeaders() : mHeaders;
    }

    @Override
    protected Response parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers));

            if (mClazz == null && mTypeToken == null){
                throw  new NullPointerException("mClazz and mTypeToken are null at GsonRequest");
            }

            T parseJson = (T) mGson.fromJson(jsonString, mTypeToken != null ? mTypeToken : mClazz);

            if (mCallBack != null){
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
        T parseJson = (T) mGson.fromJson(response, mClazz == null ? mTypeToken : mClazz);
        if (mCallBack != null){
            mCallBack.onFinish();
            mCallBack.onSimpleSuccess(parseJson);
        }
    }
}

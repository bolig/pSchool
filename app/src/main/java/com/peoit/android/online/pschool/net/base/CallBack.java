package com.peoit.android.online.pschool.net.base;

import com.android.volley.Response;
import com.android.volley.VolleyError;


/**
 * author:libo
 * time:2015/7/9
 * E-mail:boli_android@163.com
 * last: ...
 */
public abstract class CallBack<T> implements Response.ErrorListener{

    public abstract void onFinish();

    public abstract void onSimpleSuccess(T result);

    public abstract void onSimpleFailure(VolleyError error);

    @Override
    public final void onErrorResponse(VolleyError error) {
        onFinish();
        onSimpleFailure(error);
    }
}

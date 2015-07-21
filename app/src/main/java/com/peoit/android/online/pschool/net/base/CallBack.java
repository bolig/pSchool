package com.peoit.android.online.pschool.net.base;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.peoit.android.online.pschool.config.Error;


/**
 * author:libo
 * time:2015/7/9
 * E-mail:boli_android@163.com
 * last: ...
 */
public abstract class CallBack<T> implements Response.ErrorListener, Response.Listener<String>{

    public void onFinish(){

    }

    @Override
    public void onErrorResponse(VolleyError error) {
        onFinish();
        onSimpleFailure(Error.GSON_ERROR1, "");
    }

    @Override
    public void onResponse(String response) {
        onFinish();

    }

    public abstract void onSimpleSuccess(T result);

    public abstract void onSimpleFailure(int error, String errorMsg);
}

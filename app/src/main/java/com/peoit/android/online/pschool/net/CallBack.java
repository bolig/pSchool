package com.peoit.android.online.pschool.net;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.peoit.android.online.pschool.EntityBase;
import com.peoit.android.online.pschool.config.Error;

import java.util.List;


/**
 * author:libo
 * time:2015/7/9
 * E-mail:boli_android@163.com
 * last: ...
 */
public abstract class CallBack<T extends EntityBase> implements Response.ErrorListener, Response.Listener<String>{

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

    @Deprecated
    public void onSimpleSuccess(T result){
        throw new NullPointerException(" @libo onSimpleSuccess is null");
    }

    @Deprecated
    public void onSimpleSuccessList(List<T> result){
        throw new NullPointerException(" @libo onSimpleSuccess is null");
    }

    public abstract void onSimpleFailure(int error, String errorMsg);
}

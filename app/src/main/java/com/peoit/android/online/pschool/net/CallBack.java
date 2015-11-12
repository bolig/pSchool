package com.peoit.android.online.pschool.net;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.peoit.android.online.pschool.EntityBase;
import com.peoit.android.online.pschool.config.Error;
import com.peoit.android.online.pschool.entity.QueryNoallotInfo;
import com.peoit.android.online.pschool.utils.MyLogger;

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
    public final void onErrorResponse(VolleyError error) {
        MyLogger.e("error = " + (error != null ? error.toString() : "null"));
        if (error != null){
            error.printStackTrace();
        }
        onFinish();
        onSimpleFailure(Error.GSON_ERROR1, "");
    }

    @Override
    public final void onResponse(String response) {
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


    public void onSimpleSuccess1(QueryNoallotInfo info){

    }
}

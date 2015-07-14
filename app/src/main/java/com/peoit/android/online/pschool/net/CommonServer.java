package com.peoit.android.online.pschool.net;

import com.peoit.android.online.pschool.ActBase;
import com.peoit.android.online.pschool.EntityBase;
import com.peoit.android.online.pschool.entity.UserInfo;
import com.peoit.android.online.pschool.net.base.CallBack;
import com.peoit.android.online.pschool.net.base.GsonRequest;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * author:libo
 * time:2015/7/9
 * E-mail:boli_android@163.com
 * last: ...
 */
public class CommonServer<U> {
    public GsonRequest getRequest(int method, String url, Class<?> clazz, CallBack<U> callBack){
        return new GsonRequest<U>(method, url, clazz, callBack);
    }

    public GsonRequest getRequest(int method, String url, Type type, CallBack<U> callBack){
        return new GsonRequest<U>(method, url, type, callBack);
    }

    public GsonRequest getRequest(String url, Class<U> clazz, CallBack<U> callBack){
        return new GsonRequest<U>(url, clazz, callBack);
    }

    public GsonRequest getRequest(String url, Type type, CallBack<U> callBack){
        return new GsonRequest<U>(url, type, callBack);
    }

    public Map getMap(){
        return new HashMap<String, String>();
    }
}

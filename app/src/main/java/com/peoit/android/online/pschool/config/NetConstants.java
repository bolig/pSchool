package com.peoit.android.online.pschool.config;

import com.android.volley.Request;

/**
 * author:libo
 * time:2015/7/13
 * E-mail:boli_android@163.com
 * last: ...
 */
public class NetConstants {
    public final static int GET = Request.Method.GET;
    public final static int POST = Request.Method.POST;
    public final static int PUT = Request.Method.PUT;
    public final static int DELETE = Request.Method.DELETE;
    public final static int HEAD = Request.Method.HEAD;

    public final static String BRIDGE = "-NET-BRIDGE-";

    public static final String HOST = "http://...";

    public static final String NET_LOGIN = GET + BRIDGE + HOST + "/login";
}

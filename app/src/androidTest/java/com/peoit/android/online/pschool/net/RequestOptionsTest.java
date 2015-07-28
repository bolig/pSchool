package com.peoit.android.online.pschool.net;

import android.test.InstrumentationTestCase;

import com.android.volley.Request;
import com.peoit.android.online.pschool.config.NetConstants;
import com.peoit.android.online.pschool.entity.UserInfo;

/**
 * author:libo
 * time:2015/7/27
 * E-mail:boli_android@163.com
 * last: ...
 */
public class RequestOptionsTest extends InstrumentationTestCase {

    public void testEntity() throws Exception{
        RequestOptions requestOptions = new RequestOptions(NetConstants.NET_QUERYCOURSE, UserInfo.class, null);
        assertEquals(NetConstants.HOST + "queryCourse.do", requestOptions.getUrl());
        assertEquals(RequestModel.ENTITYLIST, requestOptions.getmModel());
        assertEquals(Request.Method.POST, requestOptions.getmMethod());
    }
}

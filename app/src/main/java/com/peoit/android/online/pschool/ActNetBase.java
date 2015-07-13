package com.peoit.android.online.pschool;

import java.util.Map;

/**
 * author:libo
 * time:2015/7/10
 * E-mail:boli_android@163.com
 * last: ...
 */
public interface ActNetBase<RESPONSE> {
    /**
     * 联网请求数据成功
     *
     * @param response
     */
    void responseSuccess(RESPONSE response);

    /**
     * 请求数据失败
     *
     * @param errorCode
     */
    void responseFailure(int errorCode);
}

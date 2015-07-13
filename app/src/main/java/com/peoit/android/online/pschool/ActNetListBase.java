package com.peoit.android.online.pschool;

import java.util.List;

/**
 * author:libo
 * time:2015/7/13
 * E-mail:boli_android@163.com
 * last: ...
 */
public interface ActNetListBase<RESPONSE> {
    /**
     * 请求列表数据成功
     *
     * @param responses
     */
    void responseSuccess(List<RESPONSE> responses);

    /**
     * 请求数据失败
     *
     * @param errorCode
     */
    void responseFailure(int errorCode);
}

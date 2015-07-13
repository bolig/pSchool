package com.peoit.android.online.pschool;

import android.os.Bundle;

import java.util.Map;

/**
 * author:libo
 * time:2015/7/9
 * E-mail:boli_android@163.com
 * last: ...
 */
public interface ActBase {
    /**
     * 初始化数据
     *
     */
    void initData();

    /**
     * 初始化界面控件
     *
     */
    void initView();

    /**
     * 初始化事件监听
     *
     */
    void initListener();

    /**
     * 请求数据失败
     *
     * @param errorCode
     */
    void responseFailure(int errorCode);
}

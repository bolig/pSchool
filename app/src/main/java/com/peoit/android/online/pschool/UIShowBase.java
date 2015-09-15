package com.peoit.android.online.pschool;

/**
 * author:libo
 * time:2015/8/24
 * E-mail:boli_android@163.com
 * last: ...
 */
public interface UIShowBase {
    /**
     * 显示数据加载
     *
     */
    void doShowloading();

    /**
     * 显示数据加载失败
     *
     */
    void doShowNodata(int drawableID);

    /**
     * 显示加载成功数据
     *
     */
    void doShowData();
}

package com.peoit.android.online.pschool;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import com.android.volley.Request;
import com.peoit.android.online.pschool.ui.Presenter.UIShowPresenter;

import java.util.List;
import java.util.Map;

/**
 * author:libo
 * time:2015/7/9
 * E-mail:boli_android@163.com
 * last: ...
 */
public interface ActBase<T extends EntityBase> {
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
     * 判断是否登录
     *
     * @return
     */
    boolean isLogin();

    /**
     * 判断是否登录，如果没登录去登陆
     *
     * @return
     */
    boolean isLoginAndToLogin();

    /**
     * 获取Context
     *
     * @return
     */
    Context getContext();

    /**
     * 获取ApplicationContext();
     *
     * @return
     */
    Context getApplicationContext();

    /**
     * UI界面显示改变
     *
     * @param loadingVisible
     * @param notDataVisible
     */
    void ChangeUIShow(int loadingVisible, int notDataVisible);

    /**
     * 显示加载失败界面
     *
     */
    //TODO:
    void showNoData();

    /**
     * 显示加载界面
     *
     */
    // TODO:
    void showLoading();

    /**
     * 显示加载Dialog
     *
     * @return
     */
    Dialog showLoadingDialog();

    void showToast(CharSequence msg);

    /**
     * 请求列表数据成功
     *
     * @param responses
     */
    void responseSuccess(List<T> responses);

    /**
     * 请求列表数据成功
     *
     * @param responses
     */
    void responseSuccess(T responses);

    /**
     * 请求数据失败
     *
     * @param errorCode
     */
    void responseFailure(int errorCode);

    /**
     * 添加请求到请求队列
     *
     * @param request
     */
    void addRequestToQunue(Request request);

    /**
     * 添加UI显示操作类
     *
     * @return
     */
    UIShowPresenter getUIshowPresenter();
}

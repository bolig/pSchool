package com.peoit.android.online.pschool;

import android.app.Dialog;
import android.content.Context;

import com.android.volley.Request;
import com.peoit.android.online.pschool.entity.UserInfo;
import com.peoit.android.online.pschool.ui.presenter.UIShowPresenter;
import com.peoit.android.online.pschool.utils.ShareUserHelper;

import java.util.List;

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
     * 退出登录...
     *
     */
    void logout();

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
    void changeUIShow(int loadingVisible, int notDataVisible);

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

    /**
     * 显示Toast
     *
     * @param msg
     */
    void showToast(CharSequence msg);

    /**
     * 请求列表数据成功
     *
     * @param responses
     */
    <T extends EntityBase>void onResponseSuccess(List<T> responses);

    /**
     * 请求列表数据成功
     *
     * @param response
     */
    <T extends EntityBase> void onResponseSuccess(T response);

    /**
     * 请求数据失败
     *
     * @param errorCode
     */
    void onResponseFailure(int errorCode, String errorMsg);

    /**
     * 请求网络数据完成
     *
     */
    void onResponseFinish();

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

    /**
     * 实现activity finish
     *
     */
    void finish();

    /**
     * 获取偏好设置...
     *
     * @return
     */
    ShareUserHelper getShare();

    /**
     * 获取当前用户信息
     *
     * @return
     */
    UserInfo getCurrentUser();


}

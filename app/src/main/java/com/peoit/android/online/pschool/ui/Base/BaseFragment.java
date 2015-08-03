package com.peoit.android.online.pschool.ui.Base;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.peoit.android.online.pschool.ActBase;
import com.peoit.android.online.pschool.EntityBase;
import com.peoit.android.online.pschool.config.CommonUtil;
import com.peoit.android.online.pschool.entity.UserInfo;
import com.peoit.android.online.pschool.ui.Presenter.UIShowPresenter;
import com.peoit.android.online.pschool.utils.ShareUserHelper;

import java.util.List;

/**
 * fragment基类...
 *
 * author:libo
 * time:2015/7/30
 * E-mail:boli_android@163.com
 * last: ...
 */
public abstract class BaseFragment extends Fragment implements ActBase{

    private ActBase mActBase;
    private OnFragmentCallBack mCallBack;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActBase = (ActBase) activity;
        if (activity instanceof OnFragmentCallBack)
            mCallBack = (OnFragmentCallBack) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallBack = null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initData();
        return getCurrentLayout(inflater, container, savedInstanceState);
    }

    protected abstract View getCurrentLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        initView();
        initListener();
    }

    @Override
    public boolean isLogin() {
        return CommonUtil.isIsLogin();
    }

    @Override
    public boolean isLoginAndToLogin() {
        return mActBase.isLoginAndToLogin();
    }

    @Override
    public void logout() {
        mActBase.logout();
    }

    @Override
    public Context getContext() {
        return mActBase.getContext();
    }

    @Override
    public Context getApplicationContext() {
        return mActBase.getApplicationContext();
    }

    @Override
    public void changeUIShow(int loadingVisible, int notDataVisible) {
        mActBase.changeUIShow(loadingVisible, notDataVisible);
    }

    @Override
    public void showNoData() {
        mActBase.showNoData();
    }

    @Override
    public void showLoading() {
        mActBase.showNoData();
    }

    @Override
    public Dialog showLoadingDialog() {
        return mActBase.showLoadingDialog();
    }

    @Override
    public void showToast(CharSequence msg) {
        mActBase.showToast(msg);
    }

    @Override
    public <T extends EntityBase> void onResponseSuccess(List<T> responses) {
        mActBase.onResponseSuccess(responses);
    }

    @Override
    public <T extends EntityBase> void onResponseSuccess(T response) {
        mActBase.onResponseSuccess(response);
    }

    @Override
    public void onResponseFailure(int errorCode, String errorMsg) {
        mActBase.onResponseFailure(errorCode, errorMsg);
    }

    @Override
    public void onResponseFinish() {

    }

    @Override
    public void addRequestToQunue(Request request) {
        mActBase.addRequestToQunue(request);
    }

    @Override
    public UIShowPresenter getUIshowPresenter() {
        return mActBase.getUIshowPresenter();
    }

    @Override
    public void finish() {

    }

    @Override
    public ShareUserHelper getShare() {
        return mActBase.getShare();
    }

    @Override
    public UserInfo getCurrentUser() {
        return CommonUtil.getCurrentUser();
    }

    protected View findViewById(int rid){
        return getView().findViewById(rid);
    }
}

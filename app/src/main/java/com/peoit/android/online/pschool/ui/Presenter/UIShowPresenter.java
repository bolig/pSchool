package com.peoit.android.online.pschool.ui.Presenter;

import com.peoit.android.online.pschool.ActBase;

/**
 * author:libo
 * time:2015/7/9
 * E-mail:boli_android@163.com
 * last: ...
 */
public class UIShowPresenter {
    private final ActBase showUI;

    public UIShowPresenter(ActBase showUI) {
        this.showUI = showUI;
    }

    public void showUILoading(){
        showUI.showLoading();
    }

    public void showUINotData(){
        showUI.showNoData();
    }
}

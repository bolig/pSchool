package com.peoit.android.online.pschool.ui.Presenter;

import com.peoit.android.online.pschool.UIShowBase;

/**
 * author:libo
 * time:2015/7/9
 * E-mail:boli_android@163.com
 * last: ...
 */
public class UIShowPresenter {
    private final UIShowBase showUI;

    public UIShowPresenter(UIShowBase showUI) {
        this.showUI = showUI;
    }

    public void showUILoading(){
        showUI.showLoading();
    }

    public void showUINotData(){
        showUI.showNoData();
    }
}

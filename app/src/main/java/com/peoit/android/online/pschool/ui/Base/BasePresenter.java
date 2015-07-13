package com.peoit.android.online.pschool.ui.Base;

import com.peoit.android.online.pschool.ActBase;
/**
 * author:libo
 * time:2015/7/10
 * E-mail:boli_android@163.com
 * last: ...
 */
public abstract class BasePresenter {

    public BasePresenter(ActBase actBase) {
        if (actBase != null){
            actBase.initData();
            actBase.initView();
            actBase.initListener();
        }
    }
}

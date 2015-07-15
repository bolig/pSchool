package com.peoit.android.online.pschool.ui.Presenter;

import android.graphics.BitmapFactory;

import com.peoit.android.online.pschool.ActBase;
import com.peoit.android.online.pschool.ui.Base.BasePresenter;

import java.util.Map;

/**
 * author:libo
 * time:2015/7/10
 * E-mail:boli_android@163.com
 * last: ...
 */
public class HomePersenter extends BasePresenter{

    private final ActBase mActBase;

    public HomePersenter(ActBase actBase) {
        super(actBase);
        this.mActBase = actBase;
    }

    @Override
    public Map<String, String> getParams() {
        return null;
    }

    @Override
    public Class getGsonClass() {
        return null;
    }
}

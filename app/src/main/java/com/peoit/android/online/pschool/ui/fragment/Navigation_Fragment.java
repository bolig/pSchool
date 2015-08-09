package com.peoit.android.online.pschool.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.ui.Base.BaseFragment;
import com.peoit.android.online.pschool.ui.Presenter.HomePersenter;

/**
 * author:libo
 * time:2015/8/9
 * E-mail:boli_android@163.com
 * last: ...
 */
public class Navigation_Fragment extends BaseFragment{

    private HomePersenter mPersenter;
    private ListView dataList;

    @Override
    protected View getCurrentLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.nav_header, null);
    }

    @Override
    public void initData() {
        mPersenter = new HomePersenter(this);
    }

    @Override
    public void initView() {
        dataList = (ListView) findViewById(R.id.data_list);
        mPersenter.setNavigationDataList(dataList);
    }

    @Override
    public void initListener() {

    }
}

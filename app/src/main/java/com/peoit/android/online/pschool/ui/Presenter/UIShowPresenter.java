package com.peoit.android.online.pschool.ui.Presenter;

import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;

import com.peoit.android.online.pschool.ActBase;
import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.UIShowBase;

/**
 * author:libo
 * time:2015/8/24
 * E-mail:boli_android@163.com
 * last: ...
 */
public class UIShowPresenter implements UIShowBase{

    private final ActBase mActBase;
    private final View mView_show;

    private ViewStub stub_loading;
    private ViewStub stub_noData;

    private View view_noData;
    private View view_loading;
    private ImageView iv_error;

    public UIShowPresenter(ActBase actBase, View view_show) {
        this.mActBase = actBase;
        this.mView_show = view_show;

        initView();
    }

    private void initView() {
        stub_loading = (ViewStub) mView_show.findViewById(R.id.layout_loading_stub);
        stub_noData = (ViewStub) mView_show.findViewById(R.id.layout_nodata_stub);
    }


    @Override
    public void doShowloading() {
        if (view_loading == null) {
            view_loading = stub_loading.inflate();
        } else {
            view_loading.setVisibility(View.VISIBLE);
        }
        if (view_noData != null){
            view_noData.setVisibility(View.GONE);
        }
    }

    @Override
    public void doShowNodata(int drawId) {
        if (view_noData == null) {
            view_noData = stub_noData.inflate();
            iv_error = (ImageView) view_noData.findViewById(R.id.iv_error);
        } else {
            view_noData.setVisibility(View.VISIBLE);
        }
        if (view_loading != null){
            view_loading.setVisibility(View.GONE);
        }
        if (iv_error != null){
            iv_error.setImageResource(drawId);
        }
    }

    @Override
    public void doShowData() {
        if (view_loading != null) {
            view_loading.setVisibility(View.GONE);
        }
        if (view_noData != null){
            view_noData.setVisibility(View.GONE);
        }
    }
}

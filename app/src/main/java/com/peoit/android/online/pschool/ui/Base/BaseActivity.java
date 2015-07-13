package com.peoit.android.online.pschool.ui.Base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewStub;
import android.widget.FrameLayout;

import com.peoit.android.online.pschool.ActBase;
import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.UIShowBase;
import com.peoit.android.online.pschool.ui.Presenter.UIShowPresenter;
import com.peoit.android.online.pschool.ui.view.PsActionBar;
import com.peoit.android.online.pschool.utils.ShareUserHelper;

import org.simple.eventbus.EventBus;

/**
 * author:libo
 * time:2015/7/9
 * E-mail:boli_android@163.com
 * last: ...
 */
public abstract class BaseActivity extends FragmentActivity implements
        UIShowBase, ActBase {
    private UIShowPresenter UIshowPresenter;
    private FrameLayout layout_body;

    private View layout_loading;
    private View layout_nodata;

    private ViewStub viewStub_loading;
    private ViewStub viewStub_nodata;

    protected View layout_current;
    protected Activity mContext;
    protected ShareUserHelper share;
    private PsActionBar actionBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        share = ShareUserHelper.getInstance();
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(R.layout.layout_base);

        initContentView(layoutResID);

        init();
    }

    private void init(){
        mContext = this;

        EventBus.getDefault().register(this);

        UIshowPresenter = new UIShowPresenter(this);

        initData();

        initView();

        initListener();
    }

    private void initContentView(int layoutResID) {
//      layout_loading = super.findViewById(R.id.layout_loading);
//      layout_nodata = super.findViewById(R.id.layout_nodata);

        actionBar = (PsActionBar) super.findViewById(R.id.actionbar);

        viewStub_loading = (ViewStub) super.findViewById(R.id.layout_loading_stub);
        viewStub_nodata = (ViewStub) super.findViewById(R.id.layout_nodata_stub);
        layout_body = (FrameLayout) super.findViewById(R.id.layout_body);

        layout_current = getLayoutInflater().inflate(layoutResID, null);

        layout_body.addView(layout_current);
    }

    protected PsActionBar getPsActionBar(){
        return actionBar;
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override
    public View findViewById(int id) {
        return layout_current == null ? super.findViewById(id) : layout_current.findViewById(id);
    }

    protected UIShowPresenter getUIshowPresenter(){
        if (UIshowPresenter == null)
            throw new NullPointerException("current Executed");
        return UIshowPresenter;
    }

    @Override
    public final void ChangeUIShow(int loadingVisible, int notDataVisible) {
        if (layout_loading != null)
            layout_loading.setVisibility(loadingVisible);
        if (layout_nodata != null)
            layout_nodata.setVisibility(notDataVisible);
    }

    @Override
    public final void showNoData() {
        if (layout_nodata == null)
            layout_nodata = viewStub_nodata.inflate();
    }

    @Override
    public final void showLoading() {
        if (layout_loading == null)
            layout_loading = viewStub_loading.inflate();
    }

    @Override
    public void responseFailure(int errorCode) {

    }
}

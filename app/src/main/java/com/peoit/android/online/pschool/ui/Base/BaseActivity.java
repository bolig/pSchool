package com.peoit.android.online.pschool.ui.Base;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.andexert.library.RippleView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.peoit.android.online.pschool.ActBase;
import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.config.CommonUtil;
import com.peoit.android.online.pschool.ui.Presenter.UIShowPresenter;
import com.peoit.android.online.pschool.ui.activity.LoginActivity;
import com.peoit.android.online.pschool.ui.view.PsActionBar;
import com.peoit.android.online.pschool.utils.MyLogger;
import com.peoit.android.online.pschool.utils.ShareUserHelper;

import org.simple.eventbus.EventBus;

import java.util.List;

/**
 * activity抽象父类
 * <p/>
 * author:libo
 * time:2015/7/9
 * E-mail:boli_android@163.com
 * last: ...
 */
public abstract class BaseActivity<T> extends AppCompatActivity implements ActBase<T> {
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

    protected boolean isMainUI = true;
    private RequestQueue mQuene;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        share = ShareUserHelper.getInstance();
    }

    @Override
    public void setContentView(int layoutResID) {
        if (isMainUI) {
            super.setContentView(R.layout.layout_base);
            initContentView(layoutResID);
        } else {
            super.setContentView(layoutResID);
        }
        init();
    }

    private void init() {
        MyLogger.e("当前界面 >>>>>>> " + this.getClass().getSimpleName());

        mContext = this;

        mQuene = Volley.newRequestQueue(this);

        EventBus.getDefault().register(this);

        initData();

        initView();

        initListener();
    }

    @Override
    public final boolean isLogin() {
        return CommonUtil.isIsLogin();
    }

    @Override
    public final boolean isLoginAndToLogin() {
        if (!CommonUtil.isIsLogin())
            LoginActivity.startThisActivity(this);
        return CommonUtil.isIsLogin();
    }

    @Override
    public final void logout() {
        share.clear();
        System.exit(0);
    }

    @Override
    public final Context getContext() {
        return mContext;
    }

    protected  void initContentView(int layoutResID) {
//      layout_loading = super.findViewById(R.id.layout_loading);
//      layout_nodata = super.findViewById(R.id.layout_nodata);
        UIshowPresenter = new UIShowPresenter(this);

        actionBar = (PsActionBar) super.findViewById(R.id.actionbar);
        actionBar.addLeftBtn(new RippleView.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleView rippleView) {
                finish();
            }
        });

        viewStub_loading = (ViewStub) super.findViewById(R.id.layout_loading_stub);
        viewStub_nodata = (ViewStub) super.findViewById(R.id.layout_nodata_stub);
        layout_body = (FrameLayout) super.findViewById(R.id.layout_body);

        layout_current = getLayoutInflater().inflate(layoutResID, null);

        layout_body.addView(layout_current);
    }

    protected PsActionBar getPsActionBar() {
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

    @Override
    public final UIShowPresenter getUIshowPresenter() {
        if (UIshowPresenter == null)
            throw new NullPointerException("current mothod is not init");
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
    public Dialog showLoadingDialog() {
        return null;
    }

    @Override
    public void showToast(CharSequence msg) {
        CommonUtil.showToast(msg);
    }

    @Override
    @Deprecated
    public void onResponseSuccess(List<T> responses) {
        throw new NullPointerException("current mothod is not override");
    }

    @Override
    @Deprecated
    public void onResponseSuccess(T response) {
        throw new NullPointerException("current mothod is not override");
    }

    @Override
    public void onResponseFailure(int errorCode, String errorMsg) {
        MyLogger.e("NET ----- " + "errorCode = " + errorCode + "  error = " + errorMsg);
    }

    @Override
    public void onResponseFinish() {
        MyLogger.e("NET ----- " + "onResponseFinish");
    }

    @Override
    public void addRequestToQunue(Request request) {
        if (request != null && mQuene != null) {
            mQuene.add(request);
        } else {
            throw new NullPointerException("current mothod is not init");
        }
    }

    protected void myToast(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }
}

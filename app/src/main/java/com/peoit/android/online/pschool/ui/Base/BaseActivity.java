package com.peoit.android.online.pschool.ui.Base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.peoit.android.online.pschool.ActBase;
import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.config.CommonUtil;
import com.peoit.android.online.pschool.entity.UserInfo;
import com.peoit.android.online.pschool.ui.Presenter.HXHelperPresenter;
import com.peoit.android.online.pschool.ui.Presenter.UIShowPresenter;
import com.peoit.android.online.pschool.ui.activity.GroupActivity;
import com.peoit.android.online.pschool.ui.activity.LoginActivity;
import com.peoit.android.online.pschool.ui.view.PsActionBar;
import com.peoit.android.online.pschool.utils.MyLogger;
import com.peoit.android.online.pschool.utils.ShareUserHelper;

import cn.jpush.android.api.JPushInterface;

/**
 * activity抽象父类
 * <p/>
 * author:libo
 * time:2015/7/9
 * E-mail:boli_android@163.com
 * last: ...
 */
public abstract class BaseActivity extends AppCompatActivity implements ActBase {

    private FrameLayout layout_body;

    private FrameLayout layout_show;

    protected View layout_current;

    protected Activity mContext;

    protected ShareUserHelper share;

    private PsActionBar actionBar;

    protected boolean isMainUI = true;

    private RequestQueue mQuene;

    private ProgressDialog loadingDialog;

    private UIShowPresenter mUIShowPresneter;

    protected static final String TOGROUP_ACTION = "com.peoit.android.toGroup";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        share = ShareUserHelper.getInstance();
        Log.i("BaseActivity", getClass().getSimpleName());
    }

    @Override
    public void setContentView(int layoutResID) {
        if (isMainUI) {
            super.setContentView(R.layout.layout_base);
            initContentView(layoutResID);
        } else {
            super.setContentView(layoutResID);
        }
        mContext = this;
        init();
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            GroupActivity.startThisActivity(mContext);
        }
    };

    private void init() {
        MyLogger.e("当前界面 >>>>>>> " + this.getClass().getSimpleName());

        mQuene = Volley.newRequestQueue(this);
        initData();
        initView();
        initListener();

        IntentFilter filter = new IntentFilter(TOGROUP_ACTION);
        registerReceiver(receiver, filter);
    }

    @Override
    public final boolean isLogin() {
        return CommonUtil.isIsLogin();
    }

    @Override
    public final boolean isLoginAndToLogin() {
        if (!isLogin()){
            LoginActivity.startThisActivity(mContext);
            return false;
        }
        return true;
    }

    public final boolean isLoginAndToLogin(boolean isFinish) {
        if (!isLogin()){
            LoginActivity.startThisActivity(mContext);
            if (isFinish)
                mContext.finish();
            return false;
        }
        return true;
    }

    @Override
    public final void logout() {
        CommonUtil.logout();
        isLoginAndToLogin();
        JPushInterface.stopPush(this);
        HXHelperPresenter.groupid = null;
    }

    // 触摸其他区域，输入法界面消失
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }

    public boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = {0, 0};
            // 获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击的是输入框区域，保留点击EditText的事件
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    @Override
    public final Context getContext() {
        return mContext;
    }

    protected  void initContentView(int layoutResID) {
        actionBar = (PsActionBar) super.findViewById(R.id.actionbar);
        actionBar.addLeftBtn(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        layout_body = (FrameLayout) super.findViewById(R.id.layout_body);

        layout_current = getLayoutInflater().inflate(layoutResID, null);

        layout_show = (FrameLayout) super.findViewById(R.id.layout_show);

        mUIShowPresneter = new UIShowPresenter(this, layout_show);

        layout_body.addView(layout_current);
    }

    protected PsActionBar getPsActionBar() {
        return actionBar;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    @Override
    public View findViewById(int id) {
        return layout_current == null ? super.findViewById(id) : layout_current.findViewById(id);
    }

    @Override
    public void showLoadingDialog(String msg) {
        if (loadingDialog == null) {
            loadingDialog = new ProgressDialog(mContext);
            loadingDialog.setCanceledOnTouchOutside(false);
        }
        loadingDialog.setMessage(msg);
        loadingDialog.show();
    }

    @Override
    public void hideLoadingDialog() {
        if (loadingDialog != null){
            loadingDialog.dismiss();
        }
    }

    @Override
    public void showToast(CharSequence msg) {
        CommonUtil.showToast(mContext, msg);
    }

    @Override
    public void onResponseFailure(int errorCode, String errorMsg) {
        MyLogger.e("NET ----- " + "errorCode = " + errorCode + "  error = " + errorMsg);
        if (!TextUtils.isEmpty(errorMsg)){
            showToast(errorMsg);
        }
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
            throw new NullPointerException(" @libo current mothod is not init");
        }
    }

    @Override
    public ShareUserHelper getShare() {
        return share;
    }

    @Override
    public UserInfo getCurrentUser() {
        return CommonUtil.getCurrentUser();
    }

    @Override
    public Activity getActivity() {
        return mContext;
    }

    @Override
    public UIShowPresenter getUIShowPresenter() {
        return mUIShowPresneter;
    }
}

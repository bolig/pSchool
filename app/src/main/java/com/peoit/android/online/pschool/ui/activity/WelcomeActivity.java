package com.peoit.android.online.pschool.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.config.CommonUtil;
import com.peoit.android.online.pschool.config.Constants;
import com.peoit.android.online.pschool.entity.UserInfo;
import com.peoit.android.online.pschool.ui.Base.BaseActivity;
import com.peoit.android.online.pschool.utils.JPushUtil;
import com.peoit.android.online.pschool.utils.ShareUserHelper;

import java.util.HashSet;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;

/**
 * 引导界面
 *
 * author:libo
 * time:2015/7/9
 * E-mail:boli_android@163.com
 * last: ...
 */
public class WelcomeActivity extends BaseActivity {

    private WindowManager.LayoutParams params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isMainUI = false;
        params = getWindow().getAttributes();
        initWindow();
        setContentView(R.layout.act_wel);
    }

    private void initWindow() {
        int flags = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        params.flags = flags;
        getWindow().setAttributes(params);
    }

    @Override
    public void initData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isLoginAndToLogin())
                    HomeActivity.startThisActivity(mContext);
                else {
                    if (!ShareUserHelper.getInstance().getBoolean(Constants.JPUSH_SET_ALIAS)) {
                        String userName = ShareUserHelper.getInstance().getString(Constants.LOGIN_USER_NAME);
                        JPushUtil.setAlias(userName);
                    }
                    if (ShareUserHelper.getInstance().getBoolean(Constants.JPUSH_SET_TAGS)) {
                        Set<String> tags = new HashSet<String>();
                        UserInfo info = CommonUtil.getCurrentUser();
                        tags.add(info.getIdentityType());
                        tags.add(info.getClassid());
                        tags.add(info.getSchoolid());
                        JPushUtil.setTags(tags);
                    }
                }
                finish();
            }
        }, 2000);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initListener() {

    }

    @Override
    protected void onResume() {
       JPushInterface.onResume(this);
        super.onResume();
    }

    @Override
    protected void onPause() {
        JPushInterface.onPause(this);
        super.onPause();
    }
}

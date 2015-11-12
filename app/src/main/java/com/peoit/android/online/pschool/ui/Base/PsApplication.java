package com.peoit.android.online.pschool.ui.Base;

import com.easemob.chatuidemo.DemoApplication;
import com.easemob.chatuidemo.utils.CommonUtils;
import com.peoit.android.online.pschool.config.CommonUtil;
import com.peoit.android.online.pschool.ui.activity.HomeActivity;
import com.pgyersdk.crash.PgyCrashManager;

import cn.jpush.android.api.JPushInterface;

/**
 * 自定义Application
 * author:libo
 * time:2015/7/9
 * E-mail:boli_android@163.com
 * last: ...
 */
public class PsApplication extends DemoApplication {
    private static PsApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        CommonUtil.initContext(mInstance);
        JPushInterface.setDebugMode(true);    // 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);            // 初始化 JPush
        PgyCrashManager.register(this, "ed2630a49feb59a5ac63b68e0bdd9bfc");
    }


    public static PsApplication getInstance() {
        return mInstance;
    }

    @Override
    public void notifyMainActivity() {
        //Log.i("notifyMainActivity", CommonUtils.getTopActivity(HomeActivity.instance));
        if (CommonUtils.getTopActivity(HomeActivity.instance).equals(HomeActivity.class.getName())) {
            HomeActivity.instance.onResume();
        }
    }

}

package com.peoit.android.online.pschool.ui.Presenter;

import android.app.Activity;
import android.text.TextUtils;

import com.peoit.android.online.pschool.ActBase;
import com.peoit.android.online.pschool.config.Constants;
import com.peoit.android.online.pschool.config.NetConstants;
import com.peoit.android.online.pschool.entity.UserInfo;
import com.peoit.android.online.pschool.net.CallBack;
import com.peoit.android.online.pschool.ui.Base.BasePresenter;
import com.peoit.android.online.pschool.ui.activity.HomeActivity;
import com.peoit.android.online.pschool.utils.JPushUtil;
import com.peoit.android.online.pschool.utils.MD5;
import com.peoit.android.online.pschool.utils.MyLogger;
import com.peoit.android.online.pschool.utils.ShareUserHelper;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * author:libo
 * time:2015/7/13
 * E-mail:boli_android@163.com
 * last: ...
 */
public abstract class LoginPresenter extends BasePresenter<UserInfo> {

    private HashMap<String, String> params;
    private String username;

    public LoginPresenter(ActBase actBase) {
        super(actBase);
    }

    @Override
    public Map<String, String> getParams() {
        params = new HashMap<>();
        getUserNameAndPassword(params);
        return params;
    }

    /**
     * 获取用户名和密码
     *
     * @return
     */
    protected abstract void getUserNameAndPassword(Map<String, String> params);

    @Override
    public Class getGsonClass() {
        return UserInfo.class;
    }

    public void toLogin() {

        mActBase.showLoadingDialog("正在登录...");

        request(NetConstants.NET_LOGIN, new CallBack<UserInfo>() {

            @Override
            public void onSimpleSuccess(UserInfo result) {
                String sign = getSign();
                if (TextUtils.isEmpty(sign)) {
                    mActBase.showToast("登录失败");
                } else {
                    mActBase.getShare().put(Constants.LOGIN_USER_SIGN, sign);
                    mActBase.getShare().put(Constants.LOGIN_USER_NAME, username);
                    mActBase.getShare().saveCurrentUser(result);
                    MyLogger.i(result.toString());
                    mActBase.showToast("登录成功");
                    loginTagAndAlias(result);

                    HomeActivity.startThisActivity((Activity) mActBase.getContext());
                    mActBase.finish();
                }

            }

            @Override
            public void onSimpleFailure(int error, String errorMsg) {
                mActBase.onResponseFailure(error, errorMsg);
            }

            @Override
            public void onFinish() {
                mActBase.hideLoadingDialog();
            }
        });
    }

    protected void loginTagAndAlias(UserInfo result) {
//        String tag = result.getSchoolid() + "," + result.getClassid() + "," + result.getIdentityType();
        Set<String> tags = new HashSet<>();

        String id = result.getIdentityType();

        if ("2".equals(id)){
            tags.add(result.getStuclass());
            tags.add(result.getStuschoolcode());
            tags.add(result.getIdentityType());

            tags.add(result.getStuschoolcode() + "_" + result.getIdentityType());
            tags.add(result.getStuclass() + "_" + result.getIdentityType());
        } else {
            tags.add(result.getSchoolid());
            tags.add(result.getClassid());
            tags.add(result.getIdentityType());

            tags.add(result.getSchoolid() + "_" + result.getIdentityType());
            tags.add(result.getClassid() + "_" + result.getIdentityType());
        }

        String alias = username;

        ShareUserHelper.getInstance().put(Constants.JPUSH_SET_TAGS, false);
        ShareUserHelper.getInstance().put(Constants.JPUSH_SET_ALIAS, false);

        JPushUtil.setTags(tags);
        JPushUtil.setAlias(alias);
    }

    private String getSign() {
        if (params != null && !params.isEmpty()) {
            String username = params.get("userno");
            String password = params.get("password");

            this.username = username;

            String sign = username + "|" + password + "|gzxxxx";
            sign = MD5.getMD5Code(sign);
            return sign;
        }
        return null;
    }
}

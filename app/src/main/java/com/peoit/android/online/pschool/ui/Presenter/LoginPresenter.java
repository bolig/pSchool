package com.peoit.android.online.pschool.ui.Presenter;

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
    private HXHelperPresenter mHXHelperPresneter;

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
            public void onSimpleSuccess(final UserInfo result) {
                final String sign = getSign();
                if (TextUtils.isEmpty(sign)) {
                    mActBase.showToast("登录失败");
                } else {
                    loginTagAndAlias(result);

                    mActBase.getShare().put(Constants.LOGIN_USER_SIGN, sign);
                    mActBase.getShare().put(Constants.LOGIN_USER_NAME, username);
                    mActBase.getShare().put(Constants.LOGIN_USER_PASS, params.get("password"));

                    mHXHelperPresneter = new HXHelperPresenter(mActBase);
                    mHXHelperPresneter.login(username);
                    mHXHelperPresneter.setOnGroupIdListener(new HXHelperPresenter.OnGroupIdListener() {
                        @Override
                        public void onGroupId(String GroupId, boolean isToChat) {
                            mActBase.getShare().put(Constants.LOGIN_USER_SIGN, sign);
                            mActBase.getShare().put(Constants.LOGIN_USER_NAME, username);
                            mActBase.getShare().put(Constants.LOGIN_USER_NIKE, getUserNike(result));
                            mActBase.getShare().put(Constants.LOGIN_USER_PASS, params.get("password"));
                            mActBase.getShare().saveCurrentUser(result);

                            mActBase.hideLoadingDialog();
                            mActBase.showToast("登录成功");

                            HomeActivity.startThisActivity(mActBase.getActivity());
                            mActBase.getActivity().finish();
                        }
                    });
                }
            }

            @Override
            public void onSimpleFailure(int error, String errorMsg) {
                mActBase.hideLoadingDialog();
                mActBase.showToast("登录失败");
                mActBase.getShare().put(Constants.LOGIN_ISZHUANJIA, false);
            }

            @Override
            public void onFinish() {

            }
        });
    }

    private String getUserNike(UserInfo result) {
        String type = result.getIdentityType();
        if ("1".equals(type)) {
            return result.getNickname();
        } else if ("2".equals(type)) {
            return result.getStuname() + "的家长";
        } else {
            return result.getNickname();
        }
    }

    protected void loginTagAndAlias(UserInfo result) {
        Set<String> tags = new HashSet<>();

        String id = result.getIdentityType();

        if ("2".equals(id)) {
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

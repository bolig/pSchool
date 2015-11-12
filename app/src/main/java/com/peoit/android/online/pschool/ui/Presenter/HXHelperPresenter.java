package com.peoit.android.online.pschool.ui.Presenter;

import android.text.TextUtils;

import com.easemob.EMCallBack;
import com.easemob.EMEventListener;
import com.easemob.EMNotifierEvent;
import com.easemob.chat.EMChatManager;
import com.easemob.chat.EMGroup;
import com.easemob.chat.EMGroupManager;
import com.easemob.chatuidemo.Constant;
import com.easemob.chatuidemo.db.UserDao;
import com.easemob.chatuidemo.domain.User;
import com.easemob.chatuidemo.utils.CommonUtils;
import com.easemob.exceptions.EaseMobException;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.peoit.android.online.pschool.ActBase;
import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.config.CommonUtil;
import com.peoit.android.online.pschool.config.Constants;
import com.peoit.android.online.pschool.config.NetConstants;
import com.peoit.android.online.pschool.entity.QueryNoallotInfo;
import com.peoit.android.online.pschool.ui.Base.BasePresenter;
import com.peoit.android.online.pschool.ui.Base.PsApplication;
import com.peoit.android.online.pschool.utils.MyLogger;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author:libo
 * time:2015/9/2
 * E-mail:boli_android@163.com
 * last: ...
 */
public class HXHelperPresenter extends BasePresenter implements EMEventListener {

    public static String groupid;

    public static boolean isLoginEME() {
        return !TextUtils.isEmpty(groupid);
    }

    public HXHelperPresenter(ActBase actBase) {
        super(actBase);
    }

    @Override
    public Map<String, String> getParams() {
        return null;
    }

    @Override
    public Class getGsonClass() {
        return null;
    }

    private static String currentUsername = "gly";
    private String currentPassword = "dba508b941b095bcd5060ff742a436e2";

    /**
     * 登录
     *
     * @param
     */
    public void login(final String userName) {
        if (isLoginEME()) {
            return;
        }

        if (!CommonUtils.isNetWorkConnected(mActBase.getActivity())) {
            mActBase.showToast("当前网路不可用");
            return;
        }

        if (TextUtils.isEmpty(currentUsername)) {
            //提示用户名不能为空
            mActBase.showToast(mActBase.getActivity().getString(R.string.User_name_cannot_be_empty));
            return;
        }
        if (TextUtils.isEmpty(currentPassword)) {
            mActBase.showToast(mActBase.getActivity().getString(R.string.Password_cannot_be_empty));
            return;
        }

        // 调用sdk登陆方法登陆聊天服务器
        EMChatManager.getInstance().login(userName, currentPassword, new EMCallBack() {
            @Override
            public void onSuccess() {
                // 登陆成功，保存用户名密码
                PsApplication.getInstance().setUserName(currentUsername);
                PsApplication.getInstance().setPassword(currentPassword);

                try {
                    // ** 第一次登录或者之前logout后再登录，加载所有本地群和回话
                    // ** manually load all local groups and
                    EMGroupManager.getInstance().loadAllGroups();
                    EMChatManager.getInstance().loadAllConversations();
                    // 处理好友和群组
                    initializeContacts();
                } catch (Exception e) {
                    e.printStackTrace();
                    // 取好友或者群聊失败，不让进入主页面
                    mActBase.getActivity().runOnUiThread(new Runnable() {
                        public void run() {
//                            pd.dismiss();
                            mActBase.hideLoadingDialog();
                            mActBase.showToast("登录失败1");
                        }
                    });
                    return;
                }
                List<EMGroup> grouplist = null;
                try {
                    grouplist = EMGroupManager.getInstance().getGroupsFromServer();
                } catch (EaseMobException e) {
                    e.printStackTrace();
                }
                if (grouplist != null && grouplist.size() > 0) {
                    groupid = grouplist.get(0).getGroupId();
                    CommonUtil.groupid = groupid;
                    EMGroup returnGroup = null;
                    MyLogger.i("groupid" + groupid);
                    try {
                        returnGroup = EMGroupManager.getInstance().getGroupFromServer(groupid);
                    } catch (EaseMobException e) {
                        e.printStackTrace();
                    }

                    // 更新本地数据
                    EMGroupManager.getInstance().createOrUpdateLocalGroup(returnGroup);

                    mActBase.getShare().put(Constants.LOGIN_CHAT_GROUP_ID, groupid);
                    mActBase.getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (mGorupIdListener != null) {
                                mGorupIdListener.onGroupId(groupid, false);
                            }
                        }
                    });
                } else {
                    mActBase.getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            onregist(userName);
                        }
                    });
                }
            }

            @Override
            public void onError(final int code, final String message) {
                mActBase.getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        onregist(userName);
                    }
                });

            }

            @Override
            public void onProgress(int i, String s) {

            }
        });
    }

    private void onregist(final String userName) {
        String name = CommonUtil.getUser_name();
        String pass = mActBase.getShare().getString(Constants.LOGIN_USER_PASS);
        RequestParams params = new RequestParams();
        params.put("userno", name);
        params.put("password", pass);
        new AsyncHttpClient().post(NetConstants.NET_REGISTOR, params, new AsyncHttpResponseHandler() {

            @Override
            public void onProgress(long bytesWritten, long totalSize) {

            }

            @Override
            public void onFinish() {

            }

            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody) {
                String result = getResponseString(responseBody, DEFAULT_CHARSET);
                final QueryNoallotInfo parseJson3 = new Gson().fromJson(result, QueryNoallotInfo.class);
                mActBase.getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (parseJson3 != null) {
                            if (parseJson3.isSuccess()) {
                                login(userName);
                            } else {
                                mActBase.showToast("登录失败(error code:2)");
                            }
                        } else {
                            mActBase.showToast("登录失败(error code:3)");
                        }
                    }
                });
            }

            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody, Throwable error) {
                String error1 = getResponseString(responseBody, UTF8_BOM);
                mActBase.getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mActBase.hideLoadingDialog();
                        mActBase.showToast("登录失败(error code:4)");
                    }
                });
            }
        });
    }

    public static final String UTF8_BOM = "\uFEFF";
    public static final String DEFAULT_CHARSET = "UTF-8";

    /**
     * Attempts to encode response bytes as string of set encoding
     *
     * @param charset     charset to create string with
     * @param stringBytes response bytes
     * @return String of set encoding or null
     */
    public static String getResponseString(byte[] stringBytes, String charset) {
        try {
            String toReturn = (stringBytes == null) ? null : new String(stringBytes, charset);
            if (toReturn != null && toReturn.startsWith(UTF8_BOM)) {
                return toReturn.substring(1);
            }
            MyLogger.e(" --- response string --- " + toReturn);
            return toReturn;
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    private void initializeContacts() {
        Map<String, User> userlist = new HashMap<String, User>();
        // 添加user"申请与通知"
        User newFriends = new User();
        newFriends.setUsername(Constant.NEW_FRIENDS_USERNAME);
        String strChat = mActBase.getActivity().getResources().getString(
                R.string.Application_and_notify);
        newFriends.setNick(strChat);

        userlist.put(Constant.NEW_FRIENDS_USERNAME, newFriends);
        // 添加"群聊"
        User groupUser = new User();
        String strGroup = mActBase.getActivity().getResources().getString(R.string.group_chat);
        groupUser.setUsername(Constant.GROUP_USERNAME);
        groupUser.setNick(strGroup);
        groupUser.setHeader("");
        userlist.put(Constant.GROUP_USERNAME, groupUser);

        // 添加"Robot"
        User robotUser = new User();
        String strRobot = mActBase.getActivity().getResources().getString(R.string.robot_chat);
        robotUser.setUsername(Constant.CHAT_ROBOT);
        robotUser.setNick(strRobot);
        robotUser.setHeader("");
        userlist.put(Constant.CHAT_ROBOT, robotUser);

        // 存入内存
        //PsApplication.getInstance().setContactList(userlist);
        // 存入db
        UserDao dao = new UserDao(mActBase.getActivity());
        List<User> users = new ArrayList<User>(userlist.values());
        dao.saveContactList(users);
    }

    private OnGroupIdListener mGorupIdListener;

    public void setOnGroupIdListener(OnGroupIdListener l) {
        this.mGorupIdListener = l;
    }

    @Override
    public void onEvent(EMNotifierEvent emNotifierEvent) {

    }

    public interface OnGroupIdListener {
        void onGroupId(String GroupId, boolean isToChat);
    }
}

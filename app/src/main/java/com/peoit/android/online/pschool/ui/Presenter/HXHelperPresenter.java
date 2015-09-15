package com.peoit.android.online.pschool.ui.Presenter;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.util.Log;

import com.easemob.EMCallBack;
import com.easemob.EMEventListener;
import com.easemob.EMNotifierEvent;
import com.easemob.chat.EMChatManager;
import com.easemob.chat.EMGroup;
import com.easemob.chat.EMGroupManager;
import com.easemob.chatuidemo.Constant;
import com.easemob.chatuidemo.DemoApplication;
import com.easemob.chatuidemo.db.UserDao;
import com.easemob.chatuidemo.domain.User;
import com.easemob.chatuidemo.utils.CommonUtils;
import com.easemob.exceptions.EaseMobException;
import com.peoit.android.online.pschool.ActBase;
import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.config.CommonUtil;
import com.peoit.android.online.pschool.config.Constants;
import com.peoit.android.online.pschool.entity.UserInfo;
import com.peoit.android.online.pschool.ui.Base.BasePresenter;
import com.peoit.android.online.pschool.ui.Base.PsApplication;
import com.peoit.android.online.pschool.ui.activity.HomeActivity;
import com.peoit.android.online.pschool.utils.MyLogger;

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
    private UserInfo userInfo;

    public static boolean isLoginEME(){
        return !TextUtils.isEmpty(groupid);
    }

    public HXHelperPresenter(ActBase actBase) {
        super(actBase);
        refresh();
    }

    @Override
    public Map<String, String> getParams() {
        return null;
    }

    @Override
    public Class getGsonClass() {
        return null;
    }

    private static String currentUsername = "xdd02";
    private String currentPassword = "dba508b941b095bcd5060ff742a436e2";
    private boolean progressShow;
    private String currentNikeName;

    public void refresh(){
        userInfo = CommonUtil.getCurrentUser();
        if (userInfo != null) {
            Log.i("onResume2", userInfo.toString());
            currentUsername = userInfo.getUsername();
            int type = CommonUtil.getIdEntityType();
            if (type == Constants.TYPE_PARENT) {
                currentNikeName = userInfo.getStuname();
            } else {
                currentNikeName = userInfo.getNickname();
            }
        }
    }

    /**
     * 登录
     *
     * @param
     */
    public void login(final boolean isToChat) {
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

        progressShow = true;

        final ProgressDialog pd = new ProgressDialog(mActBase.getActivity());
        pd.setCanceledOnTouchOutside(false);
        pd.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                progressShow = false;
            }
        });
        pd.setMessage(mActBase.getActivity().getString(R.string.Is_landing));
        pd.show();

        final long start = System.currentTimeMillis();

        // 调用sdk登陆方法登陆聊天服务器

        EMChatManager.getInstance().login(currentUsername, currentPassword, new EMCallBack() {
            @Override
            public void onSuccess() {
                if (!progressShow) {
                    return;
                }
                // 登陆成功，保存用户名密码
                PsApplication.getInstance().setUserName(currentUsername);
                PsApplication.getInstance().setPassword(currentPassword);
                // chatname = PsApplication.getInstance().getUserName();

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
                            pd.dismiss();
                            PsApplication.getInstance().logout(null);
                            mActBase.showToast(mActBase.getActivity().getResources().getString(R.string.login_failure_failed));
                        }
                    });
                    return;
                }

                if (!((HomeActivity) mActBase.getActivity()).isFinishing() && pd.isShowing()) {
                    pd.dismiss();
                }

                List<EMGroup> grouplist = null;
                try {
                    grouplist = EMGroupManager.getInstance().getGroupsFromServer();
                } catch (EaseMobException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                if (grouplist != null && grouplist.size() > 0) {
                    Log.i("grouplist", grouplist.size() + "---" + grouplist.toString());
                    Log.i("currentNikeName456", currentNikeName);

                    groupid = grouplist.get(0).getGroupId();

                    EMGroup returnGroup=null;
                    MyLogger.i("groupid" + groupid);
                    try {
                        returnGroup = EMGroupManager.getInstance().getGroupFromServer(groupid);
                    } catch (EaseMobException e) {
                        e.printStackTrace();
                    }

                    // 更新本地数据
                    EMGroupManager.getInstance().createOrUpdateLocalGroup(returnGroup);

                    EMGroup group=EMGroupManager.getInstance().getGroup(groupid);
                    List<String> members=group.getMembers();
                    MyLogger.i("members" + members.toString());
                    mActBase.getShare().put(Constants.LOGIN_GROUP_ID, groupid);

                    if (mGorupIdListener != null){
                        mGorupIdListener.onGroupId(groupid, isToChat);
                    }

                    DemoApplication.getInstance().setNickName(currentNikeName);
                }
            }

            @Override
            public void onProgress(int progress, String status) {
            }

            @Override
            public void onError(final int code, final String message) {
                if (!progressShow) {
                    return;
                }
                mActBase.getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        pd.dismiss();
                        mActBase.showToast(mActBase.getActivity().getString(R.string.Login_failed) + message);
                    }
                });
            }
        });
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

    public void setOnGroupIdListener(OnGroupIdListener l){
        this.mGorupIdListener = l;
    }

    @Override
    public void onEvent(EMNotifierEvent emNotifierEvent) {

    }

    public interface OnGroupIdListener{
        void onGroupId(String GroupId, boolean isToChat);
    }
}

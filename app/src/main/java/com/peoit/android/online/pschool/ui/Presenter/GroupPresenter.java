package com.peoit.android.online.pschool.ui.Presenter;

import android.text.TextUtils;

import com.easemob.chat.EMGroup;
import com.easemob.chat.EMGroupManager;
import com.peoit.android.online.pschool.ActBase;
import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.config.Constants;
import com.peoit.android.online.pschool.config.NetConstants;
import com.peoit.android.online.pschool.entity.UserInfo;
import com.peoit.android.online.pschool.entity.UserStatInfo;
import com.peoit.android.online.pschool.net.CallBack;
import com.peoit.android.online.pschool.ui.Base.BasePresenter;
import com.peoit.android.online.pschool.ui.adapter.GroupStatAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * author:libo
 * time:2015/9/1
 * E-mail:boli_android@163.com
 * last: ...
 */
public class GroupPresenter extends BasePresenter<UserInfo> {

    private GroupStatAdapter adapter;
    private List<UserStatInfo> list = new ArrayList<>();

    public GroupPresenter(ActBase actBase) {
        super(actBase);
    }

    public GroupStatAdapter getAdapter() {
        adapter = new GroupStatAdapter(mActBase.getActivity(), R.layout.group_list_item);
        return adapter;
    }

    public void doLoadUserGroup() {
        mActBase.showLoadingDialog("正在加载...");
        request(NetConstants.NET_CHAT_USERS, new CallBack<UserInfo>() {
            @Override
            public void onSimpleFailure(int error, String errorMsg) {
                //              mActBase.onResponseFailure(error, errorMsg);
                mActBase.showToast("查询失败");
            }

            @Override
            public void onSimpleSuccessList(List<UserInfo> result) {
                adapter.upDateList(getUserStat(result));
            }

            @Override
            public void onFinish() {
                mActBase.hideLoadingDialog();
            }
        });

    }

    private List<UserInfo> stus = new ArrayList<>();
    private List<UserInfo> teacs = new ArrayList<>();
    private List<UserInfo> zjs = new ArrayList<>();

    private List<UserStatInfo> getUserStat(List<UserInfo> result) {
        if (result == null || result.size() == 0)
            return null;
        if (list == null)
            list = new ArrayList<>();
        if (list.size() > 0)
            list.clear();
        stus.clear();
        teacs.clear();
        zjs.clear();
        for (int i = 0; i < result.size(); i++) {
            UserInfo info = result.get(i);
            if ("1".equals(info.getIdentityType())) {
                teacs.add(info);
            } else if ("2".equals(info.getIdentityType())) {
                stus.add(info);
            } else if ("3".equals(info.getIdentityType()) || "4".equals(info.getIdentityType())) {
                zjs.add(info);
            }
        }
        list.add(new UserStatInfo(zjs, "专家"));
        list.add(new UserStatInfo(teacs, "老师"));
        list.add(new UserStatInfo(stus, "学生家长"));
        return list;
    }

    @Override
    public Map<String, String> getParams() {
        String pass = mActBase.getShare().getString("pass");
        Map<String, String> params = getSignParams();
        String groupId = mActBase.getShare().getString(Constants.LOGIN_CHAT_GROUP_ID);
        //根据群聊ID从本地获取群聊信息
        EMGroup group = EMGroupManager.getInstance().getGroup(groupId);
        List<String> str = group.getMembers();//获取群成员
        String ower = group.getOwner();
        String member = "";
        if (str != null) {
            for (int i = 0; i < str.size(); i++) {
                if (ower.equals(str.get(i)))
                    continue;
                if (i == str.size() - 1) {
                    member += str.get(i);
                } else {
                    member += str.get(i) + ",";
                }
            }
        }
        params.put("password", pass);
        params.put("usernos", TextUtils.isEmpty(member) ? "" : member);
        return params;
    }

    @Override
    public Class<UserInfo> getGsonClass() {
        return UserInfo.class;
    }

}

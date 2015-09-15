package com.peoit.android.online.pschool.ui.Presenter;

import android.text.TextUtils;

import com.easemob.chat.EMGroup;
import com.easemob.chat.EMGroupManager;
import com.peoit.android.online.pschool.ActBase;
import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.config.Constants;
import com.peoit.android.online.pschool.config.NetConstants;
import com.peoit.android.online.pschool.entity.UserInfo;
import com.peoit.android.online.pschool.net.CallBack;
import com.peoit.android.online.pschool.ui.Base.BasePresenter;
import com.peoit.android.online.pschool.ui.adapter.UserGroupAdapter;

import java.util.List;
import java.util.Map;

/**
 * author:libo
 * time:2015/9/1
 * E-mail:boli_android@163.com
 * last: ...
 */
public class GroupPresenter extends BasePresenter<UserInfo> {

    private UserGroupAdapter adapter;

    public GroupPresenter(ActBase actBase) {
        super(actBase);
    }

    public UserGroupAdapter getAdapter() {
        adapter = new UserGroupAdapter(mActBase.getActivity(), R.layout.act_group_list_item);
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
                adapter.upDateList(result);
            }

            @Override
            public void onFinish() {
                mActBase.hideLoadingDialog();
            }
        });

    }

    @Override
    public Map<String, String> getParams() {
        String pass = mActBase.getShare().getString("pass");
        Map<String, String> params = getSignParams();

        String groupId = mActBase.getShare().getString(Constants.LOGIN_GROUP_ID);

        //根据群聊ID从本地获取群聊信息
        EMGroup group = EMGroupManager.getInstance().getGroup(groupId);

        List<String> str = group.getMembers();//获取群成员
        String ower = group.getOwner();
        String member = "";
        if (str != null){
            for (int i = 0; i < str.size(); i++) {
                if (ower.equals(str.get(i)))
                    continue;
                if (i == str.size() - 1){
                    member += str.get(i);
                } else {
                    member += str.get(i) + ",";
                }
            }
        }

        params.put("password", pass);
      // params.put("usernos", TextUtils.isEmpty(member) ? "" : member);
        params.put("usernos", TextUtils.isEmpty(member) ? "" : member);

        return params;
    }

    @Override
    public Class<UserInfo> getGsonClass() {
        return UserInfo.class;
    }

}

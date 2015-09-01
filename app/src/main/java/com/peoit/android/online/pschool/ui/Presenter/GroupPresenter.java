package com.peoit.android.online.pschool.ui.Presenter;

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

    public UserGroupAdapter getAdapter(){
        adapter = new UserGroupAdapter(mActBase.getActivity(), R.layout.act_group_list_item);
        return adapter;
    }

    public void doLoadUserGroup(){
        mActBase.showLoadingDialog("正在加载...");
        request(NetConstants.NET_CHAT_USERS, new CallBack<UserInfo>() {
            @Override
            public void onSimpleFailure(int error, String errorMsg) {
                mActBase.onResponseFailure(error, errorMsg);
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
        String groupId = mActBase.getShare().getString(Constants.LOGIN_GROUP_ID);
        Map<String, String> params = getSignParams();
        params.put("password", pass);
        params.put("usernos", groupId);
        return params;
    }

    @Override
    public Class<UserInfo> getGsonClass() {
        return UserInfo.class;
    }

}

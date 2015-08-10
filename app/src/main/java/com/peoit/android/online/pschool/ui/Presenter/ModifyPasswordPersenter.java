package com.peoit.android.online.pschool.ui.Presenter;

import com.peoit.android.online.pschool.ActBase;
import com.peoit.android.online.pschool.EntityBase;
import com.peoit.android.online.pschool.config.NetConstants;
import com.peoit.android.online.pschool.net.CallBack;
import com.peoit.android.online.pschool.ui.Base.BasePresenter;

import java.util.Map;

/**
 * author:libo
 * time:2015/8/9
 * E-mail:boli_android@163.com
 * last: ...
 */
public abstract class ModifyPasswordPersenter extends BasePresenter {

    public ModifyPasswordPersenter(ActBase actBase) {
        super(actBase);
    }

    public void doModify() {
        mActBase.showLoadingDialog("正在修改...");
        request(NetConstants.NET_MODIFY_PASSWORD, new CallBack() {

            @Override
            public void onFinish() {
                mActBase.hideLoadingDialog();
            }

            @Override
            public void onSimpleFailure(int error, String errorMsg) {
                mActBase.onResponseFailure(error, errorMsg);
            }

            @Override
            public void onSimpleSuccess(EntityBase result) {
                mActBase.showToast("修改成功");
                mActBase.finish();
            }
        });
    }

    @Override
    public Map<String, String> getParams() {
        Map<String, String> params = getSignParams();
        return getNewPassword(params);
    }

    protected abstract Map<String, String> getNewPassword(Map<String, String> params);

    @Override
    public Class getGsonClass() {
        return null;
    }
}

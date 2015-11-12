package com.peoit.android.online.pschool.ui.Presenter;

import android.app.Activity;
import android.content.Intent;

import com.peoit.android.online.pschool.ActBase;
import com.peoit.android.online.pschool.EntityBase;
import com.peoit.android.online.pschool.config.NetConstants;
import com.peoit.android.online.pschool.net.CallBack;
import com.peoit.android.online.pschool.ui.Base.BasePresenter;

import java.util.Map;

/**
 * author:libo
 * time:2015/8/11
 * E-mail:boli_android@163.com
 * last: ...
 */
public class AddQandAPresenter extends BasePresenter {

    private Map<String, String> mParams;

    public AddQandAPresenter(ActBase actBase) {
        super(actBase);
    }

    public void doAddQ(final String id, String text, boolean isPublic) {
        mParams = getSignParams();
        mParams.put("id", id);
        mParams.put("text", text);
        mParams.put("ispulic", isPublic ? "Y" : "N");
        mActBase.showLoadingDialog("正在上传提问信息...");
        request(NetConstants.NET_ADD_Q, new CallBack() {
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
                mActBase.showToast("提交成功");
                mActBase.getActivity().setResult(Activity.RESULT_OK, new Intent());
                mActBase.finish();
            }
        });
    }

    public void doAddR(final String id, String text) {
        mParams = getSignParams();
        mParams.put("id", id);
        mParams.put("text", text);
        mActBase.showLoadingDialog("正在上传提问信息...");
        request(NetConstants.NET_ADD_R, new CallBack() {
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
                mActBase.showToast("提交成功");
                mActBase.getActivity().setResult(Activity.RESULT_OK, new Intent());
                mActBase.finish();
            }
        });
    }

    @Override
    public Map<String, String> getParams() {
        return mParams;
    }

    @Override
    public Class getGsonClass() {
        return null;
    }
}

package com.peoit.android.online.pschool.ui.Presenter;

import com.peoit.android.online.pschool.ActBase;
import com.peoit.android.online.pschool.config.CommonUtil;
import com.peoit.android.online.pschool.config.NetConstants;
import com.peoit.android.online.pschool.entity.HomeZhuanJiaInfo;
import com.peoit.android.online.pschool.net.CallBack;
import com.peoit.android.online.pschool.ui.Base.BasePresenter;

/**
 * author:libo
 * time:2015/8/13
 * E-mail:boli_android@163.com
 * last: ...
 */
public abstract class HomeZhuanJiaPresenter extends BasePresenter<HomeZhuanJiaInfo> {

    public HomeZhuanJiaPresenter(ActBase actBase) {
        super(actBase);
    }
    public void doHomeZhuanJia(){
        mActBase.showLoadingDialog("正在加载...");
        request(NetConstants.NET_HOME_EXPERT, new CallBack<HomeZhuanJiaInfo>() {

            @Override
            public void onSimpleFailure(int error, String errorMsg) {
                mActBase.hideLoadingDialog();
            }

            @Override
            public void onSimpleSuccess(HomeZhuanJiaInfo result) {
                CommonUtil.showToast(""+result.getObj());
            }
        });
    }
}

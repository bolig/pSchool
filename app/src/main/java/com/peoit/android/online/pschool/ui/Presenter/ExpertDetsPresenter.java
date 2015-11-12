package com.peoit.android.online.pschool.ui.Presenter;


import com.peoit.android.online.pschool.ActBase;
import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.config.NetConstants;
import com.peoit.android.online.pschool.entity.FeatureInfo;
import com.peoit.android.online.pschool.net.CallBack;
import com.peoit.android.online.pschool.ui.Base.BasePresenter;
import com.peoit.android.online.pschool.ui.adapter.FeatureAdapter;

import java.util.List;
import java.util.Map;

/**
 * author:libo
 * time:2015/10/22
 * E-mail:boli_android@163.com
 * last: ...
 */
public class ExpertDetsPresenter extends BasePresenter<FeatureInfo> {
    private FeatureAdapter adapter;
    private String uid;

    public ExpertDetsPresenter(ActBase actBase) {
        super(actBase);
    }

    private boolean isFirst = true;

    public void load(String uid){
        if (isFirst){
            mActBase.getUIShowPresenter().doShowloading();
        }
        this.uid = uid;
        request(NetConstants.NET_FEATURE_LIST, new CallBack<FeatureInfo>() {

            @Override
            public void onSimpleSuccessList(List<FeatureInfo> result) {
                adapter.upDateList(result);
                mActBase.getUIShowPresenter().doShowData();
                isFirst = false;
            }

            @Override
            public void onSimpleFailure(int error, String errorMsg) {
                mActBase.onResponseFailure(error, errorMsg);
                if (isFirst){
                    mActBase.getUIShowPresenter().doShowNodata(R.drawable.nocolumimage);
                }
            }
        });
    }

    @Override
    public Map<String, String> getParams() {
        Map<String, String> params = getSignParams();
        int skip = adapter.getCount();
        params.put("pagesize", "10000");
        params.put("skip", skip + "");
        params.put("uid", uid);
        return params;
    }

    @Override
    public Class<FeatureInfo> getGsonClass() {
        return FeatureInfo.class;
    }

    public FeatureAdapter getAdapter() {
        this.adapter = new FeatureAdapter(mActBase.getActivity(), R.layout.act_feature_item, null);
        return adapter;
    }
}

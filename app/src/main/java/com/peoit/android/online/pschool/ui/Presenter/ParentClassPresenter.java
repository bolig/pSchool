package com.peoit.android.online.pschool.ui.Presenter;

import com.peoit.android.online.pschool.ActBase;
import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.config.NetConstants;
import com.peoit.android.online.pschool.entity.FeatureInfo;
import com.peoit.android.online.pschool.net.CallBack;
import com.peoit.android.online.pschool.ui.Base.BasePresenter;
import com.peoit.android.online.pschool.ui.adapter.FeatureAdapter;
import com.peoit.android.online.pschool.ui.view.PullToRefreshLayout;

import java.util.List;
import java.util.Map;

/**
 * Created by zyz on 2015/8/11.
 */
public class ParentClassPresenter extends BasePresenter<FeatureInfo> implements PullToRefreshLayout.OnRefreshListener {

    private int skip = 0;
    private int pagesize = 10;
    private boolean isFirst = true;
    private FeatureAdapter adapter;
    private PullToRefreshLayout loadLayout;
    private String mSonType;

    public ParentClassPresenter(ActBase actBase) {
        super(actBase);
    }

    public void load(String sonType) {
        this.mSonType = sonType;
        if (isFirst) {
            mActBase.getUIShowPresenter().doShowloading();
            isFirst = false;
        }
        request(NetConstants.NET_FEATURE_LIST, new CallBack<FeatureInfo>() {

            @Override
            public void onFinish() {
                mActBase.hideLoadingDialog();
            }

            @Override
            public void onSimpleFailure(int error, String errorMsg) {
                mActBase.onResponseFailure(error, errorMsg);
                if (adapter.getCount() == 0){
                    mActBase.getUIShowPresenter().doShowNodata(R.drawable.nocolumimage);
                }
                if (loadLayout != null) {
                    loadLayout.refreshFinish(PullToRefreshLayout.FAIL);
                }
            }

            @Override
            public void onSimpleSuccessList(List<FeatureInfo> result) {
                skip += adapter.getCount();
                if (result.size() == 0) {
                    if (adapter.getCount() == 0)
                        mActBase.getUIShowPresenter().doShowNodata(R.drawable.nocolumimage);
                } else {
                    mActBase.getUIShowPresenter().doShowData();
                    adapter.addHeadDataList(result);
                }
                if (loadLayout != null) {
                    loadLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
                }
            }
        });
    }

    public void loadMore(String sonType) {
        this.mSonType = sonType;
        request(NetConstants.NET_FEATURE_LIST, new CallBack<FeatureInfo>() {

            @Override
            public void onSimpleFailure(int error, String errorMsg) {
                mActBase.onResponseFailure(error, errorMsg);
                if (loadLayout != null) {
                    loadLayout.loadmoreFinish(PullToRefreshLayout.FAIL);
                }
            }

            @Override
            public void onSimpleSuccessList(List<FeatureInfo> result) {
                adapter.addFootDataList(result);
                if (loadLayout != null) {
                    loadLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
                }
            }
        });
    }

    public FeatureAdapter getAdapter() {
        this.adapter = new FeatureAdapter(mActBase.getActivity(), R.layout.act_feature_item, null);
        return adapter;
    }

    @Override
    public Map<String, String> getParams() {
        Map<String, String> params = getSignParams();
        params.put("pagesize", pagesize + "");
        params.put("skip", skip + "");
        params.put("type", "家长课堂");
        params.put("sontype", mSonType);
        return params;
    }

    @Override
    public Class<FeatureInfo> getGsonClass() {
        return FeatureInfo.class;
    }

    @Override
    public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
        skip = 0;
        this.loadLayout = pullToRefreshLayout;
        String tag = (String) pullToRefreshLayout.getTag();
        load(mSonType);
    }

    @Override
    public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
        skip = adapter.getCount();
        this.loadLayout = pullToRefreshLayout;
        String tag = (String) pullToRefreshLayout.getTag();
        load(mSonType);
    }
}

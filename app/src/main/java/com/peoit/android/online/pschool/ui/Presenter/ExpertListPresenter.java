package com.peoit.android.online.pschool.ui.Presenter;

import com.peoit.android.online.pschool.ActBase;
import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.config.NetConstants;
import com.peoit.android.online.pschool.entity.ExpertListInfo;
import com.peoit.android.online.pschool.net.CallBack;
import com.peoit.android.online.pschool.ui.Base.BasePresenter;
import com.peoit.android.online.pschool.ui.adapter.ExpertListAdapter1;
import com.peoit.android.online.pschool.ui.view.PullToRefreshLayout;

import java.util.List;
import java.util.Map;

/**
 * author:libo
 * time:2015/10/13
 * E-mail:boli_android@163.com
 * last: ...
 */
public class ExpertListPresenter extends BasePresenter<ExpertListInfo> implements PullToRefreshLayout.OnRefreshListener {

    private int mSkip = 0;
    private ExpertListAdapter1 mExpertAdapter;
    private boolean isFisrt = true;

    public ExpertListPresenter(ActBase actBase) {
        super(actBase);
    }

    public ExpertListAdapter1 getAdapter() {
        mExpertAdapter = new ExpertListAdapter1(mActBase.getActivity(), R.layout.act_expert_list_item);
        return mExpertAdapter;
    }

    @Override
    public Map<String, String> getParams() {
        Map<String, String> params = getSignParams();
        params.put("pagesize", 10 + "");
        params.put("skip", (mSkip < 0 ? 0 : mSkip) + "");
        return params;
    }

    @Override
    public Class<ExpertListInfo> getGsonClass() {
        return ExpertListInfo.class;
    }

    public void loadExpertList(final PullToRefreshLayout layout) {
        if (isFisrt) {
            mActBase.getUIShowPresenter().doShowloading();
            isFisrt = false;
        }
        request(NetConstants.NET_QUERY_EXPIST_LIST, new CallBack<ExpertListInfo>() {
            @Override
            public void onSimpleSuccessList(List<ExpertListInfo> result) {
                if (result.size() == 0) {
                    if (layout != null){
                        layout.refreshFinish(PullToRefreshLayout.FAIL);
                    }
                    return;
                }
                if (layout != null) {
                    layout.refreshFinish(PullToRefreshLayout.SUCCEED);
                }
                mActBase.getUIShowPresenter().doShowData();
                mExpertAdapter.addHeadDataList(result);
            }

            @Override
            public void onSimpleFailure(int error, String errorMsg) {
                if (layout != null)
                    layout.refreshFinish(PullToRefreshLayout.FAIL);
                if (mExpertAdapter.getCount() == 0) {
                    mActBase.getUIShowPresenter().doShowNodata(R.drawable.nocolumimage);
                }
            }
        });
    }

    public void loadExpertListMore(final PullToRefreshLayout layout) {
        mSkip = mExpertAdapter.getCount();
        request(NetConstants.NET_QUERY_EXPIST_LIST, new CallBack<ExpertListInfo>() {
            @Override
            public void onSimpleSuccessList(List<ExpertListInfo> result) {
                if (result.size() == 0) {
                    if (layout != null){
                        layout.loadmoreFinish(PullToRefreshLayout.FAIL);
                    }
                    return;
                }
                if (layout != null) {
                    layout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
                }
                mExpertAdapter.addFootDataList(result);
            }

            @Override
            public void onSimpleFailure(int error, String errorMsg) {
                if (layout != null)
                    layout.loadmoreFinish(PullToRefreshLayout.FAIL);
                if (mExpertAdapter.getCount() == 0) {
                    mActBase.getUIShowPresenter().doShowNodata(R.drawable.nocolumimage);
                }
            }
        });
    }

    @Override
    public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
        mSkip = 0;
        loadExpertList(pullToRefreshLayout);
    }

    @Override
    public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
        mSkip = mExpertAdapter.getCount();
        loadExpertListMore(pullToRefreshLayout);
    }
}

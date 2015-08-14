package com.peoit.android.online.pschool.ui.Presenter;

import com.peoit.android.online.pschool.ActBase;
import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.config.CommonUtil;
import com.peoit.android.online.pschool.config.NetConstants;
import com.peoit.android.online.pschool.entity.ExpertsOnlineInfo;
import com.peoit.android.online.pschool.net.CallBack;
import com.peoit.android.online.pschool.ui.Base.BasePresenter;
import com.peoit.android.online.pschool.ui.adapter.ExpertsOnlineAdapter;
import com.peoit.android.online.pschool.ui.view.PullToRefreshLayout;

import java.util.List;
import java.util.Map;

/**
 * Created by zyz on 2015/8/12.
 */
public class ExpertsOnlinePresenter extends BasePresenter<ExpertsOnlineInfo> implements PullToRefreshLayout.OnRefreshListener {

    private final String type;
    private int skip = 0;
    private int pagesize = 10;
    private boolean isFirst = true;
    private ExpertsOnlineAdapter adapter;
    private PullToRefreshLayout loadLayout;

    public ExpertsOnlinePresenter(ActBase actBase, String type) {
        super(actBase);
        this.type = type;
    }

    public void load() {
        if (isFirst) {
            mActBase.showLoadingDialog("正在加载...");
            isFirst = false;
        }
        skip = 0;
        pagesize = 10;
        request(NetConstants.NET_EXPERTSONLINE, new CallBack<ExpertsOnlineInfo>() {

            @Override
            public void onFinish() {
                mActBase.hideLoadingDialog();
            }

            @Override
            public void onSimpleFailure(int error, String errorMsg) {
                mActBase.onResponseFailure(error, errorMsg);
                if (loadLayout != null) {
                    loadLayout.refreshFinish(PullToRefreshLayout.FAIL);
                }
            }

            @Override
            public void onSimpleSuccessList(List<ExpertsOnlineInfo> result) {
                System.out.println("专家在线请求到的数据" + result);
                if (result.size() == 0){
                    CommonUtil.showToast("暂无数据");
                }else {
                    adapter.upDateList(result);
//                    adapter.notifyDataSetChanged();
                }
                if (loadLayout != null) {
                    loadLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
                }
            }
        });
    }

    public void loadMore() {
        skip += pagesize;
        pagesize += pagesize;
        request(NetConstants.NET_EXPERTSONLINE, new CallBack<ExpertsOnlineInfo>() {

            @Override
            public void onSimpleFailure(int error, String errorMsg) {
                mActBase.onResponseFailure(error, errorMsg);
                if (loadLayout != null) {
                    loadLayout.loadmoreFinish(PullToRefreshLayout.FAIL);
                }
            }

            @Override
            public void onSimpleSuccessList(List<ExpertsOnlineInfo> result) {

                adapter.addFootDataList(result);
                if (loadLayout != null) {
                    loadLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
                }
            }
        });
    }

    public ExpertsOnlineAdapter getAdapter(){
        this.adapter = new ExpertsOnlineAdapter(mActBase.getActivity(), R.layout.item_parentsclassroom, null) {
        };

        return adapter;
    }

    @Override
    public Map<String, String> getParams() {
        Map<String, String> params = getSignParams();
        params.put("pagesize", pagesize + "");
        params.put("skip", skip + "");
        params.put("id", "40");
//        params.put("type", type);
        System.out.println(">>>>>>>>>>专家提问请求数据："+params.toString());
        return params;
    }

    @Override
    public Class<ExpertsOnlineInfo> getGsonClass() {
        return ExpertsOnlineInfo.class;
    }

    @Override
    public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
        this.loadLayout = pullToRefreshLayout;

        load();
    }

    @Override
    public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
        this.loadLayout = pullToRefreshLayout;

        loadMore();
    }
}

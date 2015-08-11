package com.peoit.android.online.pschool.ui.Presenter;

import com.peoit.android.online.pschool.ActBase;
import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.config.NetConstants;
import com.peoit.android.online.pschool.entity.NoticeInfo;
import com.peoit.android.online.pschool.net.CallBack;
import com.peoit.android.online.pschool.ui.Base.BasePresenter;
import com.peoit.android.online.pschool.ui.adapter.NoticeAdapter;
import com.peoit.android.online.pschool.ui.view.PullToRefreshLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * author:libo
 * time:2015/8/9
 * E-mail:boli_android@163.com
 * last: ...
 */
public class NoticePersenter extends BasePresenter<NoticeInfo> implements PullToRefreshLayout.OnRefreshListener {

    private List<NoticeInfo> noticeInfos = new ArrayList<>();
    private NoticeAdapter adapter;
    private PullToRefreshLayout loadLayout;

    public NoticePersenter(ActBase actBase) {
        super(actBase);
    }

    public NoticeAdapter getNoticeAdapter() {
        adapter = new NoticeAdapter(mActBase.getActivity(), R.layout.act_notice_item, noticeInfos);
        return adapter;
    }

    private boolean isFirst = true;

    public void load() {
        if (isFirst) {
            mActBase.showLoadingDialog("正在加载...");
            isFirst = false;
        }
        skip = 0;
        request(NetConstants.NET_NOTICE_LIST, new CallBack<NoticeInfo>() {

            @Override
            public void onFinish() {
                mActBase.hideLoadingDialog();
            }

            @Override
            public void onSimpleFailure(int error, String errorMsg) {
                mActBase.onResponseFailure(error, errorMsg);
                if (loadLayout != null){
                    loadLayout.refreshFinish(PullToRefreshLayout.FAIL);
                }
            }

            @Override
            public void onSimpleSuccessList(List<NoticeInfo> result) {
                adapter.upDateList(result);
                skip += pagesize;
                if (loadLayout != null){
                    loadLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
                }
            }
        });
    }

    public void loadMore() {
        request(NetConstants.NET_NOTICE_LIST, new CallBack<NoticeInfo>() {

            @Override
            public void onSimpleFailure(int error, String errorMsg) {
                mActBase.onResponseFailure(error, errorMsg);
                if (loadLayout != null){
                    loadLayout.loadmoreFinish(PullToRefreshLayout.FAIL);
                }
            }

            @Override
            public void onSimpleSuccessList(List<NoticeInfo> result) {
                adapter.addFootDataList(result);
                skip += pagesize;
                if (loadLayout != null){
                    loadLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
                }
            }
        });
    }

    private int skip = 0;
    private int pagesize = 10;

    @Override
    public Map<String, String> getParams() {
        Map<String, String> params = getSignParams();
        params.put("pagesize", pagesize + "");
        params.put("skip", skip + "");
        return params;
    }

    @Override
    public Class<NoticeInfo> getGsonClass() {
        return NoticeInfo.class;
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

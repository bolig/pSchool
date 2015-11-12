package com.peoit.android.online.pschool.ui.Presenter;

import android.text.TextUtils;

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
public class ParentClassroomPresenter extends BasePresenter<FeatureInfo> implements PullToRefreshLayout.OnRefreshListener{

    private final String type;
    private int skip = 0;
    private int pagesize = 10;
    private boolean isFirst = true;
    private FeatureAdapter adapter;
    private PullToRefreshLayout loadLayout;
    private String mArea;

    public ParentClassroomPresenter(ActBase actBase, String type) {
        super(actBase);
        this.type = type;
    }

    public void load() {
        if (isFirst) {
            mActBase.showLoadingDialog("正在加载...");
            isFirst = false;
        }
        skip = 0;
        request(NetConstants.NET_FEATURE_LIST, new CallBack<FeatureInfo>() {

            @Override
            public void onFinish() {
                mActBase.hideLoadingDialog();
            }

            @Override
            public void onSimpleFailure(int error, String errorMsg) {
                mActBase.onResponseFailure(error, errorMsg);
                mActBase.getUIShowPresenter().doShowNodata(R.drawable.nocolumimage);
                if (loadLayout != null) {
                    loadLayout.refreshFinish(PullToRefreshLayout.FAIL);
                }
            }

            @Override
            public void onSimpleSuccessList(List<FeatureInfo> result) {
                System.out.println("请求到的数据" + result);
                skip += pagesize;
                if (result.size() == 0){
                    mActBase.getUIShowPresenter().doShowNodata(R.drawable.nocolumimage);

                }else {
                    adapter.upDateList(result);
                }
                if (loadLayout != null) {
                    loadLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
                }
            }
        });
    }

    public void loadMore() {
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
                skip += pagesize;
                adapter.addFootDataList(result);
                if (loadLayout != null) {
                    loadLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
                }
            }
        });
    }

    public void load(String area) {
        this.mArea = area;
        if (isFirst) {
            mActBase.showLoadingDialog("正在加载...");
            isFirst = false;
        }
        skip = 0;
        request(NetConstants.NET_FEATURE_LIST, new CallBack<FeatureInfo>() {

            @Override
            public void onFinish() {
                mActBase.hideLoadingDialog();
            }

            @Override
            public void onSimpleFailure(int error, String errorMsg) {
                mActBase.onResponseFailure(error, errorMsg);
                mActBase.getUIShowPresenter().doShowNodata(R.drawable.nocolumimage);
                if (loadLayout != null) {
                    loadLayout.refreshFinish(PullToRefreshLayout.FAIL);
                }
            }

            @Override
            public void onSimpleSuccessList(List<FeatureInfo> result) {
                System.out.println("请求到的数据" + result);
                skip += pagesize;
                if (result.size() == 0){
                    mActBase.getUIShowPresenter().doShowNodata(R.drawable.nocolumimage);

                }else {
                    adapter.upDateList(result);
                }
                if (loadLayout != null) {
                    loadLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
                }
            }
        });
    }

    public void loadMore(String area) {
        this.mArea = area;
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
                skip += pagesize;
                adapter.addFootDataList(result);
                if (loadLayout != null) {
                    loadLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
                }
            }
        });
    }

    public FeatureAdapter getAdapter(){
        this.adapter = new FeatureAdapter(mActBase.getActivity(), R.layout.act_feature_item, null);
        return adapter;
    }

    @Override
    public Map<String, String> getParams() {
        Map<String, String> params = getSignParams();
        params.put("pagesize", pagesize + "");
        params.put("skip", skip  + "");
        params.put("type", type);
        if (!TextUtils.isEmpty(mArea)){
            params.put("area", mArea);
        }
        return params;
    }

    @Override
    public Class<FeatureInfo> getGsonClass() {
        return FeatureInfo.class;
    }

    @Override
    public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
        this.loadLayout = pullToRefreshLayout;
        String tag = (String) pullToRefreshLayout.getTag();
        if ("1".equals(tag)){
            load(mArea);
        } else {
            load();
        }
    }

    @Override
    public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
        this.loadLayout = pullToRefreshLayout;
        String tag = (String) pullToRefreshLayout.getTag();
        if ("1".equals(tag)){
            loadMore(mArea);
        } else {
            loadMore();
        }
    }
}

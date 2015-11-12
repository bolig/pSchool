package com.peoit.android.online.pschool.ui.Presenter;

import com.peoit.android.online.pschool.ActBase;
import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.config.NetConstants;
import com.peoit.android.online.pschool.entity.VideoInfo;
import com.peoit.android.online.pschool.net.CallBack;
import com.peoit.android.online.pschool.ui.Base.BasePresenter;
import com.peoit.android.online.pschool.ui.adapter.VideoAdapter;
import com.peoit.android.online.pschool.ui.view.PullToRefreshLayout;

import java.util.List;
import java.util.Map;

/**
 * author:libo
 * time:2015/10/12
 * E-mail:boli_android@163.com
 * last: ...
 */
public class VideoListPresenter extends BasePresenter<VideoInfo> implements PullToRefreshLayout.OnRefreshListener {

    private int mSkip = 0;
    private VideoAdapter mVideoAdapter;

    public VideoListPresenter(ActBase actBase) {
        super(actBase);
    }

    @Override
    public Map<String, String> getParams() {
//        String name = CommonUtil.getUser_name();
//        String pass = mActBase.getShare().getString(Constants.LOGIN_USER_PASS);
//        Map<String, String> params = new HashMap<>();
//        params.put("userno", name);
//        params.put("sign", pass);
        Map<String, String> params = getSignParams();
        params.put("pagesize", "10");
        params.put("skip", mSkip + "");
        return params;
    }

    @Override
    public Class<VideoInfo> getGsonClass() {
        return VideoInfo.class;
    }

    public VideoAdapter getVideoAdapter() {
        mVideoAdapter = new VideoAdapter(mActBase.getActivity(), R.layout.act_online_video_list_item);
        return mVideoAdapter;
    }

    private boolean isFirst = true;

    public void loadVedio(final PullToRefreshLayout layout) {
        if (isFirst) {
            mActBase.getUIShowPresenter().doShowloading();
            isFirst = false;
        }
        request(NetConstants.NET_QUERYVIDEO, new CallBack<VideoInfo>() {

            @Override
            public void onSimpleSuccessList(List<VideoInfo> result) {
                if (mVideoAdapter != null) {
                    mActBase.getUIShowPresenter().doShowData();
                    mVideoAdapter.addHeadDataList(result);
                }
                if (layout != null) {
                    layout.refreshFinish(PullToRefreshLayout.SUCCEED);
                }
                mSkip += mVideoAdapter.getCount() - 1;
            }

            @Override
            public void onSimpleFailure(int error, String errorMsg) {
                mActBase.onResponseFailure(error, errorMsg);
                if (mVideoAdapter.getCount() == 0) {
                    mActBase.getUIShowPresenter().doShowNodata(R.drawable.nocolumimage);
                    mActBase.showToast("加载失败");
                }
                if (layout != null) {
                    layout.refreshFinish(PullToRefreshLayout.FAIL);
                }
            }
        });
    }

    public void loadMoreVideo(final PullToRefreshLayout layout) {
        request(NetConstants.NET_QUERYVIDEO, new CallBack<VideoInfo>() {

            @Override
            public void onSimpleSuccessList(List<VideoInfo> result) {
                if (mVideoAdapter != null) {
                    mActBase.getUIShowPresenter().doShowData();
                    mVideoAdapter.addFootDataList(result);
                }
                layout.refreshFinish(PullToRefreshLayout.SUCCEED);
                mSkip += mVideoAdapter.getCount() - 1;
            }

            @Override
            public void onSimpleFailure(int error, String errorMsg) {
                mActBase.onResponseFailure(error, errorMsg);
                if (mVideoAdapter.getCount() == 0) {
                    mActBase.showToast("加载失败");
                } else {
                    mActBase.showToast("已是最后一页");
                }
                layout.refreshFinish(PullToRefreshLayout.SUCCEED);
            }
        });
    }

    @Override
    public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
        mSkip = 0;
        loadVedio(pullToRefreshLayout);
    }

    @Override
    public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
        mSkip = mVideoAdapter.getCount();
        loadMoreVideo(pullToRefreshLayout);
    }
}

package com.peoit.android.online.pschool.ui.Presenter;

import android.view.View;

import com.peoit.android.online.pschool.ActBase;
import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.config.NetConstants;
import com.peoit.android.online.pschool.entity.CheckInInfo;
import com.peoit.android.online.pschool.net.CallBack;
import com.peoit.android.online.pschool.ui.Base.BasePresenter;
import com.peoit.android.online.pschool.ui.adapter.CheckedInAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * author:libo
 * time:2015/9/5
 * E-mail:boli_android@163.com
 * last: ...
 */
public class CheckedInPresenter extends BasePresenter<CheckInInfo> {

    private String day;
    private CheckedInAdapter adapter;
    public static List<CheckInInfo> mCheckInInfos;

    public CheckedInPresenter(ActBase actBase) {
        super(actBase);
    }

    public CheckedInAdapter getAdapter(View head) {
        this.adapter = new CheckedInAdapter(mActBase.getActivity(), R.layout.act_check_item, head);
        return this.adapter;
    }

    public void doLoadCheckIn(String day) {
        this.day = day;

        mActBase.getUIShowPresenter().doShowloading();

        request(NetConstants.NET_CHECK_IN_LIST, new CallBack<CheckInInfo>() {
            @Override
            public void onSimpleFailure(int error, String errorMsg) {
                mActBase.onResponseFailure(error, errorMsg);
                mActBase.getUIShowPresenter().doShowNodata(R.drawable.nocheck_in);
            }

            @Override
            public void onSimpleSuccessList(List<CheckInInfo> result) {
                System.out.println(">>>>>>>>>考勤查询数据：" + result);
                if (result != null && result.size() != 0) {
                    mActBase.getUIShowPresenter().doShowData();
                    adapter.upDateList(result);
                    mCheckInInfos = result;
                } else {
                    mActBase.getUIShowPresenter().doShowNodata(R.drawable.nocheck_in);
                }
            }
        });
    }

    @Override
    public Map<String, String> getParams() {
        Map<String, String> params = getSignParams();
        params.put("day", day);
        return params;
    }

    @Override
    public Class<CheckInInfo> getGsonClass() {
        return CheckInInfo.class;
    }


    public void changeAdapterShow(int position) {
        if (adapter == null)
            return;
        switch (position) {
            case 0:
                adapter.upDateList(mCheckInInfos);
                break;
            case 1:
            case 2:
            case 3:
                change(position);
                break;
        }
    }

    private void change(int index) {
        if (mCheckInInfos == null || mCheckInInfos.size() == 0)
            return;
        List<CheckInInfo> inInfos = new ArrayList<>();
        for (CheckInInfo info : mCheckInInfos) {
            if (index == info.getType()){
                inInfos.add(info);
            }
        }
        adapter.upDateList(inInfos);
    }
}

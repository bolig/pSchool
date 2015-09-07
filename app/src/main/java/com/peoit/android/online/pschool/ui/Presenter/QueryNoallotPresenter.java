package com.peoit.android.online.pschool.ui.Presenter;

import android.os.Handler;
import android.widget.TextView;

import com.peoit.android.online.pschool.ActBase;
import com.peoit.android.online.pschool.config.NetConstants;
import com.peoit.android.online.pschool.entity.QueryNoallotInfo;
import com.peoit.android.online.pschool.net.CallBack;
import com.peoit.android.online.pschool.ui.Base.BasePresenter;

import java.util.Map;

/**
 * Created by Administrator on 2015/9/6.
 */
public class QueryNoallotPresenter extends BasePresenter<QueryNoallotInfo> {
    private final TextView tv_query;

    public QueryNoallotPresenter(ActBase actBase, TextView tv_Query) {
        super(actBase);
        this.tv_query = tv_Query;
    }

    public void doLoadQueryNoallot(){
        request(NetConstants.NET_HOME_EXPERT, new CallBack<QueryNoallotInfo>() {
            @Override
            public void onSimpleFailure(int error, String errorMsg) {
                mActBase.onResponseFailure(error, errorMsg);
            }

            @Override
            public void onSimpleSuccess1(QueryNoallotInfo info) {
                tv_query.setText(info.getObj());
            }

            @Override
            public void onFinish() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        doLoadQueryNoallot();
                    }
                }, 5000);
            }
        });
    }

    @Override
    public Map<String, String> getParams() {
        return null;
    }

    @Override
    public Class<QueryNoallotInfo> getGsonClass() {
        return null;
    }

    public void start(){
        doLoadQueryNoallot();
    }
}

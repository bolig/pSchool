package com.peoit.android.online.pschool.ui.Presenter;

import com.peoit.android.online.pschool.ActBase;
import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.config.NetConstants;
import com.peoit.android.online.pschool.entity.SingleGradeInfo;
import com.peoit.android.online.pschool.net.CallBack;
import com.peoit.android.online.pschool.ui.Base.BasePresenter;
import com.peoit.android.online.pschool.ui.adapter.GradeByIdAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * author:libo
 * time:2015/8/10
 * E-mail:boli_android@163.com
 * last: ...
 */
public class GradeByidPersenter extends BasePresenter<SingleGradeInfo> {

    private final int id;
    private GradeByIdAdapter adapter;

    private List<SingleGradeInfo> infos = new ArrayList<>();

    public GradeByidPersenter(ActBase actBase, int id) {
        super(actBase);
        this.id = id;
        infos.add(new SingleGradeInfo());
        infos.add(new SingleGradeInfo());
        infos.add(new SingleGradeInfo());
        infos.add(new SingleGradeInfo());
        infos.add(new SingleGradeInfo());
        infos.add(new SingleGradeInfo());
        infos.add(new SingleGradeInfo());
        infos.add(new SingleGradeInfo());
        infos.add(new SingleGradeInfo());
        infos.add(new SingleGradeInfo());
        infos.add(new SingleGradeInfo());
        infos.add(new SingleGradeInfo());
        infos.add(new SingleGradeInfo());
        infos.add(new SingleGradeInfo());
        infos.add(new SingleGradeInfo());
        infos.add(new SingleGradeInfo());
        infos.add(new SingleGradeInfo());
        adapter = new GradeByIdAdapter(mActBase.getActivity(), R.layout.act_grade_by_id_item, infos);
    }

    public GradeByIdAdapter getAdapter() {
        return adapter;
    }

    public void toGetGradeByIdList(){
        request(NetConstants.NET_QUERYCOURSEBYID, new CallBack<SingleGradeInfo>() {
            @Override
            public void onSimpleFailure(int error, String errorMsg) {
                mActBase.onResponseFailure(error, errorMsg);
            }

            @Override
            public void onSimpleSuccessList(List<SingleGradeInfo> result) {
                // adapter.upDateList(result);
            }
        });
    }

    @Override
    public Map<String, String> getParams() {
        Map<String, String> params = getSignParams();
        params.put("id", id + "");
        params.put("pagesize", 10000 + "");
        params.put("skip", 0 + "");
        return params;
    }

    @Override
    public Class<SingleGradeInfo> getGsonClass() {
        return SingleGradeInfo.class;
    }
}

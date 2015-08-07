package com.peoit.android.online.pschool.entity;

import com.peoit.android.online.pschool.config.CommonUtil;

import java.io.Serializable;
import java.util.HashMap;

/**
 * author:libo
 * time:2015/7/27
 * E-mail:boli_android@163.com
 * last: ...
 */
public class ListPageStat implements Serializable {

    /**
     * message : 登陆成功
     * obj : null
     * code : 0
     * success : true
     */
    private final int DEF_PAGESIZE;

    private int pagesize = 10;
    private int skip = 0;
    private HashMap<String, String> params;

    public ListPageStat() {
        this.DEF_PAGESIZE = 10;
    }

    public ListPageStat(int pagesize, int skip) {
        this.DEF_PAGESIZE = pagesize < 5 ? 5 : pagesize;
        this.pagesize = pagesize < 5 ? 5 : pagesize;
        this.skip = skip < 0 ? 0 : skip;
    }

    public void loadData(){
        if (params != null && !params.isEmpty()){
            params.put("pagesize", pagesize + "");
            params.put("skip", "0");
        } else {
            String sign = CommonUtil.getUser_sign();
            String name = CommonUtil.getUser_name();
            params = new HashMap<>();
            params.put("userno", name);
            params.put("sign", sign);
            params.put("pagesize", pagesize + "");
            params.put("skip", skip + "");
        }
    }

    public void loadMore(){
        params.put("pagesize", pagesize + "");
        params.put("skip", skip + "");
    }

    public HashMap<String, String> getParams() {
        return params;
    }

    public void toSkipLoadMore(int currentPage){
        if (currentPage == pagesize)
            skip += pagesize;
    }
}

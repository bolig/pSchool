package com.peoit.android.online.pschool.net;

import android.text.TextUtils;

import com.android.volley.Request;
import com.google.gson.Gson;
import com.peoit.android.online.pschool.EntityBase;
import com.peoit.android.online.pschool.PresenterNetBase;
import com.peoit.android.online.pschool.config.NetConstants;


/**
 * 请求辅助类
 *
 * author:libo
 * time:2015/7/27
 * E-mail:boli_android@163.com
 * last: ...
 */
public class RequestOptions {

    private RequestModel mModel = RequestModel.ENTITY;
    private int mMethod = Request.Method.POST;

    private String url;
    private Gson mGson;
    private PresenterNetBase mPresenterNetBase;
    private Class<? extends EntityBase> mClazz;

    public  RequestOptions(String url, Class<? extends EntityBase> mClazz, PresenterNetBase mPresenterNetBase) {
        if (TextUtils.isEmpty(url))
            throw new NullPointerException(" @libo url is null");
        if (mPresenterNetBase == null)
            throw new NullPointerException(" @libo PresenterNetBase is null");
        if (mClazz == null)
            throw new NullPointerException(" @libo Calss is null");
        resolveUrl(url);
        this.mClazz = mClazz;
        this.mPresenterNetBase = mPresenterNetBase;
    }

    /**
     * 解析拼接数据请求
     *
     * @param url
     */
    private void resolveUrl(String url) {
        if (!url.contains(NetConstants.URL_BRIDGE))
            this.url = url;
        else {
            String[] urlParam = url.split(NetConstants.URL_BRIDGE);
            if (urlParam.length == 2){
                if (urlParam[0].startsWith("http")){
                    this.url = urlParam[0];
                    this.mModel = getRequestModel(urlParam[1]);
                } else if (urlParam[1].startsWith("http")) {
                    this.url = urlParam[1];
                    this.mMethod = Integer.valueOf(urlParam[0]);
                }
            } else if (urlParam.length == 3){
                this.url = urlParam[1];
                this.mMethod = Integer.valueOf(urlParam[0]);
                this.mModel = getRequestModel(urlParam[2]);
            }
        }
    }

    /**
     * 通过判断拼接数据请求，来获取数据返回是类型
     *
     * @param s
     * @return
     */
    private RequestModel getRequestModel(String s) {
        if (NetConstants.REQMODEL_ENTITY.equals(s))
            return RequestModel.ENTITY;
        else if (NetConstants.REQMODEL_ENTITYLIST.equals(s))
            return RequestModel.ENTITYLIST;
        else if (NetConstants.REQMODEL_ENTITYLISTPAGE.equals(s))
            return RequestModel.ENTITYLISTPAGE;
        throw new RuntimeException("网络请求组装错误...");
    }

    public Gson getmGson() {
        return mGson;
    }

    public void setmGson(Gson mGson) {
        this.mGson = mGson;
    }

    public PresenterNetBase getmPresenterNetBase() {
        return mPresenterNetBase;
    }

    public Class<? extends EntityBase>  getmClazz() {
        return mClazz;
    }

    public void setmClazz(Class<? extends EntityBase> mClazz) {
        this.mClazz = mClazz;
    }

    public RequestModel getmModel() {
        return mModel;
    }

    public void setmModel(RequestModel mModel) {
        this.mModel = mModel;
    }

    public int getmMethod() {
        return mMethod;
    }

    public void setmMethod(int mMethod) {
        this.mMethod = mMethod;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

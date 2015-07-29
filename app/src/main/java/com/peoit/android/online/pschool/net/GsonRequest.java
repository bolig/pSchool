package com.peoit.android.online.pschool.net;

import com.android.volley.AuthFailureError;
import com.android.volley.RetryPolicy;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.peoit.android.online.pschool.EntityBase;
import com.peoit.android.online.pschool.PresenterNetBase;
import com.peoit.android.online.pschool.config.Error;
import com.peoit.android.online.pschool.entity.BaseEntity;
import com.peoit.android.online.pschool.entity.BaseListEntity;
import com.peoit.android.online.pschool.entity.BaseListPageEntity;
import com.peoit.android.online.pschool.utils.MyLogger;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * 自定义Volley请求,
 *
 * author:libo
 * time:2015/7/9
 * E-mail:boli_android@163.com
 * last: ...
 */
public class GsonRequest<T extends EntityBase> extends StringRequest {
    private RequestOptions mOptions;
    private PresenterNetBase mPresenterNetBase;
    private Gson mGson;
    private Class<T> mClazz;
    private CallBack<T> mCallBack;

    public GsonRequest(RequestOptions mOptions, CallBack<T> callBack) {
        super(mOptions.getmMethod(), mOptions.getUrl(), callBack, callBack);
        this.mOptions = mOptions;
        this.mClazz = (Class<T>) mOptions.getmClazz();
        this.mGson = mOptions.getmGson() == null ? new Gson() : mOptions.getmGson();
        this.mCallBack = callBack;
        this.mPresenterNetBase = mOptions.getmPresenterNetBase();
    }

    /**
     * 自定义Gson
     *
     * @return
     */
    public synchronized Gson getGson() {
        if (mGson == null) {
            if (mPresenterNetBase != null && mPresenterNetBase.getCustomGson() != null)
                mGson = mPresenterNetBase.getCustomGson();
            else
                mGson = new Gson();
        }
        return mGson;
    }

    @Override
    public final RetryPolicy getRetryPolicy() {
        return mPresenterNetBase.getRetryPolicy() == null ? super.getRetryPolicy() : mPresenterNetBase.getRetryPolicy();
    }

    @Override
    protected final Map<String, String> getParams() throws AuthFailureError {
        Map<String, String> params = mPresenterNetBase.getParams();
        MyLogger.i("getParams() -----> " + (params == null ? "null" : params.toString()));
        return (params == null || params.isEmpty()) ? super.getParams() : params;
    }

    @Override
    public final Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> headers = mPresenterNetBase.getHeaders();
        MyLogger.i("getHeaders() ----> " + ((headers == null || headers.isEmpty()) ? "null..." : headers.toString()));
        return (headers == null || headers.isEmpty()) ? super.getHeaders() : headers;
    }

    @Override
    protected void deliverResponse(String response) {
        if (mCallBack == null)
            return;
        mCallBack.onFinish();
        RequestModel mModel = mOptions.getmModel();
        switch (mModel) {
            case ENTITY:
                getEntity(response);
                break;
            case ENTITYLIST:
                getEntityList(response);
                break;
            case ENTITYLISTPAGE:
                getEntityListPage(response);
                break;
        }
    }

    /**
     * 通过Gson解析网络返回json
     * 获取实体集合，并有分页...
     *
     * @param response
     */
    private void getEntityListPage(String response) {
        BaseListPageEntity<T> parseJson3 = getGson().fromJson(response, getType(BaseEntity.class, mClazz));
        if (parseJson3 != null){
            if (parseJson3.isSuccess()){
                mCallBack.onSimpleSuccess(parseJson3.getObj());
            } else {
                mCallBack.onSimpleFailure(parseJson3.getCode(), parseJson3.getMessage());
            }
        } else {
            mCallBack.onSimpleFailure(Error.GSON_ERROR1, "Gson 解析异常...");
        }
    }

    /**
     * 通过Gson解析网络返回json
     * 获取实体集合...
     *
     * @param response
     */
    public BaseListEntity<T> getEntityList(String response) {
        BaseListEntity<T> parseJson2 = getGson().fromJson(response, getType(BaseListEntity.class, mClazz));
        if (parseJson2 != null){
            if (parseJson2.isSuccess()){
                mCallBack.onSimpleSuccess(parseJson2.getObj());
            } else {
                mCallBack.onSimpleFailure(parseJson2.getCode(), parseJson2.getMessage());
            }
        } else {
            mCallBack.onSimpleFailure(Error.GSON_ERROR1, "Gson 解析异常...");
        }
        return parseJson2;
    }

    /**
     * 通过Gson解析网络返回json
     * 获取单个实体...
     *
     * @param response
     */
    public void getEntity(String response) {
        BaseEntity<T> parseJson1 = getGson().fromJson(response, getType(BaseEntity.class, mClazz));
        if (parseJson1 != null){
            if (parseJson1.isSuccess()){
                mCallBack.onSimpleSuccess(parseJson1.getObj());
            } else {
                mCallBack.onSimpleFailure(parseJson1.getCode(), parseJson1.getMessage());
            }
        } else {
            mCallBack.onSimpleFailure(Error.GSON_ERROR1, "Gson 解析异常...");
        }
    }

    /**
     * 获取解析实体类型,用于Gson解析...
     *
     * @param baseClass
     * @param mClazz
     * @return
     */
    private <U> Type getType(final Class<U> baseClass, final Class... mClazz) {
        return new ParameterizedType() {
            public Type getRawType() {
                return baseClass;
            }

            public Type[] getActualTypeArguments() {
                return mClazz;
            }

            public Type getOwnerType() {
                return null;
            }
        };
    }
}

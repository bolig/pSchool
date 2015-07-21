package com.peoit.android.online.pschool.ui.Base;

import android.app.Application;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.peoit.android.online.pschool.config.CommonUtil;

import java.util.Map;

/**
 * author:libo
 * time:2015/7/9
 * E-mail:boli_android@163.com
 * last: ...
 */
public class PsApplication extends Application{

    private static PsApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        CommonUtil.initContext(mInstance);
        StringRequest request = new StringRequest("", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return super.getParams();
            }
        };
    }

    public static PsApplication getInstance(){
        return mInstance;
    }
}

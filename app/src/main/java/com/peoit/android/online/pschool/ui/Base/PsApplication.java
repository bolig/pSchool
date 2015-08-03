package com.peoit.android.online.pschool.ui.Base;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.easemob.chatuidemo.DemoApplication;
import com.easemob.chatuidemo.utils.CommonUtils;
import com.peoit.android.online.pschool.config.CommonUtil;
import com.peoit.android.online.pschool.ui.activity.HomeActivity;

import java.util.Map;

/**
 * author:libo
 * time:2015/7/9
 * E-mail:boli_android@163.com
 * last: ...
 */
public class PsApplication extends DemoApplication{//DemoApplication为聊天de
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

     @Override
    public void notifyMainActivity(){
         Log.i("notifyMainActivity", "notifyMainActivity");
         //Log.i("notifyMainActivity", CommonUtils.getTopActivity(HomeActivity.instance));
         if (CommonUtils.getTopActivity(HomeActivity.instance).equals(HomeActivity.class.getName())) {
             HomeActivity.instance.onResume();
         }
    }

    public String currUser = "";

    public void setNickName(String currentNikeName) {
        currUser = currentNikeName;
    }

}

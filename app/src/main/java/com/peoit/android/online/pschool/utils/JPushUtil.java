package com.peoit.android.online.pschool.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.peoit.android.online.pschool.config.CommonUtil;
import com.peoit.android.online.pschool.config.Constants;

import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

public class JPushUtil {
    private static final String TAG = "JPushUtil";

    public static void init() {
        Log.i(TAG, "开启推送");
        JPushInterface.init(CommonUtil.mContext);
    }

    public static void onPause() {
        JPushInterface.onPause(CommonUtil.mContext);
    }

    public static void onResume() {
        JPushInterface.onResume(CommonUtil.mContext);
    }

    public static void stop() {
        JPushInterface.stopPush(CommonUtil.mContext);
    }

    private static final int SEND_TAG = 100;
    private static final int SEND_ALIAS = 101;

    private static Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            Context mContext = CommonUtil.mContext;
            int what = msg.what;
            if (mContext == null)
                return;
            if (!NetWorkHelper.isNetworkAvailable(mContext))
                return;
            switch (what) {
                case SEND_TAG:
                    Set<String> tags = (Set<String>) msg.obj;
                    JPushInterface.setTags(mContext, tags, mTagsCallback);
                    break;
                case SEND_ALIAS:
                    String alias = (String) msg.obj;
                    JPushInterface.setAlias(mContext, alias, mAliasCallback);
                    break;
            }
        }

        ;
    };

    private static final TagAliasCallback mAliasCallback = new TagAliasCallback() {
        @Override
        public void gotResult(int code, String alias, Set<String> tags) {
            String logs;
            switch (code) {
                case 0:
                    logs = "Set tag and alias success";
                    Log.i(TAG, logs);
                    ShareUserHelper.getInstance().put(Constants.JPUSH_SET_ALIAS, true);
                    break;
                case 6002:
                    logs = "Failed to set alias and tags due to timeout. Try again after 60s.";
                    Log.i(TAG, logs);
                    handler.sendMessageDelayed(Message.obtain(handler, SEND_ALIAS, alias), 60 * 1000);
                    break;
            }
        }
    };

    private static final TagAliasCallback mTagsCallback = new TagAliasCallback() {
        @Override
        public void gotResult(int code, String alias, Set<String> tags) {
            String logs;
            switch (code) {
                case 0:
                    ShareUserHelper.getInstance().put(Constants.JPUSH_SET_TAGS, true);
                    break;
                case 6002:
                    handler.sendMessageDelayed(
                            handler.obtainMessage(SEND_TAG, tags),
                            1000 * 60);
                    break;
            }
        }
    };

    public static void setAlias(String alias) {
        handler.sendMessage(Message.obtain(handler, SEND_ALIAS, alias));
    }

    public static void setTags(Set<String> tags) {
        handler.sendMessage(Message.obtain(handler, SEND_TAG, tags));
    }
}

package com.peoit.android.online.pschool.ui.Base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * author:libo
 * time:2015/7/9
 * E-mail:boli_android@163.com
 * last: ...
 */
public class CommonUtil {
    private static DisplayMetrics metrics;

    public static Context mContext;
    public static int w_screeen;
    public static int h_screeen;

    public static LinearLayout.LayoutParams PARAM_MP_WC = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT);

    public static LinearLayout.LayoutParams PARAM_WC_WC = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT);

    public static LinearLayout.LayoutParams PARAM_WC_MP = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.MATCH_PARENT);

    public static LinearLayout.LayoutParams PARAM_MP_MP = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT);

    public  static int VISIBLE = View.VISIBLE;

    public  static int INVISIBLE = View.INVISIBLE;

    public  static int GONE = View.GONE;

    /**
     * 配置全局Context
     *
     * @param context
     */
    public static void initContext(@NonNull Context context) {
        mContext = context;
        metrics = getApplicationContext().getResources().getDisplayMetrics();
        w_screeen = metrics.widthPixels;
        h_screeen = metrics.heightPixels;
    }

    /**
     * 获取系统ApplicationContext
     *
     * @return
     */
    public static Context getApplicationContext() {
        return mContext.getApplicationContext();
    }

    /**
     * 显示Toast
     *
     * @param msg
     */
    public static void showToast(@NonNull String msg) {
        if (!TextUtils.isEmpty(msg))
            Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * dp转换为px
     *
     * @param context
     * @param dpValue
     * @return
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        //MyLogger.i(scale+"");
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * px装换成dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * sp转换为px
     */
    public static int sp2px(Context context, float spValue) {
        final float scale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * scale + 0.5f);
    }

    /**
     * px转换为sp
     */
    public static int px2sp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / scale + 0.5f);
    }
}

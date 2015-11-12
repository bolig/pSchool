package com.peoit.android.online.pschool.ui.Presenter;

import android.graphics.Bitmap;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.peoit.android.online.pschool.ActBase;
import com.peoit.android.online.pschool.config.CommonUtil;
import com.peoit.android.online.pschool.config.NetConstants;
import com.peoit.android.online.pschool.entity.QueryNoallotInfo;
import com.peoit.android.online.pschool.ui.Base.BasePresenter;
import com.peoit.android.online.pschool.utils.BitmapUtils;
import com.peoit.android.online.pschool.utils.MyLogger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * author:libo
 * time:2015/10/13
 * E-mail:boli_android@163.com
 * last: ...
 */
public class UploadImgPresenter extends BasePresenter {

    private Map<String, String> requestParams;

    public UploadImgPresenter(ActBase actBase) {
        super(actBase);
    }

    @Override
    public Map<String, String> getHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "multipart/form-data");
        return null;
    }

    @Override
    public Map<String, String> getParams() {
        return requestParams;
    }

    @Override
    public Class getGsonClass() {
        return null;
    }

    /**
     * 上传图片
     *
     * @param filePath
     * @param area
     * @param title
     * @param abs
     */
    public void uploadImg(String filePath, String area, String title, String abs) throws FileNotFoundException {
        String sign = CommonUtil.getUser_sign();
        String name = CommonUtil.getUser_name();
        RequestParams params = new RequestParams();
        params.put("userno", name);
        params.put("sign", sign);
        params.put("img", getFile(filePath));
        params.put("area", area);
        params.put("title", title);
        params.put("abs", abs);
        mActBase.showLoadingDialog("正在上传图片...");
        new AsyncHttpClient().post(NetConstants.NET_ADDIMG, params, new AsyncHttpResponseHandler() {

            @Override
            public void onProgress(long bytesWritten, long totalSize) {
                mActBase.showLoadingDialog("上传进度: " + getProgress(bytesWritten, totalSize) + "/100");
            }

            @Override
            public void onFinish() {
                mActBase.hideLoadingDialog();
            }

            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody) {
                String result = getResponseString(responseBody, DEFAULT_CHARSET);
                QueryNoallotInfo parseJson3 = new Gson().fromJson(result, QueryNoallotInfo.class);
                if (parseJson3 != null) {
                    if (parseJson3.isSuccess()) {
                        mActBase.showToast("上传成功");
                        mActBase.getActivity().finish();
                    } else {
                        mActBase.showToast("上传失败");
                    }
                } else {
                    mActBase.showToast("上传失败");
                }
            }

            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody, Throwable error) {
                mActBase.onResponseFailure(statusCode, "");
                mActBase.showToast("上传失败");
            }
        });
    }

    private File getFile(String filePath) {
        File file = new File(filePath);
        long fileSize = file.length();
        MyLogger.e("file size = " + fileSize);
        if (fileSize > 50l * 1024l) {
            Bitmap bitmap = BitmapUtils.commpressBitmap(filePath, fileSize);
            BitmapUtils.saveBitmap(bitmap);
            file = new File(BitmapUtils.FILE_PATH);
        }
        return file;
    }

    private String getProgress(long bytesWritten, long totalSize) {
        return (bytesWritten / totalSize * 100) + "";
    }

    public static final String UTF8_BOM = "\uFEFF";
    public static final String DEFAULT_CHARSET = "UTF-8";

    /**
     * Attempts to encode response bytes as string of set encoding
     *
     * @param charset     charset to create string with
     * @param stringBytes response bytes
     * @return String of set encoding or null
     */
    public static String getResponseString(byte[] stringBytes, String charset) {
        try {
            String toReturn = (stringBytes == null) ? null : new String(stringBytes, charset);
            if (toReturn != null && toReturn.startsWith(UTF8_BOM)) {
                return toReturn.substring(1);
            }
            MyLogger.e(" --- response string --- " + toReturn);
            return toReturn;
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }
}

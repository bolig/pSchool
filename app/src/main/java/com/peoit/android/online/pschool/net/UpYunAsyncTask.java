package com.peoit.android.online.pschool.net;

import android.os.AsyncTask;
import android.text.TextUtils;

import com.peoit.android.online.pschool.config.CommonUtil;
import com.peoit.android.online.pschool.entity.ProgressInfo;
import com.peoit.android.online.pschool.exception.IllegalParamException;
import com.upyun.block.api.listener.CompleteListener;
import com.upyun.block.api.listener.ProgressListener;
import com.upyun.block.api.main.UploaderManager;
import com.upyun.block.api.utils.UpYunUtils;

import java.io.File;
import java.util.Map;

/**
 * author:libo
 * time:2015/8/31
 * E-mail:boli_android@163.com
 * last: ...
 */
public class UpYunAsyncTask extends AsyncTask<String, ProgressInfo, String> {

    private static final String PARAMS_ERROR = "PARAMS_ERROR";
    private static final String FILE_ERROR = "FILE_ERROR";
    private static final String UPLOAD_ERROR = "FILE_ERROR";

    // 空间名
    private static final String bucket = "gztrwx-video";
    // 表单密钥
    private static final String formApiSecret = "3eQeRrYNFTVp3UIwYjDAHI3Syfs=";
    // 上传路径
    private static final String NET_HOST = "gztrwx-video.b0.upaiyun.com";
    // 保存到又拍云的路径
    private String savePath;

    private OnProgressListener mListener;

    public void setmListener(OnProgressListener mListener) {
        this.mListener = mListener;
    }

    @Override
    protected String doInBackground(String[] params) {
        String localFilePath = params[0];
        if (TextUtils.isEmpty(localFilePath)) {
            return PARAMS_ERROR;
        }

        File localFile = new File(localFilePath);

        if (localFile == null || !localFile.exists()) {
            return FILE_ERROR;
        }
        try {
                /*
                 * 设置进度条回掉函数
				 *
				 * 注意：由于在计算发送的字节数中包含了图片以外的其他信息，最终上传的大小总是大于图片实际大小，
				 * 为了解决这个问题，代码会判断如果实际传送的大小大于图片
				 * ，就将实际传送的大小设置成'fileSize-1000'（最小为0）
				 */
            ProgressListener progressListener = new ProgressListener() {
                @Override
                public void transferred(long transferedBytes, long totalBytes) {
                    publishProgress(getProgress(transferedBytes, totalBytes, null, null, false, false));
                }
            };

            CompleteListener completeListener = new CompleteListener() {
                @Override
                public void result(boolean isComplete, String result, String error) {
                    publishProgress(getProgress(0l, 0l, result, error, isComplete, true));
                }
            };

            UploaderManager uploaderManager = UploaderManager.getInstance(bucket);
            uploaderManager.setConnectTimeout(60);
            uploaderManager.setResponseTimeout(60);

            savePath = "/" + CommonUtil.getUser_name() + System.currentTimeMillis() + ".mp4";

            Map<String, Object> paramsMap = uploaderManager.fetchFileInfoDictionaryWith(localFile, savePath);
            //还可以加上其他的额外处理参数...
            paramsMap.put("return_url", "http://httpbin.org/get");
            // signature & policy 建议从服务端获取
            String policyForInitial = UpYunUtils.getPolicy(paramsMap);
            String signatureForInitial = UpYunUtils.getSignature(paramsMap, formApiSecret);
            uploaderManager.upload(policyForInitial, signatureForInitial, localFile, progressListener, completeListener);
        } catch (Exception e) {
            e.printStackTrace();
            return UPLOAD_ERROR;
        }
        return savePath;
    }

    private ProgressInfo getProgress(long transferedBytes, long totalBytes, String result, String error, boolean iscomplate, boolean isSuccess) {
        ProgressInfo info = new ProgressInfo();
        info.setCurProgress(transferedBytes);
        info.setTotalProgress(totalBytes);
        info.setResult(result);
        info.setError(error);
        info.setIsSuccess(isSuccess);
        info.setIsComplate(iscomplate);
        return info;
    }


    @Override
    protected void onProgressUpdate(ProgressInfo... values) {
        ProgressInfo info = values[0];
        if (mListener == null) {
            return;
        }
        if (info.isSuccess()) {
            mListener.onSuccess(info.isComplate(), info.getResult(), info.getError());
        } else {
            mListener.onProgresss(info.getCurProgress(), info.getTotalProgress());
        }
    }

    @Override
    protected void onPostExecute(String result) {
        if (PARAMS_ERROR.equals(result)) {
            throw new IllegalParamException(" @libo params is error ");
        } else if (FILE_ERROR.equals(result)) {
            throw new IllegalParamException(" @libo file is not exists ");
        } else if (UPLOAD_ERROR.equals(result)) {
            throw new IllegalParamException(" @libo upload failure ");
        }
    }

    public interface OnProgressListener {

        void onProgresss(long curProgress, long totalProgress);

        void onSuccess(boolean isComplete, String result, String error);
    }
}

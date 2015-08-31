package com.peoit.android.online.pschool.entity;

import java.io.Serializable;

/**
 * author:libo
 * time:2015/8/31
 * E-mail:boli_android@163.com
 * last: ...
 */
public class ProgressInfo implements Serializable {

    private long curProgress = 0;
    private long totalProgress = -1;
    private String result;
    private String error;
    private boolean isComplate = false;
    private boolean isSuccess = false;

    public boolean isComplate() {
        return isComplate;
    }

    public void setIsComplate(boolean isComplate) {
        this.isComplate = isComplate;
    }

    public long getCurProgress() {
        return curProgress;
    }

    public void setCurProgress(long curProgress) {
        this.curProgress = curProgress;
    }

    public long getTotalProgress() {
        return totalProgress;
    }

    public void setTotalProgress(long totalProgress) {
        this.totalProgress = totalProgress;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    @Override
    public String toString() {
        return "ProgressInfo{" +
                "curProgress=" + curProgress +
                ", totalProgress=" + totalProgress +
                ", result='" + result + '\'' +
                ", error='" + error + '\'' +
                ", isSuccess=" + isSuccess +
                '}';
    }
}

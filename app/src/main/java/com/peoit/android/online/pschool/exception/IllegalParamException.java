package com.peoit.android.online.pschool.exception;

/**
 * author:libo
 * time:2015/8/31
 * E-mail:boli_android@163.com
 * last: ...
 */
public class IllegalParamException extends RuntimeException {

    public IllegalParamException(String detailMessage) {
        super(detailMessage);
    }

    public IllegalParamException() {

    }

    public IllegalParamException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public IllegalParamException(Throwable throwable) {
        super(throwable);
    }

    @Override
    public void printStackTrace() {
        super.printStackTrace();
    }
}

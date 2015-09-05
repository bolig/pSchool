package com.peoit.android.online.pschool.entity;

import com.peoit.android.online.pschool.EntityBase;

import java.io.Serializable;

/**
 * Created by zyz on 2015/9/2.
 */
public class HomeZhuanJiaInfo implements Serializable ,EntityBase {


    /**
     * code : 0
     * success : true
     * obj : 40
     * obj1 : null
     * message : 成功
     */
    private int code;
    private boolean success;
    private int obj;
    private String obj1;
    private String message;

    @Override
    public boolean isNull() {
        return false;
    }

    @Override
    public boolean match() {
        return false;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setObj(int obj) {
        this.obj = obj;
    }

    public void setObj1(String obj1) {
        this.obj1 = obj1;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public boolean isSuccess() {
        return success;
    }

    public int getObj() {
        return obj;
    }

    public String getObj1() {
        return obj1;
    }

    public String getMessage() {
        return message;
    }
}

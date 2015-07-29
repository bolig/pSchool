package com.peoit.android.online.pschool.entity;

import com.peoit.android.online.pschool.EntityBase;

import java.io.Serializable;
import java.util.List;

/**
 * author:libo
 * time:2015/7/27
 * E-mail:boli_android@163.com
 * last: ...
 */
public class ListPageStat<T extends EntityBase> implements Serializable, EntityBase {
    /**
     * message : 登陆成功
     * obj : null
     * code : 0
     * success : true
     */
    private String message;

    private int code = -1;

    private boolean success = false;

    private List<T> obj;

    @Override
    public boolean isNull() {
        return obj == null || obj.size() == 0;
    }

    @Override
    public boolean match() {
        return false;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<T> getObj() {
        return obj;
    }

    public void setObj(List<T> obj) {
        this.obj = obj;
    }

    @Override
    public String toString() {
        return "BaseListEntity{" +
                "message='" + message + '\'' +
                ", code=" + code +
                ", success=" + success +
                ", obj=" + obj +
                '}';
    }
}

package com.peoit.android.online.pschool.entity;

import com.peoit.android.online.pschool.EntityBase;

import java.io.Serializable;

/**
 * 考试列表
 * author:libo
 * time:2015/8/9
 * E-mail:boli_android@163.com
 * last: ...
 */
public class GradeStatInfo implements Serializable, EntityBase {

    /**
     * id : 4
     * startdateStr : 2015-07-08
     * name : 2015年中期考试
     * type : 3
     */
    private long id;
    private String startdateStr;
    private String name;
    private String type;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GradeStatInfo)) return false;

        GradeStatInfo info = (GradeStatInfo) o;

        return id == info.id;

    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public boolean isNull() {
        return false;
    }

    @Override
    public boolean match() {
        return false;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStartdateStr(String startdateStr) {
        this.startdateStr = startdateStr;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public String getStartdateStr() {
        return startdateStr;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "GradeStatInfo{" +
                "id=" + id +
                ", startdateStr='" + startdateStr + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}

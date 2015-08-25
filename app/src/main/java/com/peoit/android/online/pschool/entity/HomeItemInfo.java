package com.peoit.android.online.pschool.entity;

import com.peoit.android.online.pschool.EntityBase;

import java.io.Serializable;

/**
 * author:libo
 * time:2015/8/25
 * E-mail:boli_android@163.com
 * last: ...
 */
public class HomeItemInfo implements Serializable, EntityBase {

    private int res_icon;

    private String title;

    private boolean isAddMark;

    public HomeItemInfo(int res_icon, String title, boolean isAddMark) {
        this.res_icon = res_icon;
        this.title = title;
        this.isAddMark = isAddMark;
    }

    public int getRes_icon() {
        return res_icon;
    }

    public void setRes_icon(int res_icon) {
        this.res_icon = res_icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isAddMark() {
        return isAddMark;
    }

    public void setIsAddMark(boolean isAddMark) {
        this.isAddMark = isAddMark;
    }


    @Override
    public String toString() {
        return "HomeItemInfo{" +
                "res_icon=" + res_icon +
                ", title='" + title + '\'' +
                ", isAddMark=" + isAddMark +
                ", mark_count=" +
                '}';
    }

    @Override
    public boolean isNull() {
        return false;
    }

    @Override
    public boolean match() {
        return false;
    }
}

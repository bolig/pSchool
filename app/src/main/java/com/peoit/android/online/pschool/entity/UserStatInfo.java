package com.peoit.android.online.pschool.entity;

import com.peoit.android.online.pschool.EntityBase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * author:libo
 * time:2015/10/22
 * E-mail:boli_android@163.com
 * last: ...
 */
public class UserStatInfo implements Serializable, EntityBase {
    private String title;
    private List<UserInfo> userInfos = new ArrayList<>();

    public UserStatInfo(List<UserInfo> userInfos, String title) {
        this.userInfos = userInfos;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<UserInfo> getUserInfos() {
        return userInfos;
    }

    public void setUserInfos(List<UserInfo> userInfos) {
        this.userInfos = userInfos;
    }

    @Override
    public String toString() {
        return "UserStatInfo{" +
                "title='" + title + '\'' +
                ", userInfos=" + userInfos +
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

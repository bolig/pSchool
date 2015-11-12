package com.peoit.android.online.pschool.entity;

import com.peoit.android.online.pschool.EntityBase;

import java.io.Serializable;

/**
 * author:libo
 * time:2015/10/13
 * E-mail:boli_android@163.com
 * last: ...
 */
public class ExpertListInfo implements Serializable, EntityBase {
    /**
     * id : 1
     * phone : 13910768418
     * username : dls
     * nickname : 段老师
     * zjpic :
     * zjjj : 简介
     */
    private String id;
    private String phone;
    private String username;
    private String nickname;
    private String zjpic;
    private String zjjj;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExpertListInfo)) return false;

        ExpertListInfo info = (ExpertListInfo) o;

        return !(id != null ? !id.equals(info.id) : info.id != null);

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public boolean isNull() {
        return false;
    }

    @Override
    public boolean match() {
        return false;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setZjpic(String zjpic) {
        this.zjpic = zjpic;
    }

    public void setZjjj(String zjjj) {
        this.zjjj = zjjj;
    }

    public String getId() {
        return id;
    }

    public String getPhone() {
        return phone;
    }

    public String getUsername1() {
        return username;
    }

    public String getNickname() {
        return nickname;
    }

    public String getZjpic() {
        return zjpic;
    }

    public String getZjjj() {
        return zjjj;
    }

    @Override
    public String toString() {
        return "ExpertListInfo{" +
                "id='" + id + '\'' +
                ", phone='" + phone + '\'' +
                ", username='" + username + '\'' +
                ", nickname='" + nickname + '\'' +
                ", zjpic='" + zjpic + '\'' +
                ", zjjj='" + zjjj + '\'' +
                '}';
    }

}

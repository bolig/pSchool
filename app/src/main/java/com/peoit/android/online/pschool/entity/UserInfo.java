package com.peoit.android.online.pschool.entity;

import com.peoit.android.online.pschool.EntityBase;

import java.io.Serializable;

/**
 * 用户属性实体类
 * <p/>
 * author:libo
 * time:2015/7/13
 * E-mail:boli_android@163.com
 * last: ...
 */
public class UserInfo implements Serializable, EntityBase {

    private int id = Integer.MIN_VALUE;

    private int loginType = Integer.MIN_VALUE;

    private String nickName;

    private String phoneNumber;

    private String scalePhoto;

    private boolean payed = false;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLoginType() {
        return loginType;
    }

    public void setLoginType(int loginType) {
        this.loginType = loginType;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getScalePhoto() {
        return scalePhoto;
    }

    public void setScalePhoto(String scalePhoto) {
        this.scalePhoto = scalePhoto;
    }

    public boolean isPayed() {
        return payed;
    }

    public void setPayed(boolean payed) {
        this.payed = payed;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", loginType=" + loginType +
                ", nickName='" + nickName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", scalePhoto='" + scalePhoto + '\'' +
                ", payed=" + payed +
                '}';
    }

    @Override
    public boolean isNull() {
        return id == Integer.MIN_VALUE;
    }

    @Override
    public boolean match() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}

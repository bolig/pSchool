package com.peoit.android.online.pschool.entity;

import com.peoit.android.online.pschool.EntityBase;

import java.io.Serializable;

/**
 * 首页Banner图
 *
 * author:libo
 * time:2015/8/31
 * E-mail:boli_android@163.com
 * last: ...
 */
public class HomeBannerInfo implements Serializable, EntityBase {

    /**
     * id : 1
     * text : 测试1
     *  url :  /gz/upload/20150831005057634.jpg
     */
    private int id = Integer.MIN_VALUE;

    private String text;

    private String url;

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "HomeBannerInfo{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", url='" + url + '\'' +
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
}

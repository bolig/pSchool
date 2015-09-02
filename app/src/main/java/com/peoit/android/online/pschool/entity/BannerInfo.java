package com.peoit.android.online.pschool.entity;

import android.text.TextUtils;

import com.peoit.android.online.pschool.EntityBase;

import java.io.Serializable;

/**
 * author:libo
 * time:2015/9/2
 * E-mail:boli_android@163.com
 * last: ...
 */
public class BannerInfo implements Serializable, EntityBase {

    private int id;
    private String title;
    private String url;
    private String content;
    private String flg;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFlg() {
        return flg;
    }

    public void setFlg(String flg) {
        this.flg = flg;
    }

    @Override
    public String toString() {
        return "BannerInfo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", content='" + content + '\'' +
                ", flg='" + flg + '\'' +
                '}';
    }

    @Override
    public boolean isNull() {
        return id == Integer.MIN_VALUE || TextUtils.isEmpty(content);
    }

    @Override
    public boolean match() {
        return false;
    }
}

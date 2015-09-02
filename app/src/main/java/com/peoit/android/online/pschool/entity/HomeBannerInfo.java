package com.peoit.android.online.pschool.entity;

import com.peoit.android.online.pschool.EntityBase;

import java.io.Serializable;

/**
 * 首页Banner图
 * <p/>
 * author:libo
 * time:2015/8/31
 * E-mail:boli_android@163.com
 * last: ...
 */
public class HomeBannerInfo implements Serializable, EntityBase {

    /**
     * id : 1
     * text : 测试1
     * url :  /gz/upload/20150831005057634.jpg
     */
    private int id = Integer.MIN_VALUE;

    private String url;

    /**
     * content : null
     * title : 新的开始
     * flg : Y
     */
    private String content;
    private String title;
    private String flg;

    public int getId() {
        return id;
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

    @Override
    public boolean isNull() {
        return id == Integer.MIN_VALUE;
    }

    @Override
    public boolean match() {
        return false;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setFlg(String flg) {
        this.flg = flg;
    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }

    public String getFlg() {
        return flg;
    }
}

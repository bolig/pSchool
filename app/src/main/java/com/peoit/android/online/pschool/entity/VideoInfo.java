package com.peoit.android.online.pschool.entity;

import android.text.TextUtils;

import com.peoit.android.online.pschool.EntityBase;

import java.io.Serializable;

/**
 * author:libo
 * time:2015/10/12
 * E-mail:boli_android@163.com
 * last: ...
 */
public class VideoInfo implements Serializable, EntityBase {
    /**
     * id : 1
     * videourl : 视频地址
     * title : 标题
     * abs : 简介
     * imgurl  : 图片截图
     */
    private String id;
    private String videourl;
    private String title;
    private String abs;
    private String imgurl;

    public void setId(String id) {
        this.id = id;
    }

    public void setVideourl(String videourl) {
        this.videourl = videourl;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAbs(String abs) {
        this.abs = abs;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getId() {
        return id;
    }

    public String getVideourl() {
        return videourl;
    }

    public String getTitle() {
        return title;
    }

    public String getAbs() {
        return abs;
    }

    public String getImgurl() {
        return imgurl;
    }

    @Override
    public String toString() {
        return "VideoInfo{" +
                "id='" + id + '\'' +
                ", videourl='" + videourl + '\'' +
                ", title='" + title + '\'' +
                ", abs='" + abs + '\'' +
                ", imgurl='" + imgurl + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VideoInfo)) return false;

        VideoInfo videoInfo = (VideoInfo) o;

        return id.equals(videoInfo.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean isNull() {
        return TextUtils.isEmpty(id);
    }

    @Override
    public boolean match() {
        return false;
    }
}

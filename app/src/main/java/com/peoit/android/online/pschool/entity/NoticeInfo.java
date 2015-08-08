package com.peoit.android.online.pschool.entity;

import com.google.gson.annotations.Expose;
import com.peoit.android.online.pschool.EntityBase;

import java.io.Serializable;

/**
 * 通知消息
 * <p>
 * author:libo
 * time:2015/8/7
 * E-mail:boli_android@163.com
 * last: ...
 */
public class NoticeInfo implements Serializable, EntityBase {

    /**
     * content : null
     * id : 1
     * title : 标题标题标题标题
     * type : back
     * stimeStr : 2015-08-01 12:00
     */
    private String content;
    private int id;
    private String title;
    private String type;
    private String stimeStr;

    @Expose(serialize = false)
    private boolean isAdd = false;

    public NoticeInfo() {

    }

    public NoticeInfo(String title, String content, String stimeStr) {
        this.stimeStr = stimeStr;
        this.title = title;
        this.content = content;
    }

    @Override
    public boolean isNull() {
        return false;
    }

    @Override
    public boolean match() {
        return false;
    }

    public boolean isAdd() {
        return isAdd;
    }

    public void setIsAdd(boolean isAdd) {
        this.isAdd = isAdd;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setStimeStr(String stimeStr) {
        this.stimeStr = stimeStr;
    }

    public String getContent() {
        return content;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public String getStimeStr() {
        return stimeStr;
    }

    @Override
    public String toString() {
        return "NoticeInfo{" +
                "content='" + content + '\'' +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", stimeStr='" + stimeStr + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NoticeInfo)) return false;

        NoticeInfo that = (NoticeInfo) o;

        return id == that.id;

    }

    @Override
    public int hashCode() {
        return id;
    }
}

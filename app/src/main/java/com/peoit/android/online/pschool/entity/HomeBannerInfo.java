package com.peoit.android.online.pschool.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.peoit.android.online.pschool.EntityBase;
import com.peoit.android.online.pschool.config.Constants;

import java.io.Serializable;

/**
 * 首页Banner图
 * <p/>
 * author:libo
 * time:2015/8/31
 * E-mail:boli_android@163.com
 * last: ...
 */
@DatabaseTable(tableName = Constants.DB_TAB_HOMEBANNER)
public class HomeBannerInfo implements Serializable, EntityBase {
    /**
     * id : 1
     * text : 测试1
     * url :  /gz/upload/20150831005057634.jpg
     * content : null
     * title : 新的开始
     * flg : Y
     */
    @DatabaseField(generatedId = true)
    private long _id;

    @DatabaseField(canBeNull = false)
    private long id = Integer.MIN_VALUE;

    @DatabaseField(canBeNull = false)
    private String url;

    @DatabaseField(canBeNull = false)
    private String content;

    @DatabaseField(canBeNull = false)
    private String title;

    private String flg;

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public long getId() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HomeBannerInfo)) return false;

        HomeBannerInfo that = (HomeBannerInfo) o;

        return id == that.id;

    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "HomeBannerInfo{" +
                "_id=" + _id +
                ", id=" + id +
                ", url='" + url + '\'' +
                ", content='" + content + '\'' +
                ", title='" + title + '\'' +
                ", flg='" + flg + '\'' +
                '}';
    }
}

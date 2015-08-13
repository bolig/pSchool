package com.peoit.android.online.pschool.entity;

import com.peoit.android.online.pschool.EntityBase;

import java.io.Serializable;

/**
 * 专栏信息
 * <p/>
 * author:libo
 * time:2015/8/3
 * E-mail:boli_android@163.com
 * last: ...
 */
public class FeatureInfo implements Serializable, EntityBase {



    /**
     * uid : 1
     * content : <p>123</p>
     * id : 2
     * title : 123
     * stime : 1438440686000
     * flg : Y
     * sontype : 语文
     * type : 专家在线
     * stimeStr : 2015-08-01 22:51
     */


    private int uid;

    private String content;

    private int id;

    private String title;

    private long stime;

    private String flg;

    private String sontype;

    private String type;

    private String stimeStr;

    private String abs;

    private String imgurl;

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public void setUid(int uid) {
        this.uid = uid;
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

    public void setStime(long stime) {
        this.stime = stime;
    }

    public void setFlg(String flg) {
        this.flg = flg;
    }

    public void setSontype(String sontype) {
        this.sontype = sontype;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setStimeStr(String stimeStr) {
        this.stimeStr = stimeStr;
    }

    public int getUid() {
        return uid;
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

    public long getStime() {
        return stime;
    }

    public String getFlg() {
        return flg;
    }

    public String getSontype() {
        return sontype;
    }

    public String getType() {
        return type;
    }

    public String getStimeStr() {
        return stimeStr;
    }

    public String getAbs() {
        return abs;
    }

    public void setAbs(String abs) {
        this.abs = abs;
    }

    @Override
    public String toString() {
        return "FeatureInfo{" +
                "uid=" + uid +
                ", content='" + content + '\'' +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", stime=" + stime +
                ", flg='" + flg + '\'' +
                ", sontype='" + sontype + '\'' +
                ", type='" + type + '\'' +
                ", stimeStr='" + stimeStr + '\'' +
                ", abs='" + abs + '\'' +
                ", imgurl='" + imgurl + '\'' +
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

package com.peoit.android.online.pschool.entity;

import com.peoit.android.online.pschool.EntityBase;

import java.io.Serializable;

/**
 * author:libo
 * time:2015/7/31
 * E-mail:boli_android@163.com
 * last: ...
 */
public class SchoolDataInfo implements Serializable, EntityBase{

    private String imageUrl = "http://pic25.nipic.com/20121209/9252150_194258033000_2.jpg";
    private String title = "粉红背景图片源文件";
    private String content = "50万张高清图片素材和桌面壁纸免费下载,全部高清无水印!内容涵盖风景图片、动物图片、鲜花图片、家居图片、设计素材、电脑壁纸、动漫壁纸、电影壁纸、明星壁纸...";
    private String time = "2015/7/31 12:12";

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "SchoolDataInfo{" +
                "imageUrl='" + imageUrl + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", time='" + time + '\'' +
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

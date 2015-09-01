package com.peoit.android.online.pschool.entity;

import java.io.Serializable;

/**
 * author:libo
 * time:2015/8/31
 * E-mail:boli_android@163.com
 * last: ...
 */
public class VideoSuccessInfo implements Serializable {

    /**
     * mimetype : video/mp4
     * last_modified : 1441018292
     * file_size : 14816275
     * bucket_name : gztrwx-video
     * path : /5222012002020900301441018224171.mp4
     * signature : fb776505d512ca1cbfec22e431aa0ac7
     */
    private String mimetype;
    private String last_modified;
    private String file_size;
    private String bucket_name;
    private String path;
    private String signature;

    public void setMimetype(String mimetype) {
        this.mimetype = mimetype;
    }

    public void setLast_modified(String last_modified) {
        this.last_modified = last_modified;
    }

    public void setFile_size(String file_size) {
        this.file_size = file_size;
    }

    public void setBucket_name(String bucket_name) {
        this.bucket_name = bucket_name;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getMimetype() {
        return mimetype;
    }

    public String getLast_modified() {
        return last_modified;
    }

    public String getFile_size() {
        return file_size;
    }

    public String getBucket_name() {
        return bucket_name;
    }

    public String getPath() {
        return path;
    }

    public String getSignature() {
        return signature;
    }

    @Override
    public String toString() {
        return "VideoSuccessInfo{" +
                "mimetype='" + mimetype + '\'' +
                ", last_modified='" + last_modified + '\'' +
                ", file_size='" + file_size + '\'' +
                ", bucket_name='" + bucket_name + '\'' +
                ", path='" + path + '\'' +
                ", signature='" + signature + '\'' +
                '}';
    }
}

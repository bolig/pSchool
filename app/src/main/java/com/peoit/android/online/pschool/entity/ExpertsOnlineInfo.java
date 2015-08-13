package com.peoit.android.online.pschool.entity;

import com.peoit.android.online.pschool.EntityBase;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/8/12.
 */
public class ExpertsOnlineInfo implements Serializable, EntityBase {
    /*{
            "id":69,
            "text":"十分丰富",
            "type":"Q", -- Q代表用户的提问 R代表专家的回复
            "stime":1439347204000,
            "pid":0,
            "nid":40,
            "userno":"522201200207020023",-- 用户登录账户
            "stimeStr":"2015-08-12 10:40",-- 发布时间
            "state":"N",-- Y 专家已经回复 N 专家未回复或者有新的问题
            "username":"时美玉", --提问或者回复人的名字
            "usertype":"2",--提问或者回复人的名字类型  1老师 2 家长 3专家
            "dis":[],-- 本条提问的专家回复和用户在这条提问中追加的提问，属性含义和提问一致{"id":3,"text":"测试3","pid":2,"userno":"522201200202090030","username":"晏泰峰","usertype":"2","stimeStr":"2015-08-02 17:53"}
            "flg":"Y"
    }*/

    private String id;
    private String text;
    private String type;
    private String stime;
    private String pid;
    private String nid;
    private String userno;
    private String stimeStr;
    private String state;
    private String username;
    private String usertype;

    private String flg;
    public String getFlg() {
        return flg;
    }

    public void setFlg(String flg) {
        this.flg = flg;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStime() {
        return stime;
    }

    public void setStime(String stime) {
        this.stime = stime;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getUserno() {
        return userno;
    }

    public void setUserno(String userno) {
        this.userno = userno;
    }

    public String getStimeStr() {
        return stimeStr;
    }

    public void setStimeStr(String stimeStr) {
        this.stimeStr = stimeStr;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
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

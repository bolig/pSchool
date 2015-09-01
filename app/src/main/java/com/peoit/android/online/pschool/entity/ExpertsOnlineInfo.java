package com.peoit.android.online.pschool.entity;

import com.peoit.android.online.pschool.EntityBase;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2015/8/12.
 */
public class ExpertsOnlineInfo implements Serializable, EntityBase {
    /**
     * flg : Y
     * userno : 522229200201173615
     * stimeStr : 2015-08-13 10:30
     * nid : 40
     * usertype : 2
     * stime : 1439433018000
     * pid : 0
     * type : Q
     * dis : [{"userno":"zj","stimeStr":"2015-08-13 10:33","usertype":"3","pid":70,"id":71,"text":"一些地区","username":"专家"}]
     * id : 70
     * text : 在一起了！在
     * state : Y
     * username : 吴伟
     */
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
    private String flg;
    private String userno;
    private String stimeStr;
    private int nid;
    private String usertype;
    private long stime;
    private int pid;
    private String type;
    private List<DisEntity> dis;
    private int id;
    private String text;
    private String state;
    private String username;

    @Override
    public boolean isNull() {
        return false;
    }

    @Override
    public boolean match() {
        return false;
    }

    public void setFlg(String flg) {
        this.flg = flg;
    }

    public void setUserno(String userno) {
        this.userno = userno;
    }

    public void setStimeStr(String stimeStr) {
        this.stimeStr = stimeStr;
    }

    public void setNid(int nid) {
        this.nid = nid;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public void setStime(long stime) {
        this.stime = stime;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDis(List<DisEntity> dis) {
        this.dis = dis;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFlg() {
        return flg;
    }

    public String getUserno() {
        return userno;
    }

    public String getStimeStr() {
        return stimeStr;
    }

    public int getNid() {
        return nid;
    }

    public String getUsertype() {
        return usertype;
    }

    public long getStime() {
        return stime;
    }

    public int getPid() {
        return pid;
    }

    public String getType() {
        return type;
    }

    public List<DisEntity> getDis() {
        return dis;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getState() {
        return state;
    }

    public String getUsername() {
        return username;
    }

    public class DisEntity implements Serializable{
        /**
         * userno : zj
         * stimeStr : 2015-08-13 10:33
         * usertype : 3
         * pid : 70
         * id : 71
         * text : 一些地区
         * username : 专家
         */
        private String userno;
        private String stimeStr;
        private String usertype;
        private int pid;
        private int id;
        private String text;
        private String username;

        public void setUserno(String userno) {
            this.userno = userno;
        }

        public void setStimeStr(String stimeStr) {
            this.stimeStr = stimeStr;
        }

        public void setUsertype(String usertype) {
            this.usertype = usertype;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setText(String text) {
            this.text = text;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getUserno() {
            return userno;
        }

        public String getStimeStr() {
            return stimeStr;
        }

        public String getUsertype() {
            return usertype;
        }

        public int getPid() {
            return pid;
        }

        public int getId() {
            return id;
        }

        public String getText() {
            return text;
        }

        public String getUsername() {
            return username;
        }
    }

}

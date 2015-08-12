package com.peoit.android.online.pschool.entity;

import com.peoit.android.online.pschool.EntityBase;

import java.io.Serializable;

/**
 * author:libo
 * time:2015/8/12
 * E-mail:boli_android@163.com
 * last: ...
 */
public class CheckInInfo implements Serializable, EntityBase {

    /**
     * sTime : 14:30:00
     * stuno : 0080904049
     * stime : null
     * stuname : 桂振耀
     * state : 1
     * pername : 下午
     * type : 3
     * stuschoolname : 铜仁八小
     * updated : null
     * created : 2015-07-07 01:00:00.0
     * frename : 八小上学规则
     * sCd : 0
     * stuclassname : 2009届04班
     * stuclass : 008028
     * status : 未处理
     * eTime : 16:00:00
     * stuschoolcode : 008
     * perorder : 1
     * perid : 8462
     * toup : 0
     * stuid : 16980
     * flag : null
     * etime : null
     * detDate : 2015-07-07 00:00:00.0
     * detId : 4347867
     * offEntrytime : null
     * freid : 766096
     */

    private String sTime;
    private String stuno;
    private String stime;
    private String stuname;
    private int state;
    private String pername;
    private int type;
    private String stuschoolname;
    private String updated;
    private String created;
    private String frename;
    private String sCd;
    private String stuclassname;
    private String stuclass;
    private String status;
    private String eTime;
    private String stuschoolcode;
    private int perorder;
    private int perid;
    private int toup;
    private int stuid;
    private String flag;
    private String etime;
    private String detDate;
    private int detId;
    private String offEntrytime;
    private int freid;

    @Override
    public boolean isNull() {
        return false;
    }

    @Override
    public boolean match() {
        return false;
    }

    public void setSTime(String sTime) {
        this.sTime = sTime;
    }

    public void setStuno(String stuno) {
        this.stuno = stuno;
    }

    public void setStime(String stime) {
        this.stime = stime;
    }

    public void setStuname(String stuname) {
        this.stuname = stuname;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setPername(String pername) {
        this.pername = pername;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setStuschoolname(String stuschoolname) {
        this.stuschoolname = stuschoolname;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public void setFrename(String frename) {
        this.frename = frename;
    }

    public void setSCd(String sCd) {
        this.sCd = sCd;
    }

    public void setStuclassname(String stuclassname) {
        this.stuclassname = stuclassname;
    }

    public void setStuclass(String stuclass) {
        this.stuclass = stuclass;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setETime(String eTime) {
        this.eTime = eTime;
    }

    public void setStuschoolcode(String stuschoolcode) {
        this.stuschoolcode = stuschoolcode;
    }

    public void setPerorder(int perorder) {
        this.perorder = perorder;
    }

    public void setPerid(int perid) {
        this.perid = perid;
    }

    public void setToup(int toup) {
        this.toup = toup;
    }

    public void setStuid(int stuid) {
        this.stuid = stuid;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public void setEtime(String etime) {
        this.etime = etime;
    }

    public void setDetDate(String detDate) {
        this.detDate = detDate;
    }

    public void setDetId(int detId) {
        this.detId = detId;
    }

    public void setOffEntrytime(String offEntrytime) {
        this.offEntrytime = offEntrytime;
    }

    public void setFreid(int freid) {
        this.freid = freid;
    }

    public String getSTime() {
        return sTime;
    }

    public String getStuno() {
        return stuno;
    }

    public String getStime() {
        return stime;
    }

    public String getStuname() {
        return stuname;
    }

    public int getState() {
        return state;
    }

    public String getPername() {
        return pername;
    }

    public int getType() {
        return type;
    }

    public String getStuschoolname() {
        return stuschoolname;
    }

    public String getUpdated() {
        return updated;
    }

    public String getCreated() {
        return created;
    }

    public String getFrename() {
        return frename;
    }

    public String getSCd() {
        return sCd;
    }

    public String getStuclassname() {
        return stuclassname;
    }

    public String getStuclass() {
        return stuclass;
    }

    public String getStatus() {
        return status;
    }

    public String getETime() {
        return eTime;
    }

    public String getStuschoolcode() {
        return stuschoolcode;
    }

    public int getPerorder() {
        return perorder;
    }

    public int getPerid() {
        return perid;
    }

    public int getToup() {
        return toup;
    }

    public int getStuid() {
        return stuid;
    }

    public String getFlag() {
        return flag;
    }

    public String getEtime() {
        return etime;
    }

    public String getDetDate() {
        return detDate;
    }

    public int getDetId() {
        return detId;
    }

    public String getOffEntrytime() {
        return offEntrytime;
    }

    public int getFreid() {
        return freid;
    }

    @Override
    public String toString() {
        return "CheckInInfo{" +
                "sTime='" + sTime + '\'' +
                ", stuno='" + stuno + '\'' +
                ", stime='" + stime + '\'' +
                ", stuname='" + stuname + '\'' +
                ", state=" + state +
                ", pername='" + pername + '\'' +
                ", type=" + type +
                ", stuschoolname='" + stuschoolname + '\'' +
                ", updated='" + updated + '\'' +
                ", created='" + created + '\'' +
                ", frename='" + frename + '\'' +
                ", sCd='" + sCd + '\'' +
                ", stuclassname='" + stuclassname + '\'' +
                ", stuclass='" + stuclass + '\'' +
                ", status='" + status + '\'' +
                ", eTime='" + eTime + '\'' +
                ", stuschoolcode='" + stuschoolcode + '\'' +
                ", perorder=" + perorder +
                ", perid=" + perid +
                ", toup=" + toup +
                ", stuid=" + stuid +
                ", flag='" + flag + '\'' +
                ", etime='" + etime + '\'' +
                ", detDate='" + detDate + '\'' +
                ", detId=" + detId +
                ", offEntrytime='" + offEntrytime + '\'' +
                ", freid=" + freid +
                '}';
    }
}

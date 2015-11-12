package com.peoit.android.online.pschool.entity;

import com.peoit.android.online.pschool.EntityBase;

import java.io.Serializable;

/**
 * 单词成绩查询
 *
 * author:libo
 * time:2015/8/7
 * E-mail:boli_android@163.com
 * last: ...
 */
public class SingleGradeInfo implements Serializable, EntityBase {
    /**
     * cid : 4
     * id : 2
     * stuno : 0080904042
     * stuname: 周蕾            --学生姓名
     * stuid: 16987            --学生编号
     * className : 2009届04班
     * classid : 008028
     * top : 0                --本次考试排名
     *
     * v1 : 0
     * v5 : 0
     * v4 : 0
     * v3 : 0
     * v2 : 0
     * v7 : 0
     * v6 : 0
     * v9 : 1.0
     * v8 : 0
     * v10 : 0
     * <p>
     *                         "v1":"0","v2":"0","v3":"0","v4":"0","v5":"0","v6":"0","v7":"0","v8":"0","v9":"0",
     * 各个科目的成绩。顺序对应{"语文成绩","数学成绩","英语成绩","物理成绩","生物成绩","化学成绩","历史成绩","政治成绩","地理成绩"};
     */

    private long id = -1;
    private long cid;

    private String stuno = "0080904043";
    private String stuname = "唐文豪";
    private String stuid;
    private String className = "2009届04班";
    private String top = "99999";
    private String classid;

    private String v1 = "100";
    private String v2 = "100";
    private String v3 = "100";
    private String v4 = "100";
    private String v5 = "100";
    private String v6 = "100";
    private String v7 = "100";
    private String v8 = "100";
    private String v9 = "100";
    private String v10 = "100";

    public void setV1(String v1) {
        this.v1 = v1;
    }

    public void setStuno(String stuno) {
        this.stuno = stuno;
    }

    public void setV5(String v5) {
        this.v5 = v5;
    }

    public void setV4(String v4) {
        this.v4 = v4;
    }

    public void setStuname(String stuname) {
        this.stuname = stuname;
    }

    public void setV3(String v3) {
        this.v3 = v3;
    }

    public void setV2(String v2) {
        this.v2 = v2;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStuid(String stuid) {
        this.stuid = stuid;
    }

    public void setV7(String v7) {
        this.v7 = v7;
    }

    public void setV6(String v6) {
        this.v6 = v6;
    }

    public void setV9(String v9) {
        this.v9 = v9;
    }

    public void setV8(String v8) {
        this.v8 = v8;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setV10(String v10) {
        this.v10 = v10;
    }

    public void setTop(String top) {
        this.top = top;
    }

    public void setClassid(String classid) {
        this.classid = classid;
    }

    public String getV1() {
        return v1;
    }

    public String getStuno() {
        return stuno;
    }

    public String getV5() {
        return v5;
    }

    public String getV4() {
        return v4;
    }

    public String getStuname() {
        return stuname;
    }

    public String getV3() {
        return v3;
    }

    public String getV2() {
        return v2;
    }

    public long getCid() {
        return cid;
    }

    public long getId() {
        return id;
    }

    public String getStuid() {
        return stuid;
    }

    public String getV7() {
        return v7;
    }

    public String getV6() {
        return v6;
    }

    public String getV9() {
        return v9;
    }

    public String getV8() {
        return v8;
    }

    public String getClassName() {
        return className;
    }

    public String getV10() {
        return v10;
    }

    public String getTop() {
        return top;
    }

    public String getClassid() {
        return classid;
    }

    @Override
    public String toString() {
        return "SingleGradeInfo{" +
                "id=" + id +
                ", cid=" + cid +
                ", stuno='" + stuno + '\'' +
                ", stuname='" + stuname + '\'' +
                ", stuid='" + stuid + '\'' +
                ", className='" + className + '\'' +
                ", top='" + top + '\'' +
                ", classid='" + classid + '\'' +
                ", v1='" + v1 + '\'' +
                ", v2='" + v2 + '\'' +
                ", v3='" + v3 + '\'' +
                ", v4='" + v4 + '\'' +
                ", v5='" + v5 + '\'' +
                ", v6='" + v6 + '\'' +
                ", v7='" + v7 + '\'' +
                ", v8='" + v8 + '\'' +
                ", v9='" + v9 + '\'' +
                ", v10='" + v10 + '\'' +
                '}';
    }

    @Override
    public boolean isNull() {
        return id == -1;
    }

    @Override
    public boolean match() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SingleGradeInfo that = (SingleGradeInfo) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return (int) id;
    }
}

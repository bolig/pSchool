package com.peoit.android.online.pschool.entity;

import android.text.TextUtils;

import com.peoit.android.online.pschool.EntityBase;

import java.io.Serializable;

/**
 * 用户属性实体类
 * <p/>
 * author:libo
 * time:2015/7/13
 * E-mail:boli_android@163.com
 * last: ...
 */
public class UserInfo implements Serializable, EntityBase {

    /**
     * schoolid : null
     * phone : 1391076841
     * stuno : 0080904043
     * schoolName : null
     * fatidnum : null
     * stuname : 唐文豪
     * basUtId : 0
     * fatpass : null
     * cardstatus : 0
     * calssName : null
     * type : 3
     * password : null
     * stuschoolname : 铜仁八小
     * fatname : null
     * username : 12
     * fatbanknum : null
     * reliefdesc : null
     * updated : null
     * created : 2014-11-24 20:55:59
     * fatmobile : 13985868573
     * stualiase : null
     * classid : 006035
     * admissiondate : 2010-09-01
     * stuidnum : 6232082800004467038
     * identityType : 2
     * stuclassname : 2009届04班
     * status : 1
     * nickname : 12
     * stuclass : 008028
     * flg : Y
     * stuschoolcode : 008
     * deleted : null
     * reliefstatus : 0
     * stupass : 300058
     * stubanknum : 6232082800004467038
     * createid : 0
     * stusex : 0
     * cardno : 43
     */

    private String schoolid; // -------------------------- 老师或者专家学校编号
    private String phone; // ----------------------------- 老师或者专家电话号码（用户类型为家长时此字段为其孩子的班主任信息）
    private String stuno; // ----------------------------- 编号
    private String schoolName; // ------------------------ 老师或者专家所在学校姓名
    private String fatidnum; // -------------------------- 家长身份证号
    private String stuname; // --------------------------- 姓名
    private int basUtId; // ------------------------------
    private String fatpass; // --------------------------- 家长登录密码
    private int cardstatus; // --------------------------- 卡状态0正常；1挂失；2为办卡
    private String calssName; // ------------------------- 老师或者专家所在班级姓名
    private String type; // ------------------------------
    private String password; // -------------------------- 老师或者专家登陆密码 （用户类型为家长时此字段为其孩子的班主任信息）
    private String stuschoolname; // --------------------- 学校名称
    private String fatname; // --------------------------- 家长姓名
    private String username; // -------------------------- 老师或者专家登录名 （用户类型为家长时此字段为其孩子的班主任信息）
    private String fatbanknum; // ------------------------ 家长银行卡号
    private String reliefdesc; // ------------------------ 减免说明
    private String updated; // --------------------------- 更新时间
    private String created; // --------------------------- 人员创建时间
    private String fatmobile; // ------------------------- 家长手机
    private String stualiase; // ------------------------- 别名
    private String classid; // --------------------------- 老师或者专家班级编号
    private String admissiondate; // --------------------- 入学时间
    private String stuidnum; // -------------------------- 人员身份证号
    private String identityType; // ---------------------- 用户类型 1老师 2 家长 3专家
    private String stuclassname; // ---------------------- 班级名称
    private int status; // ------------------------------- 学籍状态 1/入学;2/转出;3/转入;4/退学;5/留级;6/结业;7/毕业;8/身亡
    private String nickname; // -------------------------- 老师或者专家昵称 （用户类型为家长时此字段为其孩子的班主任信息）
    private String stuclass; // -------------------------- 班级编号
    private String flg; // -------------------------------
    private String stuschoolcode; // --------------------- 学校编号
    private String deleted; // --------------------------- 删除时间
    private int reliefstatus; // ------------------------- 是否有减免0无;1有
    private String stupass; // --------------------------- 人员登录密码
    private String stubanknum; // ------------------------ 人员银行卡号
    private int createid; // ----------------------------- 添加改人员的操作员Id
    private int stusex; // ------------------------------- 性别0男/1女
    private int cardno; // -------------------------------
    /**
     * result : {"receiverTel":null,"reversePhoto":null,"receiverName":null,"recentLoginDate":null,"mobile":"18306043086","photo":null,"description":null,"receiveSms":true,"officeTel":null,"password":"123456","phoneNumber":"18306043086","nick_name":"18306043086","name":null,"scale_image":null,"details":null,"id":15,"shopId":0,"payPassword":null,"email":null,"cardNumber":null,"username":"18306043086","frontPhoto":null}
     * reason : success
     * result_code : 200
     */
    private ResultEntity result;
    private String reason;
    private int result_code;

    @Override
    public boolean isNull() {
        return TextUtils.isEmpty(identityType);
    }

    @Override
    public boolean match() {
        return false;
    }

    public void setSchoolid(String schoolid) {
        this.schoolid = schoolid;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setStuno(String stuno) {
        this.stuno = stuno;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public void setFatidnum(String fatidnum) {
        this.fatidnum = fatidnum;
    }

    public void setStuname(String stuname) {
        this.stuname = stuname;
    }

    public void setBasUtId(int basUtId) {
        this.basUtId = basUtId;
    }

    public void setFatpass(String fatpass) {
        this.fatpass = fatpass;
    }

    public void setCardstatus(int cardstatus) {
        this.cardstatus = cardstatus;
    }

    public void setCalssName(String calssName) {
        this.calssName = calssName;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setStuschoolname(String stuschoolname) {
        this.stuschoolname = stuschoolname;
    }

    public void setFatname(String fatname) {
        this.fatname = fatname;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFatbanknum(String fatbanknum) {
        this.fatbanknum = fatbanknum;
    }

    public void setReliefdesc(String reliefdesc) {
        this.reliefdesc = reliefdesc;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public void setFatmobile(String fatmobile) {
        this.fatmobile = fatmobile;
    }

    public void setStualiase(String stualiase) {
        this.stualiase = stualiase;
    }

    public void setClassid(String classid) {
        this.classid = classid;
    }

    public void setAdmissiondate(String admissiondate) {
        this.admissiondate = admissiondate;
    }

    public void setStuidnum(String stuidnum) {
        this.stuidnum = stuidnum;
    }

    public void setIdentityType(String identityType) {
        this.identityType = identityType;
    }

    public void setStuclassname(String stuclassname) {
        this.stuclassname = stuclassname;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setStuclass(String stuclass) {
        this.stuclass = stuclass;
    }

    public void setFlg(String flg) {
        this.flg = flg;
    }

    public void setStuschoolcode(String stuschoolcode) {
        this.stuschoolcode = stuschoolcode;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public void setReliefstatus(int reliefstatus) {
        this.reliefstatus = reliefstatus;
    }

    public void setStupass(String stupass) {
        this.stupass = stupass;
    }

    public void setStubanknum(String stubanknum) {
        this.stubanknum = stubanknum;
    }

    public void setCreateid(int createid) {
        this.createid = createid;
    }

    public void setStusex(int stusex) {
        this.stusex = stusex;
    }

    public void setCardno(int cardno) {
        this.cardno = cardno;
    }

    public String getSchoolid() {
        return schoolid;
    }

    public String getPhone() {
        return phone;
    }

    public String getStuno() {
        return stuno;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public String getFatidnum() {
        return fatidnum;
    }

    public String getStuname() {
        return stuname;
    }

    public int getBasUtId() {
        return basUtId;
    }

    public String getFatpass() {
        return fatpass;
    }

    public int getCardstatus() {
        return cardstatus;
    }

    public String getCalssName() {
        return calssName;
    }

    public String getType() {
        return type;
    }

    public String getPassword() {
        return password;
    }

    public String getStuschoolname() {
        return stuschoolname;
    }

    public String getFatname() {
        return fatname;
    }

    public String getUsername() {
        return username;
    }

    public String getFatbanknum() {
        return fatbanknum;
    }

    public String getReliefdesc() {
        return reliefdesc;
    }

    public String getUpdated() {
        return updated;
    }

    public String getCreated() {
        return created;
    }

    public String getFatmobile() {
        return fatmobile;
    }

    public String getStualiase() {
        return stualiase;
    }

    public String getClassid() {
        return classid;
    }

    public String getAdmissiondate() {
        return admissiondate;
    }

    public String getStuidnum() {
        return stuidnum;
    }

    public String getIdentityType() {
        return identityType;
    }

    public String getStuclassname() {
        return stuclassname;
    }

    public int getStatus() {
        return status;
    }

    public String getNickname() {
        return nickname;
    }

    public String getStuclass() {
        return stuclass;
    }

    public String getFlg() {
        return flg;
    }

    public String getStuschoolcode() {
        return stuschoolcode;
    }

    public String getDeleted() {
        return deleted;
    }

    public int getReliefstatus() {
        return reliefstatus;
    }

    public String getStupass() {
        return stupass;
    }

    public String getStubanknum() {
        return stubanknum;
    }

    public int getCreateid() {
        return createid;
    }

    public int getStusex() {
        return stusex;
    }

    public int getCardno() {
        return cardno;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "schoolid='" + schoolid + '\'' +
                ", phone='" + phone + '\'' +
                ", stuno='" + stuno + '\'' +
                ", schoolName='" + schoolName + '\'' +
                ", fatidnum='" + fatidnum + '\'' +
                ", stuname='" + stuname + '\'' +
                ", basUtId=" + basUtId +
                ", fatpass='" + fatpass + '\'' +
                ", cardstatus=" + cardstatus +
                ", calssName='" + calssName + '\'' +
                ", type='" + type + '\'' +
                ", password='" + password + '\'' +
                ", stuschoolname='" + stuschoolname + '\'' +
                ", fatname='" + fatname + '\'' +
                ", username='" + username + '\'' +
                ", fatbanknum='" + fatbanknum + '\'' +
                ", reliefdesc='" + reliefdesc + '\'' +
                ", updated='" + updated + '\'' +
                ", created='" + created + '\'' +
                ", fatmobile='" + fatmobile + '\'' +
                ", stualiase='" + stualiase + '\'' +
                ", classid='" + classid + '\'' +
                ", admissiondate='" + admissiondate + '\'' +
                ", stuidnum='" + stuidnum + '\'' +
                ", identityType='" + identityType + '\'' +
                ", stuclassname='" + stuclassname + '\'' +
                ", status=" + status +
                ", nickname='" + nickname + '\'' +
                ", stuclass='" + stuclass + '\'' +
                ", flg='" + flg + '\'' +
                ", stuschoolcode='" + stuschoolcode + '\'' +
                ", deleted='" + deleted + '\'' +
                ", reliefstatus=" + reliefstatus +
                ", stupass='" + stupass + '\'' +
                ", stubanknum='" + stubanknum + '\'' +
                ", createid=" + createid +
                ", stusex=" + stusex +
                ", cardno=" + cardno +
                '}';
    }

    public void setResult(ResultEntity result) {
        this.result = result;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setResult_code(int result_code) {
        this.result_code = result_code;
    }

    public ResultEntity getResult() {
        return result;
    }

    public String getReason() {
        return reason;
    }

    public int getResult_code() {
        return result_code;
    }

    public class ResultEntity {

    }
}

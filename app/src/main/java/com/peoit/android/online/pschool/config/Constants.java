package com.peoit.android.online.pschool.config;

/**
 * 配置信息，用于保存项目常量...
 * <p/>
 * author:libo
 * time:2015/7/13
 * E-mail:boli_android@163.com
 * last: ...
 */
public class Constants {
    /**
     * 用户登录后信息...
     */
    public static final String LOGIN_USER_INFO = "LOGIN_USER_INFO";
    public static final String LOGIN_USER_SIGN = "LOGIN_USER_SIGN";
    public static final String LOGIN_USER_NAME = "LOGIN_USER_NAME";
    public static final String LOGIN_CHAT_GROUP_ID = "LOGIN_GROUP_ID";
    public static final String LOGIN_CHAT_NICKNAME = "LOGIN_NICKNAME";
    public static final String LOGIN_ISZHUANJIA = "LOGIN_ISZHUANJIA";
    public static final String LOGIN_USER_PASS = "LOGIN_USER_PASS";

    /**
     * 登录用户所属类型...1. 老师、 2.家长、 3.专家  4、专家管理员
     */
    public static final int TYPE_TEACHER = 1;
    public static final int TYPE_PARENT = 2;
    public static final int TYPE_ZHUAN_JIA = 3;
    public static final int TYPE_ZHUAN_JIA1 = 4;
    public static final int TPYE_NOT_LOGIN = -1;

    /**
     * 极光推送
     */
    public static final String JPUSH_SET_TAGS = "jpush_set_tags";
    public static final String JPUSH_SET_ALIAS = "jpush_set_alias";
    public static final String DB_TAB_HOMEBANNER = "TAB_HOMEBANNER";

    public static final String[] mParentChildAreas = new String[]{"header市网校",
            "阳光亲子营",
            "header区县分校",
            "碧江区阳光亲子营",
            "万山区阳光亲子营",
            "玉屏县阳光亲子营",
            "松桃县阳光亲子营",
            "江口县阳光亲子营",
            "印江县阳光亲子营",
            "石阡县阳光亲子营",
            "思南县阳光亲子营",
            "德江县阳光亲子营",
            "沿河县阳光亲子营"};

    public static final String[] mParentChildAreasDet = new String[]{"网校",
            "网校亲子活动",
            "区县",
            "碧江区",
            "万山区",
            "玉屏县",
            "松桃县",
            "江口县",
            "印江县",
            "石阡县",
            "思南县",
            "德江县",
            "沿河县"};

    public static final String $_ADD_$ = "add";

    public static final String[] mParentClasses = new String[]{
            $_ADD_$ + "学前教育",
            "小班",
            "中班",
            "大班",
            $_ADD_$ + "小学",
            "一年级",
            "二年级",
            "三年级",
            "四年级",
            "五年级",
            "六年级",
            $_ADD_$ + "初中",
            "初一年级",
            "初二年级",
            "初三年级"
    };

    public static final String LOGIN_USER_NIKE = "LOGIN_USER_NIKE1";
}

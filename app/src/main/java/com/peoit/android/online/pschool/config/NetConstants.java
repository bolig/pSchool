package com.peoit.android.online.pschool.config;

import com.android.volley.Request;

/**
 * author:libo
 * time:2015/7/13
 * E-mail:boli_android@163.com
 * last: ...
 */
public class NetConstants {
    public final static int GET_METHOD = Request.Method.GET;
    public final static int POST_METHOD = Request.Method.POST;
    public final static int PUT_METHOD = Request.Method.PUT;
    public final static int DELETE_METHOD = Request.Method.DELETE;
    public final static int HEAD_METHOD = Request.Method.HEAD;

    public final static String REQMODEL_ENTITY = "1111";  // ------------- 单个实体
    public final static String REQMODEL_ENTITYLIST = "1112"; // ---------- 实体集合
    public final static String REQMODEL_ENTITYLISTPAGE = "1113"; // ------ 实体集合并有分页...

    public final static String URL_BRIDGE = "-NET-BRIDGE-"; // 用于连接数据请求字符串,

    /**
     *
     * -------------------------- 网络请求 ------------------------------
     * 格式: 网络请求方式 (见上面 例: GET_METHOD 不写默认为 POST) + 网络地址 + 网络请求返回数据 (见上面
     * 例: REQMODEL_ENTITY 不写默认为 REQMODEL_ENTITY)；
     *
     * 例: GET_METHOD + URL_BRIDGE + HOST + "/queryCourse.do" + URL_BRIDGE + REQMODEL_ENTITY;
     *
     */

    public static final String IMAGE_HOST = "http://123.57.221.31:8081";

    public static final String HOST = IMAGE_HOST+"/gz/api/"; // ------------------------------------- 域名

    public static final String NET_LOGIN = HOST + "login.do"; // -------------------------------------------------- 用户登录

    public static final String NET_QUERYCOURSEBYID = HOST + "queryCourseByid.do"+ URL_BRIDGE + REQMODEL_ENTITYLIST; // - 获取单一考试成绩明细

    public static final String NET_QUERYCOURSE = HOST + "queryCourse.do" + URL_BRIDGE + REQMODEL_ENTITYLIST; // --- 获取考试成绩列表明细

    public static final String NET_FEATURE_LIST = HOST + "queryNew.do" + URL_BRIDGE + REQMODEL_ENTITYLIST; // ----- 查询专栏列表(POST请求)

    public static final String NET_EXPERTSONLINE = HOST + "queryDiscuss.do" + URL_BRIDGE + REQMODEL_ENTITYLIST; // ----- 查询针对专栏文章的提问和回复列表

    public static final String NET_MODIFY_PASSWORD = HOST + "modifyPassword.do"; // ------------------------------- 修改密码

    public static final String NET_NOTICE_LIST = HOST + "queryNode.do" + URL_BRIDGE + REQMODEL_ENTITYLIST; // ----- 通知列表

    public static final String NET_ADD_Q = HOST + "addQuestion.do"; // -------------------------------------------- 通知列表

    public static final String NET_ADD_R = HOST + "addReply.do";

    public static final String NET_CHECK_IN_LIST = HOST + "queryDiffAtt.do" + URL_BRIDGE + REQMODEL_ENTITYLIST; //- 考勤列表

    public static final String NET_PUSH = HOST + "queryNodeByid.do"; //-------------------------------------------- 通知详情

    public static final String NET_QUERY_AD = HOST + "queryAd.do" + URL_BRIDGE + REQMODEL_ENTITYLIST; //----------- 获取首页轮播图列表查询

    public static final String NET_ADDVIDEO = HOST + "addVideo.do"; // -------------------------------------------- 上传视频

    public static final String NET_CHAT_USERS = HOST + "queryusers.do" + URL_BRIDGE + REQMODEL_ENTITYLIST;; //----- 多个用户信息查询

    public static final String NET_BANNER_INFO = HOST + "queryAdByid.do"; // -------------------------------------- 获取banner详情


}

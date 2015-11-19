package com.peoit.android.online.pschool.db.service;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.peoit.android.online.pschool.db.DataBaseHelper;
import com.peoit.android.online.pschool.entity.HomeBannerInfo;

import java.sql.SQLException;
import java.util.List;

/**
 * author:libo
 * time:2015/10/12
 * E-mail:boli_android@163.com
 * last: ...
 */
public class HomeBannerService {
    private final DataBaseHelper mDataBaseHelper;
    private final Dao<HomeBannerInfo, Long> mEntityDao;

    public HomeBannerService(Context context) {
        mDataBaseHelper = DataBaseHelper.getHelper(context);
        mEntityDao = mDataBaseHelper.getHomeBannerDao();
    }

    public void addAll(List<HomeBannerInfo> infos) {
        try {
            mEntityDao.create(infos);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<HomeBannerInfo> getAll() {
        try {
            return mEntityDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void delAll() {
        try {
            mEntityDao.delete(getAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

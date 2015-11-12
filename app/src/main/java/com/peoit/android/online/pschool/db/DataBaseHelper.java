package com.peoit.android.online.pschool.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.peoit.android.online.pschool.entity.HomeBannerInfo;

import java.sql.SQLException;

public class DataBaseHelper extends OrmLiteSqliteOpenHelper {
    public static final String DB_dataBase = "pshool.db";
    private static DataBaseHelper helper;
    private Dao<HomeBannerInfo, Long> mHomeBannerDao;

    private DataBaseHelper(Context context) {
        super(context, DB_dataBase, null, 1);
    }

    public static DataBaseHelper getHelper(Context context) {
        if (helper == null) {
            helper = new DataBaseHelper(context);
        }
        return helper;
    }

    @Override
    public void onCreate(SQLiteDatabase db,
                         ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, HomeBannerInfo.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,
                          ConnectionSource connectionSource,
                          int version,
                          int arg3) {
        try {
            TableUtils.dropTable(connectionSource, HomeBannerInfo.class, true);
            onCreate(db, connectionSource);
            Log.e(DataBaseHelper.class.getName(), "更新数据库成功");
        } catch (SQLException e) {
            Log.e(DataBaseHelper.class.getName(), "更新数据库失败", e);
        }
    }

    @Override
    public void close() {
        super.close();
    }

    public Dao<HomeBannerInfo, Long> getHomeBannerDao(){
        try {
            if (mHomeBannerDao == null)
                mHomeBannerDao = getDao(HomeBannerInfo.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mHomeBannerDao;
    }
}

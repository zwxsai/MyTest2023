package com.example.mytest2023.greendao.manager;

import android.database.sqlite.SQLiteDatabase;

import com.example.mytest2023.BuildConfig;
import com.example.mytest2023.base.MyApp;
import com.example.mytest2023.greendao.dao.DaoMaster;
import com.example.mytest2023.greendao.dao.DaoSession;


/**
 * Created by 钟文祥 on 2018/6/18.
 * Describer: greendao工具类不用修改  可以不用这个类 直接下载application
 */
public class GreenDaoManager {
    private static DaoSession daoSession;

    private volatile static GreenDaoManager instance = null;

    private GreenDaoManager() {
//		//创建数据库shop.db"
//		DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "shop.db", null);
//		//获取可写数据库
//		SQLiteDatabase db = helper.getWritableDatabase();

        //创建数据库shop.db"
        MyOpenHelper helper = new MyOpenHelper(MyApp.getContextObject(), BuildConfig.DB_NAME, null);
        //获取可写数据库
        SQLiteDatabase db = helper.getWritableDatabase();
        //获取数据库对象
        DaoMaster daoMaster = new DaoMaster(db);
        //获取Dao对象管理者
        daoSession = daoMaster.newSession();
    }

    public static GreenDaoManager getInstance() {
        if (instance == null) {
            synchronized (GreenDaoManager.class) {
                if (instance == null) {
                    instance = new GreenDaoManager();
                }
            }
        }
        return instance;
    }

    public static DaoSession getDaoInstant() {
        getInstance();
        return daoSession;
    }

}

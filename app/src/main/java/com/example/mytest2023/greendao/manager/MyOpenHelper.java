package com.example.mytest2023.greendao.manager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import com.example.mytest2023.greendao.dao.DaoMaster;

import org.greenrobot.greendao.database.Database;

/**
 * Created by 钟文祥 on 2018/6/18.
 * Describer: 数据库升级使用
 */
public class MyOpenHelper extends DaoMaster.OpenHelper {
    public MyOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    /**
     * 数据库升级
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        //操作数据库的更新 有几个表升级都可以传入到下面

        Log.i("888", oldVersion + "---先前和更新之后的版本---" + newVersion);
        if (oldVersion < newVersion) {
//            if (newVersion == 2) {
//                AddTaskRequestDao.createTable(db, true);
//            }
//            更改过的实体类(新增的不用加)   更新UserDao文件 可以添加多个  XXDao.class 文件
//             MigrationHelper.getInstance().migrate(db, UserDao.class,XXDao.class);


            //把需要管理的数据库表DAO作为最后一个参数传入到方法中
            MigrationHelper2.migrate(db, new MigrationHelper2.ReCreateAllTableListener() {
                @Override
                public void onCreateAllTables(Database db, boolean ifNotExists) {
                    DaoMaster.createAllTables(db, true);
                }

                @Override
                public void onDropAllTables(Database db, boolean ifExists) {
                    DaoMaster.dropAllTables(db, true);
                }
            });//, 最后一个参数写更新的表
        }


    }
}

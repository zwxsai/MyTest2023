package com.example.mytest2023.room.helper;

import com.example.mytest2023.room.bean.Student;
import com.example.mytest2023.room.dao.StudentDao;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

/**
 * Created by 钟文祥 on 2023/6/14.
 * Describer:
 */
@Database(entities = {Student.class}, version = 1, exportSchema = false)
public abstract class DBMaster extends RoomDatabase {
    public abstract StudentDao studentDao();

    //升级问题 https://www.jianshu.com/p/a729f6ee37db
    //上面修改版本信息为2  version = 2
    //数据库变动添加Migration，简白的而说就是版本1到版本2改了什么东西
//    public static final Migration MIGRATION_1_2 = new Migration(1, 2) {
//        @Override
//        public void migrate(SupportSQLiteDatabase database) {
//            //告诉person表，增添一个String类型的字段 son
//            database.execSQL("ALTER TABLE person ADD COLUMN son TEXT");
//        }
//    };

}

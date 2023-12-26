package com.example.mytest2023.room.dao;

import com.example.mytest2023.room.bean.Student;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import io.reactivex.Flowable;
import io.reactivex.Single;

/**
 * Created by 钟文祥 on 2023/6/13.
 * Describer:
 */
@Dao
public interface StudentDao {

    @Query("select * from Student")
    List<Student> findAll();

    //Flowable写法 https://juejin.cn/post/6844903966086529038
    @Query("select * from Student")
    Flowable<List<Student>> findAll2();

    @Query("select * from Student where uid in (:ids)")
    List<Student> findAllByIds(int[] ids);

    @Query("select * from Student where first_name like :firstName and last_name like :lastName " +
            "LIMIT 1")
    List<Student> findLikeFirstName(String firstName, String lastName);

    //插入数据，onConflict = OnConflictStrategy.REPLACE表明若存在主键相同的情况则直接覆盖
    //返回的long表示的是插入项新的id
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Student student);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insertAll(Student... students);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insertAll2(List<Student> students);

    //更新数据，这意味着可以指定id然后传入新的person对象进行更新
    //返回行数
    @Update
    int update(Student student);

    //删除数据，根据传入实体的主键进行数据的删除。
    //返回行数
    @Delete
    int delete(Student student);


}

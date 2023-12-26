package com.example.mytest2023.room.bean;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**
 * Created by 钟文祥 on 2023/6/13.
 * Describer: room数据库
 * https://zhuanlan.zhihu.com/p/109074162
 * https://blog.csdn.net/ScottePerk/article/details/124973559
 *
 * @Entity ： 数据表的实体类。
 * @PrimaryKey ： 每一个实体类都需要一个唯一的标识。
 * @ColumnInfo ： 数据表中字段名称。
 * @Ignore ： 标注不需要添加到数据表中的属性。
 * @Embedded ： 实体类中引用其他实体类。
 * @ForeignKey ： 外键约束。
 * <p>
 * 作者：岩浆李的游鱼leo2
 * 链接：https://juejin.cn/post/6844903966086529038
 * 来源：稀土掘金
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 * <p>
 * //单列索引          @Entity(indices = {@Index(value = "name")})
 * //单列索引唯一性    @Entity(indices = {@Index(value = "name", unique = true)})
 * //组合索引          @Entity(indices ={@Index(value = {"name","age"})})
 * //组合索引唯一性    @Entity(indices ={@Index(value = {"name","age"},unique = true)})
 * //当然可以混起来用 如下：
 * @Entity(indices ={@Index(value = "name"),@Index(value = {"name","age"},unique = true)})
 */
@Entity(tableName = "Student")
public class Student {

    /**
     * 使用@PrimaryKey声明为主键，并且允许自动生成
     * 使用@ColumnInfo表明这个属性是表中的一列列名，并可以指明列的名称
     */
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "uid")
    public int uid;

    @ColumnInfo(name = "first_name")
    public String firstName;

    @ColumnInfo(name = "last_name")
    public String lastName;

    //构造方法必须添加@Ignore注解   并且只能一个可以不加
    @Ignore
    public Student() {

    }

    //为了更新数据时使用
//    @Ignore
    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Ignore
    public Student(int uid, String firstName, String lastName) {
        this.uid = uid;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    //为了删除时使用
    @Ignore
    public Student(int uid) {
        this.uid = uid;
    }


    //Getter和Setter方法：这些方法是必须要的，否则无法对对象属性进行读取或修改。


    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Student{" +
                "uid=" + uid +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}

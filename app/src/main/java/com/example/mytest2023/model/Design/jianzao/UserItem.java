package com.example.mytest2023.model.design.jianzao;

import com.example.mytest2023.BR;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

/**
 * Created by 钟文祥 on 2023-04-21.
 * Describer: 建造者模式
 * 定义：将一个复杂对象的构建与它的表示分离，使得同样的构建过程可以创建不同的表示
 * https://blog.csdn.net/qq_14931305/article/details/81296329
 */
public class UserItem extends BaseObservable {

    private int id;

    //如果是 public 修饰符，则可以直接在成员变量上方加上 @Bindable 注解
    @Bindable
    public String name;
    //如果是 private 修饰符，则在成员变量的 get 方法上添加 @Bindable 注解
    private int age;

    public UserItem() {
    }

    public UserItem(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.age = builder.age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        //只更新本字段 到view上
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
        //更新所有字段 到view上
        notifyChange();
    }


    public static class Builder {
        private int id;
        private String name;
        private int age;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public UserItem build() {
            return new UserItem(this);
        }

    }

}

package com.example.mytest2023.model.home;

/**
 * Created by 钟文祥 on 2020/4/21.
 * Describer: AR大类
 */
public class Category {

    private int CategoryID; //id
    private String Name;    //名称
    private int CIndex;     //排序

    public Category() {
    }

    public Category(int categoryID, String name, int CIndex) {
        CategoryID = categoryID;
        Name = name;
        this.CIndex = CIndex;
    }

    public int getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(int categoryID) {
        CategoryID = categoryID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getCIndex() {
        return CIndex;
    }

    public void setCIndex(int CIndex) {
        this.CIndex = CIndex;
    }
}

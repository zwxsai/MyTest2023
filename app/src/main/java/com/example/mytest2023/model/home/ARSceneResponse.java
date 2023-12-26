package com.example.mytest2023.model.home;


import java.util.List;

/**
 * Created by 钟文祥 on 2020/4/21.
 * Describer: 场景AR响应类
 */
public class ARSceneResponse {

    private List<Category> categoryList;
    private List<List<Scene>> sceneLists;

    public ARSceneResponse() {
    }

    public ARSceneResponse(List<Category> categoryList, List<List<Scene>> sceneLists) {
        this.categoryList = categoryList;
        this.sceneLists = sceneLists;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public List<List<Scene>> getSceneLists() {
        return sceneLists;
    }

    public void setSceneLists(List<List<Scene>> sceneLists) {
        this.sceneLists = sceneLists;
    }
}

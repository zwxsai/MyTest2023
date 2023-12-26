package com.example.mytest2023.model.home;

import java.io.Serializable;

/**
 * Created by 钟文祥 on 2020/4/21.
 * Describer: AR场景
 */
public class Scene implements Serializable {
    private static final long serialVersionUID = 1L;
    private int SceneID;            //场景id
    private int CategoryID;         //关联的大类id
    private String Name;            //场景名称
    private int ARType;             //AR识别类型 0云识别，1离线本地识别
    private String ARContentCode;   //AR内容类型，用于给客户端判断怎么展示AR内容
    private String SceneImgUrl;     //场景cell对应图片url
    private String Describe;        //场景文字描述
    private int CIndex;             //排序，默认0
    private boolean IsBuild;        //是否建设好，false建设中，true建设完成


    public int getSceneID() {
        return SceneID;
    }

    public void setSceneID(int sceneID) {
        SceneID = sceneID;
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

    public int getARType() {
        return ARType;
    }

    public void setARType(int ARType) {
        this.ARType = ARType;
    }

    public String getARContentCode() {
        return ARContentCode;
    }

    public void setARContentCode(String ARContentCode) {
        this.ARContentCode = ARContentCode;
    }

    public String getSceneImgUrl() {
        return SceneImgUrl;
    }

    public void setSceneImgUrl(String sceneImgUrl) {
        SceneImgUrl = sceneImgUrl;
    }

    public String getDescribe() {
        return Describe;
    }

    public void setDescribe(String describe) {
        Describe = describe;
    }

    public int getCIndex() {
        return CIndex;
    }

    public void setCIndex(int CIndex) {
        this.CIndex = CIndex;
    }

    public boolean isBuild() {
        return IsBuild;
    }

    public void setBuild(boolean build) {
        IsBuild = build;
    }


    public Scene(int sceneID, int categoryID, String name, int ARType, String ARContentCode,
                 String sceneImgUrl, String describe, int CIndex, boolean isBuild) {
        SceneID = sceneID;
        CategoryID = categoryID;
        Name = name;
        this.ARType = ARType;
        this.ARContentCode = ARContentCode;
        SceneImgUrl = sceneImgUrl;
        Describe = describe;
        this.CIndex = CIndex;
        IsBuild = isBuild;
    }


    public enum ARTypeCode {
        Cloud(0),   //云识别
        Local(1),   //离线识别
        Assist(2);  //远程协助

        private int value;

        public int getValue() {
            return value;
        }

        ARTypeCode(int value) {
            this.value = value;
        }
    }
}

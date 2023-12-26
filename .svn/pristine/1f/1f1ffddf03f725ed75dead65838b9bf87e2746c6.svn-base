package com.example.mytest2023.model.design.danli;

/**
 * Created by 钟文祥 on 2023-04-21.
 * Describer: 单例之 饿汗式  (不推荐使用)
 * 参考 https://blog.csdn.net/qq_14931305/article/details/81296329
 */
public class EHanSingle {


    private static final EHanSingle single = new EHanSingle();


    public static EHanSingle getInstance() {
        return single;
    }


    //优点 ：在类初始化时，已经自行实例化,所以是线程安全的。
    //缺点 ：没有懒加载的效果，如果没有使用过的话会造成内存浪费。
}

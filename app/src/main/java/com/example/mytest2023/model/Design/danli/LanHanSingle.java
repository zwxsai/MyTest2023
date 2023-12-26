package com.example.mytest2023.model.design.danli;

/**
 * Created by 钟文祥 on 2023-04-21.
 * Describer: 单例之 懒汗式
 * https://blog.csdn.net/qq_14931305/article/details/81296329
 */
public class LanHanSingle {
    private static LanHanSingle single = null;

    public static synchronized LanHanSingle getInstance() {
        if (single == null) {
            single = new LanHanSingle();
        }
        return single;
    }

//    优点：实现了懒加载的效果，线程安全。
//    缺点：使用synchronized会造成不必要的同步开销，而且大部分时候我们是用不到同步的。
}

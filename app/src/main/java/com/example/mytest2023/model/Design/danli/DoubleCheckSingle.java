package com.example.mytest2023.model.design.danli;

/**
 * Created by 钟文祥 on 2023-04-21.
 * Describer:单例 之双重检查锁  (单例就这样写)
 * https://blog.csdn.net/qq_14931305/article/details/81296329
 */
public class DoubleCheckSingle {
    private volatile static DoubleCheckSingle single; //对cpu总线加锁，数据直接写会到系统内存中，并且使在其他CPU里缓存了该地址的数据无效

    public static DoubleCheckSingle getInstance() {
        if (single == null) {
            synchronized (DoubleCheckSingle.class) {
                if (single == null) {
                    single = new DoubleCheckSingle();
                }
            }
        }
        return single;
    }

//    优点：懒加载，线程安全，效率较高
//    缺点：volatile影响一点性能，高并发下有一定的缺陷，某些情况下DCL会失效，虽然概率较小
}

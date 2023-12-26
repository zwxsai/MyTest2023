package com.example.mytest2023.module.Foundation;

import android.os.Bundle;

import com.example.mytest2023.R;


/**
 * Created by 钟文祥 on 2023/4/24.
 * Describer：数据结构
 * <p>
 * 参考 https://www.jianshu.com/p/e46af49c1739
 */
public class ShuJuJieGouActivity extends FoundationActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shu_ju_jie_gou);

    }

    @Override
    public void initView() {
        txt1.setText("数据结构");
        txt2.setText(data);

        int[] arr1 = new int[]{};//动态
        int[] arr2 = new int[3];
        int[] arr3 = new int[]{1, 2, 3};
    }

//    有链的查询慢 增减快

    //ArrayList 数组实现，查询快,增删慢,线程不安全
    //LinkedList 双向链表实现，查询慢，增删快，线程不安全
    //HashMap 数组+链表 或 链表+红黑树 无序，查询快
    //LinkHashMap 继承自HashMap,双向链表，查询慢，增删快，
    //TreeMap 红黑树实现的排序Map
    //HashSet 内部是使用HashMap  无序、不允许元素重复 利用了hashmap的key不会重复的特性来实现元素的唯一性
    //TreeSet 内部是使用TreeMap  有序、不允许元素重复
}
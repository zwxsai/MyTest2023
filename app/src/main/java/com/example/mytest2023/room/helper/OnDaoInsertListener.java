package com.example.mytest2023.room.helper;

import java.util.List;

/**
 * Created by 钟文祥 on 2023/6/27.
 * Describer: dao insert 的执行回调
 */
public interface OnDaoInsertListener {
    /** 子线程执行回调 */
    List<Long> call();

    /** 主线程回调 */
    void onNext(List<Long> longs);
}

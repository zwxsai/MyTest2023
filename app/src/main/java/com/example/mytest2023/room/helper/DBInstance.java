package com.example.mytest2023.room.helper;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.mytest2023.base.MyApp;

import java.util.List;
import java.util.concurrent.Callable;

import androidx.room.Room;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 钟文祥 on 2023/6/16.
 * Describer: 单例调出room数据库对象
 * https://juejin.cn/post/6844903966086529038
 */
public class DBInstance {
    //private static final String DB_NAME = "/sdcard/LianSou/room_iqiyi.db";
    private static final String DB_NAME = "room_iqiyi";
    public static DBMaster dbMaster;

    public static DBMaster getDBMaster() {
        if (dbMaster == null) {
            synchronized (DBInstance.class) {
                if (dbMaster == null) {
                    dbMaster = Room.databaseBuilder(MyApp.getContextObject(), DBMaster.class,
                                    DB_NAME)
//                            .allowMainThreadQueries() //可以在主线程使用 但推荐不用
//                            .addMigrations(DBMaster.MIGRATION_1_2)//版本升级时加上下面这一行
                            .build();
                }
            }
        }
        return dbMaster;
    }


    /** 数据库操作后在主线程执行任务 */
    @SuppressLint("CheckResult")
    public static <T> void daoFinds(Flowable<T> value,
                                    OnDaoFindsListener<T> listener) {
        value.subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Consumer<T>() {
                    @Override
                    public void accept(T t) throws Exception {
                        if (listener != null) {
                            listener.onNext(t);
                        }
                    }
                });
    }

    /** 数据库新增操作 */
    public static void doInsert(OnDaoInsertListener listener) {
        Single.fromCallable(new Callable<List<Long>>() {
                    @Override
                    public List<Long> call() throws Exception {
                        if (listener != null) {
                            return listener.call();
                        }
                        return null;
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Long>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<Long> longs) {
                        if (listener != null) {
                            listener.onNext(longs);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("AA", "新增数据失败");
                    }
                });
    }

    /** 数据库修改或删除操作 */
    public static void doUpdateOrDelete(OnDaoUpdateOrDeleteListener listener) {
        Single.fromCallable(new Callable<Integer>() {
                    @Override
                    public Integer call() throws Exception {
                        if (listener != null) {
                            return listener.call();
                        }
                        return 0;
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(Integer i) {
                        if (listener != null) {
                            listener.onNext(i);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }
}


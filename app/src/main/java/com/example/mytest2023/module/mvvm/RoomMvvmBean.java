package com.example.mytest2023.module.mvvm;

import com.example.mytest2023.helper.TimeHelper;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;

/**
 * Created by 钟文祥 on 2023/6/16.
 * Describer:
 */
public class RoomMvvmBean {
    //这里必须是常量,ObservableField<参数类型>
    //其实写上了下面一句，就是BaseObservable，set，get, @Bindable,刷新都封装了。直接看构造方法
    public final ObservableField<String> name = new ObservableField<>();

    public RoomMvvmBean() {
    }

    public RoomMvvmBean(String name) {
        this.name.set(name);
    }

    public ObservableField<String> getName() {
        return name;
    }
}

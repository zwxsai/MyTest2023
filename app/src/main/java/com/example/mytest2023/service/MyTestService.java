package com.example.mytest2023.service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.example.mytest2023.module.kl.IRemoteService;

import androidx.annotation.Nullable;

/**
 * Created by 钟文祥 on 2023/12/25.
 * Describer: aidl的服务
 * https://blog.csdn.net/hello_1995/article/details/122094512?ops_request_misc=%257B%2522request
 * %255Fid%2522%253A%2522170349611916800185811505%2522%252C%2522scm%2522%253A%252220140713
 * .130102334..%2522%257D&request_id=170349611916800185811505&biz_id=0&utm_medium=distribute
 * .pc_search_result.none-task-blog-2~all~top_positive~default-1-122094512-null-null
 * .142^v96^pc_search_result_base8&utm_term=aidl&spm=1018.2226.3001.4187
 * https://blog.csdn.net/jdfkldjlkjdl/article/details/115012307
 * https://www.jianshu.com/p/1140b90d8807
 */
public class MyTestService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyAidlService();
    }

    private class MyAidlService extends IRemoteService.Stub {

        @Override
        public String getName1(String name) throws RemoteException {
            return name + "123456";
        }

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
                               double aDouble, String aString) throws RemoteException {

        }
    }

//    private final IRemoteService.Stub binder = new IRemoteService.Stub() {
//        @Override
//        public String getName1(String name) throws RemoteException {
//            return name + "123456";
//        }
//
//        @Override
//        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
//                               double aDouble, String aString) throws RemoteException {
//
//        }
//    };


    /** activity里面
     private IRemoteService iRemoteService;
     private ServiceConnection mConnection = new ServiceConnection() {
    @Override public void onServiceConnected(ComponentName name, IBinder service) {
    Log.e("aidl测试", "onServiceConnected: 绑定成功");
    iRemoteService = IRemoteService.Stub.asInterface(service);
    new Thread(new Runnable() {
    @Override public void run() {
    try {
    String newname = iRemoteService.getName1("钟文祥");
    Log.e("aidl测试", "onServiceConnected: " + newname);
    } catch (RemoteException e) {
    throw new RuntimeException(e);
    }
    }
    }).start();
    }

    @Override public void onServiceDisconnected(ComponentName name) {
    Log.e("aidl测试", "onServiceConnected: 绑定中断");
    iRemoteService = null;
    }
    };

     private void bindService() {
     Intent intent = new Intent();
     intent.setAction("MyTestService.action");
     intent.setPackage("com.example.mytest2023");
     boolean df = getActivity().bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
     int er = 3;
     }
     * */

}

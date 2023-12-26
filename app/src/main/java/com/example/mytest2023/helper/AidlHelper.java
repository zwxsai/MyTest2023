package com.example.mytest2023.helper;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

import com.example.mytest2023.module.kl.IRemoteService;

/**
 * Created by 钟文祥 on 2023/12/25.
 * Describer:
 */
public class AidlHelper {
    private final IRemoteService.Stub binder = new IRemoteService.Stub() {
        @Override
        public String getName1(String name) throws RemoteException {
            return name + "123456";
        }

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
                               double aDouble, String aString) throws RemoteException {

        }
    };
}

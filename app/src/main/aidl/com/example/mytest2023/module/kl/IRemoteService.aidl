// IRemoteService.aidl
package com.example.mytest2023.module.kl;

// Declare any non-default types here with import statements

interface IRemoteService {

     String getName1(String name);

    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);


}
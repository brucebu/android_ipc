// IMyAidlInterface.aidl
package com.example.shibu.android_ipc.aidl;

// Declare any non-default types here with import statements
import com.example.shibu.android_ipc.aidl.IAidlListener;
import com.example.shibu.android_ipc.aidl.TestData;

interface IMyAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void print(int i);
//
    void setListener(in IAidlListener l);
//
    void printData(in TestData date);

    void getDataOut(out TestData data);

    void getDataInOut(inout TestData data);

    void getDataIn(in TestData data);

    void startCrashTask();
}

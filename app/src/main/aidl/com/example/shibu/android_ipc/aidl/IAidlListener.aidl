// IMyAidlListener.aidl
package com.example.shibu.android_ipc.aidl;
import com.example.shibu.android_ipc.aidl.TestData;
// Declare any non-default types here with import statements

interface IAidlListener {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    TestData getData();
}

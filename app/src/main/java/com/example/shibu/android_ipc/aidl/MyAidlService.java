package com.example.shibu.android_ipc.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

public class MyAidlService extends Service {
    Binder mBinder = new IMyAidlInterface.Stub(){

        @Override
        public void print(int i) throws RemoteException {
            Log.d("wpstest","remote print "+i);
        }

        @Override
        public void setListener(IAidlListener l) throws RemoteException {
            Log.d("wpstest","remote setListener "+l.getData().toString());
        }

        @Override
        public void printData(TestData date) throws RemoteException {
            Log.d("wpstest","remote print "+date.toString());
        }

        @Override
        public void getDataOut(TestData data) throws RemoteException {
            data.name="getdata";
        }

        @Override
        public void getDataInOut(TestData data) throws RemoteException {
            data.name="getdata";
        }

        @Override
        public void getDataIn(TestData data) throws RemoteException {
            data.name="getdata";
        }


        @Override
        public void startCrashTask() throws RemoteException {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    throw new NullPointerException();
                }
            }).start();
        }
    };
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}

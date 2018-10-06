package com.example.shibu.android_ipc.intent;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.Parcelable;
import android.util.Log;

public class MyService extends Service {
    public MyService() {
    }
    MyBinder binder = new MyBinder();
    public class MyBinder extends Binder {
         public void print() {
            // Return this instance of LocalService so clients can call public methods
             Log.d("wpstest","hello");
        }
    }
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return binder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(intent!=null){
            Parcelable parcelable = intent.getParcelableExtra("data");
            if(parcelable!=null) {
                Log.d("wpstest",parcelable.toString());
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }
}

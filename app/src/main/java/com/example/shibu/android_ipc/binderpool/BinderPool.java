package com.example.shibu.android_ipc.binderpool;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

import java.net.ContentHandler;

public class BinderPool {
    IBinderPool binderPool;
    private BinderPool(){
    }

    static public BinderPool getInstance(){
        return Instance.instance;
    }

    final static class Instance{
        final static BinderPool instance = new BinderPool();
    }

    IBinder.DeathRecipient deathRecipient = new IBinder.DeathRecipient() {
        @Override
        public void binderDied() {
            binderPool = null;
        }
    };
    public void init(Context c){
        Intent intent = new Intent(c,BinderPoolService.class);
        c.bindService(intent,serviceConnection,Context.BIND_AUTO_CREATE);
    }
    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binderPool = IBinderPool.Stub.asInterface(service);
            try {
                binderPool.asBinder().linkToDeath(deathRecipient,0);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    public IBinder getService(String name){
        IBinder binder = null;
        if(binderPool!=null){
            try {
                binder = binderPool.getBinder(name);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}

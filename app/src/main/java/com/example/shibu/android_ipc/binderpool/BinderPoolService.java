package com.example.shibu.android_ipc.binderpool;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;

public class BinderPoolService extends Service {
    Map<String, IBinder> binderMap = new HashMap<>();
    IBinder mbinderPool = new IBinderPool.Stub() {
        @Override
        public IBinder getBinder(String bindername) throws RemoteException {
            IBinder binder = binderMap.get(bindername);
            if(binder == null){
                binder = createBinder(bindername);
                binderMap.put(bindername,binder);
            }
            return binder;
        }
    };

    private IBinder createBinder(String bindername) {
        IBinder binder = null;
        switch (bindername){
            case "music":
                binder = new MusicServiceImpl();
                break;
            case "fm":
                binder = new FmServiceImpl();
                break;
        }
        return binder;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mbinderPool;
    }
}

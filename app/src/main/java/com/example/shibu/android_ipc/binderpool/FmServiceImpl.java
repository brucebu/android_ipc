package com.example.shibu.android_ipc.binderpool;

import android.os.RemoteException;
import android.util.Log;

import com.example.shibu.android_ipc.IFmService;

class FmServiceImpl extends IFmService.Stub {
    @Override
    public void play(float fre) throws RemoteException {
        Log.d("wpstest","fm play "+ fre);
    }
}

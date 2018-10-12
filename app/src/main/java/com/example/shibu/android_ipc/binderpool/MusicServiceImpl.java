package com.example.shibu.android_ipc.binderpool;

import android.os.RemoteException;
import android.util.Log;

import com.example.shibu.android_ipc.IMusicService;

public class MusicServiceImpl extends IMusicService.Stub {
    @Override
    public void play(String url) throws RemoteException {
        Log.d("wpstest","music play "+ url);
    }
}

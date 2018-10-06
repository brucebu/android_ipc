package com.example.shibu.android_ipc.messager;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

public class MessagerService extends Service {
    static class MessagerHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            Log.d("wpstest", ""+msg.what);
            Message message = Message.obtain();
            message.what = 21;
            try {
                msg.replyTo.send(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
    final Messenger mMessenger = new Messenger(new MessagerHandler());
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }
}

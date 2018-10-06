package com.example.shibu.android_ipc;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

import com.example.shibu.android_ipc.aidl.IAidlListener;
import com.example.shibu.android_ipc.aidl.IMyAidlInterface;
import com.example.shibu.android_ipc.aidl.MyAidlService;
import com.example.shibu.android_ipc.intent.Main2Activity;
import com.example.shibu.android_ipc.intent.MyService;
import com.example.shibu.android_ipc.messager.MessagerService;
import com.example.shibu.android_ipc.aidl.TestData;
import com.example.shibu.android_ipc.remoteView.RemoteViewsActivity;

public class MainActivity extends AppCompatActivity {
    private Button messageBtn;
    Messenger replyMessenger;
    final Handler handler = new MessengerHandler();

    private static class MessengerHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            Log.d("wpstest", "reply:" + msg.what);
        }
    };
    Messenger messenger;
    ServiceConnection messengerConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            messenger = new Messenger(service);
            replyMessenger = new Messenger(handler);
            messageBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Message  message = Message.obtain();
                    message.replyTo = replyMessenger;
//                    message.obj = new TestData(2,"haha");
                    message.what=20;
                    try {
                        messenger.send(message);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    ServiceConnection myConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d("test","1" + service.getClass().getSimpleName());
//            ((MyService.MyBinder)service).print();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    IBinder.DeathRecipient deathRecipient = new IBinder.DeathRecipient() {
        @Override
        public void binderDied() {
            if(iMyAidlInterface!=null) {
                Intent intent = new Intent(getApplicationContext(), MyAidlService.class);
                bindService(intent, myAidlConnection, BIND_AUTO_CREATE);
            }
        }
    };
    IMyAidlInterface iMyAidlInterface;
    TestData mTestData = new TestData(123,"123");
    ServiceConnection myAidlConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            try {
                service.linkToDeath(deathRecipient,0);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            iMyAidlInterface = IMyAidlInterface.Stub.asInterface(service);
            if(iMyAidlInterface!=null){
                try {
                    iMyAidlInterface.print(20);
                    iMyAidlInterface.printData(mTestData);
                    iMyAidlInterface.setListener(new IAidlListener.Stub() {
                        @Override
                        public TestData getData() throws RemoteException {
                            return mTestData;
                        }

                    });
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            iMyAidlInterface = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button activityBtn = findViewById(R.id.start_activity);
        activityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Main2Activity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("data",new TestData(20,"bushi"));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        Button serviceBtn = findViewById(R.id.start_service);
        serviceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MyService.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("data",new TestData(20,"bushi"));
                intent.putExtras(bundle);
                startService(intent);
                bindService(intent,myConnection,BIND_AUTO_CREATE);
            }
        });
        Button aidlBtn = findViewById(R.id.aidl);
        aidlBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MyAidlService.class);
                bindService(intent, myAidlConnection, BIND_AUTO_CREATE);
            }
        });
        Button crashBtn = findViewById(R.id.crash);
        crashBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(iMyAidlInterface!=null){
                    try {
                        iMyAidlInterface.startCrashTask();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        messageBtn = findViewById(R.id.messager);
        Button remoteviewBtn = findViewById(R.id.remoteview);
        remoteviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.layout_remoteview);
                remoteViews.setTextViewText(R.id.remote_text,"wps");
                Bitmap bitmap = Bitmap.createBitmap(200,200, Bitmap.Config.RGB_565);
                bitmap.eraseColor(Color.RED);
                remoteViews.setImageViewBitmap(R.id.remote_img,bitmap);
                Intent intent = new Intent(getApplicationContext(), RemoteViewsActivity.class);
                intent.putExtra("remoteview",remoteViews);
                startActivity(intent);
            }
        });
        Button musicBtn = findViewById(R.id.music);
        musicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.layout_remoteview);
                remoteViews.setTextViewText(R.id.remote_text,"wps");
                Bitmap bitmap = Bitmap.createBitmap(200,200, Bitmap.Config.RGB_565);
                bitmap.eraseColor(Color.RED);
                remoteViews.setImageViewBitmap(R.id.remote_img,bitmap);
                Intent intent = new Intent(getApplicationContext(), RemoteViewsActivity.class);
                intent.putExtra("remoteview",remoteViews);
                startActivity(intent);
            }
        });
        Button fmBtn = findViewById(R.id.fm);
        fmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.layout_remoteview);
                remoteViews.setTextViewText(R.id.remote_text,"wps");
                Bitmap bitmap = Bitmap.createBitmap(200,200, Bitmap.Config.RGB_565);
                bitmap.eraseColor(Color.RED);
                remoteViews.setImageViewBitmap(R.id.remote_img,bitmap);
                Intent intent = new Intent(getApplicationContext(), RemoteViewsActivity.class);
                intent.putExtra("remoteview",remoteViews);
                startActivity(intent);
            }
        });
        Intent intent = new Intent(this, MessagerService.class);
        bindService(intent, messengerConnection,BIND_AUTO_CREATE);
    }
}

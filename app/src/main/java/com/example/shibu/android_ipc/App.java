package com.example.shibu.android_ipc;

import android.app.ActivityManager;
import android.app.Application;
import android.os.Process;
import android.util.Log;

import java.util.List;

public class App extends Application {
    int i = 0;
    @Override
    public void onCreate() {
        super.onCreate();
        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> list = activityManager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo processInfo : list){
            if(processInfo.pid == Process.myPid()){
                Log.d("wpstest","i = "+i +" processName : " + processInfo.processName);
                i++;
            }
        }

    }
}

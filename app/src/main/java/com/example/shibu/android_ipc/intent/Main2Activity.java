package com.example.shibu.android_ipc.intent;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.shibu.android_ipc.R;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent = getIntent();
        if(intent!=null){
            Parcelable parcelable = intent.getParcelableExtra("data");
            if(parcelable!=null) {
                Log.d("wpstest",parcelable.toString());
            }
        }
    }
}

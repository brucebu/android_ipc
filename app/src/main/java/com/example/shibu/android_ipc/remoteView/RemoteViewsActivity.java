package com.example.shibu.android_ipc.remoteView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RemoteViews;

import com.example.shibu.android_ipc.R;

public class RemoteViewsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote_views);
        LinearLayout linearLayout = findViewById(R.id.remoteview_container);
        Intent intent = getIntent();
        if(intent!=null){
            RemoteViews remoteViews = intent.getParcelableExtra("remoteview");
            if(remoteViews!=null){
                View view = remoteViews.apply(this,linearLayout);
                linearLayout.addView(view);
            }
        }



    }

}

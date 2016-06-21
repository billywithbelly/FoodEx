package com.example.billywithbelly.foodex;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class LoadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
        Toast.makeText(getApplicationContext(), "Loading FoodEx", Toast.LENGTH_LONG)
                .show();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms
                Intent starterIntent = new Intent()
                        .setClass(LoadActivity.this, MainActivity.class);
                startActivity(starterIntent);
            }
        }, 5000);
    }
}

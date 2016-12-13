package com.example.billywithbelly.foodex;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

/**
 * Created by billywithbelly on 6/21/16.
 */
public class SplashActivity extends AppCompatActivity {

    private Intent mLoginIntent;
    private Intent mMainIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mMainIntent = new Intent(this, MainActivity.class);
        mLoginIntent = new Intent(this, LoginActivity.class);

        FacebookSdk.sdkInitialize(getApplicationContext(), new FacebookSdk.InitializeCallback() {
            @Override
            public void onInitialized() {
                checkFbLogin(AccessToken.getCurrentAccessToken());
            }
        });
        AppEventsLogger.activateApp(getApplication());

    }

    private void checkFbLogin(AccessToken currentToken){
        if(currentToken != null){
            startActivity(mMainIntent);
        }else{
            startActivity(mLoginIntent);
        }
        finish();
    }
}

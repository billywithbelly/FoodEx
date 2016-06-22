package com.example.billywithbelly.foodex;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    private CallbackManager callbackManager;
    public static boolean loginFlag = false;
    public static String id;
    public static AccessToken accessToken;

    //private GraphRequest loginResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!FacebookSdk.isInitialized()) {
            FacebookSdk.sdkInitialize(getApplicationContext());
            AppEventsLogger.activateApp(getApplication());
        }

        setContentView(R.layout.activity_login);

        // Login button provided by SDK
        LoginButton butFbLogin = (LoginButton) findViewById(R.id.fb_login_button);
        assert butFbLogin != null;

        callbackManager = CallbackManager.Factory.create();

        butFbLogin.setReadPermissions("email");
        butFbLogin.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                accessToken = loginResult.getAccessToken();

                Log.d("FB", "access token got.");

                //send request and call graph api

                GraphRequest request = GraphRequest.newMeRequest(
                        accessToken,
                        new GraphRequest.GraphJSONObjectCallback() {

                            //當RESPONSE回來的時候

                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {

                                //讀出姓名 ID FB個人頁面連結

                                Log.d("FB", "complete");
                                Log.d("FB", object.optString("name"));
                                Log.d("FB", object.optString("link"));
                                Log.d("FB", object.optString("id"));
                                id = object.optString("id");

                                Document document = null;
                                try {
                                    document = Jsoup.connect("http://www.facebook.com/"
                                            + id).get();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                //Elements img = document.getElementsByClass("_s0 _1k69 _ry img");
                                // Using Elements to get the class data
                                Elements img = document.getElementsByTag("img");

                                //Elements img = document.select("img[class=_s0 _1k69 _ry img]");
                                // Locate the src attribute
                                String imgSrc = img.attr("src");
                                // Download image from URL
                                InputStream input = null;
                                try {
                                    input = new URL(imgSrc).openStream();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                // Decode Bitmap
                                Bitmap bitmap = BitmapFactory.decodeStream(input);
                            }
                        });

                //包入你想要得到的資料 送出request

                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,link");
                request.setParameters(parameters);
                request.executeAsync();
                loginFlag = true;
            }

            @Override
            public void onCancel() {
                //TODO
                loginFlag = false;
            }

            @Override
            public void onError(FacebookException error) {
                error.printStackTrace();
                Toast.makeText(LoginActivity.this, "Error Login to Facebook", Toast.LENGTH_LONG).show();
            }

        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
    public Bitmap getBitmapFromUrl (String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.connect();

            InputStream input = conn.getInputStream();
            Bitmap bmp = BitmapFactory.decodeStream(input);
            return bmp;
        }catch (IOException E) {
            E.printStackTrace();
            return null;
        }
    }
    public static Bitmap getFacebookProfilePicture(String userID) throws MalformedURLException {
        URL imageURL = new URL("https://graph.facebook.com/" + userID + "/picture?type=large");
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream(imageURL.openConnection().getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bitmap;
    }

}

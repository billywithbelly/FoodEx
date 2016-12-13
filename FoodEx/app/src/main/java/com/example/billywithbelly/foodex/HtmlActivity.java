package com.example.billywithbelly.foodex;

import android.app.ProgressDialog;
import android.app.assist.AssistContent;
import android.app.SharedElementCallback;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.SearchEvent;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by billywithbelly on 6/20/16.
 */
public class HtmlActivity extends AppCompatActivity {
    String url;
    String sorts;
    ProgressDialog mProgressDialog;
    ListView htmlList;
    String[] a = new String[15];
    String[] web;
    String[] AfterSplit = new String[15];
    Bitmap[] AfterFetched = new Bitmap[15];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = this.getIntent();
        url = intent.getStringExtra("link");
        sorts = intent.getStringExtra("sorts");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_html);

        new Description().execute();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.sort_fab);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent
                        .putExtra(Intent.EXTRA_TEXT,
                                "來吃" + sorts + "摟～");
                sendIntent.setType("text/plain");
                sendIntent.setPackage("com.facebook.orca");
                try
                {
                    startActivity(sendIntent);
                }
                catch (android.content.ActivityNotFoundException ex)
                {
                    //ToastHelper.MakeShortText("Please Install Facebook Messenger");
                }
            }
        });
    }

    // Description AsyncTask
    private class Description extends AsyncTask<Void, Void, Void> {
        String desc;
        String website;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(HtmlActivity.this);
            mProgressDialog.setTitle("搜尋" + sorts + "中...");
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {

                Document document = Jsoup.connect(url).get();
                Elements description = document
                        .select("meta[name=description]");
                Elements website_search = document
                        .select("div.titleBlock a.storeTitle");

                for (Element link : website_search) {
                    website += " " + link.attr("href");
                    AfterSplit = website.split(" ");
                }
                //from AfterSplit 1 to 9
                //from AfterFetched 1 to 9
                for (int i=0; i<AfterSplit.length-1; i++) {
                    Document D = Jsoup.connect(AfterSplit[i+1])
                            .get();
                    Elements img = D.select("div[class=infoImage] img[src]");
                    String imgSrc = img.attr("src");
                    //InputStream inputUrl = new java.net.URL(imgSrc).openStream();
                    Log.d("image to parse", "" + imgSrc);
                    AfterFetched[i] = getBitmapFromUrl(imgSrc);

                    Log.d("AfterSplit", "" + AfterSplit[i+1]);
                    //Log.d("image", "" + AfterFetched[i].toString());
                    Log.d("", "");
                }
                desc = description.attr("content");
                a = desc.split("、");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {

            web = a;
            HtmlList adapter = new
                    HtmlList(HtmlActivity.this, web, AfterFetched);
            htmlList = (ListView) findViewById(R.id.activity_html);
            htmlList.setAdapter(adapter);
            htmlList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    Intent ie = new Intent(Intent.ACTION_VIEW, Uri.parse(AfterSplit[position + 1]));
                    //Log.d("AfterSplit", "" + AfterSplit[1]);
                    startActivity(ie);
                }
            });
            mProgressDialog.dismiss();
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
    }
}
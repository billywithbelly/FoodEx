package com.example.billywithbelly.foodex;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
public class BBQActivity extends AppCompatActivity {
    String url = "http://www.fonfood.com/%E6%96%B0%E7%AB%B9%E5%B8%82/%E7%87%92%E8%82%89";
    ProgressDialog mProgressDialog;
    ListView bbqlist;
    String[] a;
    String[] web;
    String[] AfterSplit = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bbq);

        new Description().execute();

    }



    // Description AsyncTask
    private class Description extends AsyncTask<Void, Void, Void> {
        String desc;
        String website;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(BBQActivity.this);
            mProgressDialog.setTitle("搜尋燒肉餐廳中...");
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
                    website +=" "+link.attr("href");
                    AfterSplit = website.split(" ");

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

            web  = a;
            BBQList adapter = new
                    BBQList(BBQActivity.this, web);
            bbqlist=(ListView)findViewById(R.id.bbqlist);
            bbqlist.setAdapter(adapter);
            bbqlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    Intent ie = new Intent(Intent.ACTION_VIEW, Uri.parse(AfterSplit[position+1]));

                    startActivity(ie);

                }
            });

            mProgressDialog.dismiss();
        }

        }





}

package com.example.billywithbelly.foodex;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by billywithbelly on 6/20/16.
 */
public class HtmlActivity extends AppCompatActivity {
    String url;
    String sorts;
    ProgressDialog mProgressDialog;
    ListView htmlList;
    String[] a;
    String[] web;
    String[] AfterSplit = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = this.getIntent();
        url = intent.getStringExtra("link");
        sorts = intent.getStringExtra("sorts");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_html);

        new Description().execute();
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
                    HtmlList(HtmlActivity.this, web);
            htmlList = (ListView) findViewById(R.id.activity_html);
            htmlList.setAdapter(adapter);
            htmlList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    Intent ie = new Intent(Intent.ACTION_VIEW, Uri.parse(AfterSplit[position + 1]));

                    startActivity(ie);
                }
            });
            mProgressDialog.dismiss();
        }
    }
}
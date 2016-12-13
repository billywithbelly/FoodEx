package com.example.billywithbelly.foodex;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SearchOnGoogle extends AppCompatActivity {
    private Button searchButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_on_google);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.search_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Quit Search", Toast.LENGTH_SHORT)
                        .show();
                SearchOnGoogle.this.onBackPressed();
            }
        });

        final EditText city = (EditText) findViewById(R.id.search_google_for_city);
        final EditText restaurant = (EditText) findViewById(R.id.search_google_for_restaurant);

        searchButton = (Button) findViewById(R.id.search_google_go);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String toSearch = city.getText().toString() + ' ' + restaurant.getText().toString();
                Uri uri = Uri.parse("http://google.com/#q=" + toSearch);
                Intent it = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(it);
            }
        });
    }

}

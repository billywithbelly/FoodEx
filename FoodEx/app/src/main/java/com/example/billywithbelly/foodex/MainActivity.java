package com.example.billywithbelly.foodex;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.app.ListActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private Context context;
    ListView list;
    String[] web = {
            "基隆市",
            "台北市",
            "新北市",
            "桃園市",
            "新竹市",
            "新竹縣",
            "台中市",
            "南投縣",
            "彰化縣",
            "雲林縣",
            "嘉義市",
            "嘉義縣",
            "台南市",
            "高雄市",
            "屏東縣",
            "宜蘭縣",
            "花蓮縣",
            "台東縣",
            "澎湖縣",
            "金門縣",
            "連江縣"
    } ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent searchIntent = new Intent();
                searchIntent.setClass(MainActivity.this, SearchOnGoogle.class);
                startActivity(searchIntent);
                Toast.makeText(getApplicationContext(), "Search On Google", Toast.LENGTH_SHORT)
                        .show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        locatelist adapter = new
                locatelist(MainActivity.this, web);
        list=(ListView)findViewById(R.id.citylist);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // 建立 Bundle 物件
               // Bundle location = new Bundle();

                // 寫入資料到 Bundle 中
              //  location.putInt("num1",position);

                Intent menu = new Intent();
                menu.setClass(MainActivity.this, MenuActivity.class)
                .putExtra("num",position);
                startActivity(menu);

            }
        });
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.drawer_my_map) {
            // jump to google map application
            Uri gmmIntentUri = Uri.parse("geo:0,0?q=餐廳");
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            Toast.makeText(this.getApplicationContext(), "Searching For Restaurants", Toast.LENGTH_SHORT)
                    .show();
            startActivity(mapIntent);
        } else if (id == R.id.drawer_explore) {
            // Search for restaurants nearby
            Uri gmmIntentUri = Uri.parse("geo:0,0?q=停車場");
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            Toast.makeText(this.getApplicationContext(), "Searching For Parking Lot", Toast.LENGTH_SHORT)
                    .show();
            startActivity(mapIntent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

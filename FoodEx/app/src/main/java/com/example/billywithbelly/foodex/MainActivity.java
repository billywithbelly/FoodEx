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
            "早午餐",
            "下午茶",
            "火鍋",
            "燒肉",
            "日式",
            "韓式",
            "吃到飽"
    } ;
    String[] link = {
            //brunch
            "http://www.fonfood.com/%E6%96%B0%E7%AB%B9%E5%B8%82/%E6%97%A9%E5%8D%88%E9%A4%90",
            //afternoon tea
            "http://www.fonfood.com/%E6%96%B0%E7%AB%B9%E5%B8%82/%E4%B8%8B%E5%8D%88%E8%8C%B6",
            //hot pot
            "http://www.fonfood.com/%E6%96%B0%E7%AB%B9%E5%B8%82/%E7%81%AB%E9%8D%8B",
            //barbeque
            "http://www.fonfood.com/%E6%96%B0%E7%AB%B9%E5%B8%82/%E7%87%92%E8%82%89",
            //japanese
            "http://www.fonfood.com/%E6%96%B0%E7%AB%B9%E5%B8%82/%E6%97%A5%E5%BC%8F",
            //korean
            "http://www.fonfood.com/%E6%96%B0%E7%AB%B9%E5%B8%82/%E9%9F%93%E5%BC%8F",
            //buffet
            "http://www.fonfood.com/%E6%96%B0%E7%AB%B9%E5%B8%82/%E5%90%83%E5%88%B0%E9%A3%BD"
    };
    Integer[] imageId = {
            R.drawable.ic_menu_gallery,
            R.drawable.ic_menu_camera,
            R.drawable.ic_menu_gallery,
            R.drawable.ic_plusone_medium_off_client,
            R.drawable.common_signin_btn_icon_disabled_focus_light,
            R.drawable.common_ic_googleplayservices,
            R.drawable.ic_menu_slideshow
    };

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
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        menulist adapter = new
                menulist(MainActivity.this, web, imageId);
        list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent html = new Intent();
                switch (position)
                {
                    case 0:
                        //brunch
                        html.setClass(MainActivity.this, HtmlActivity.class)
                                .putExtra("link", link[position])
                                .putExtra("sorts", web[position]);
                        startActivity(html);
                        break;
                    case 1:
                        //afternoon tea
                        html.setClass(MainActivity.this, HtmlActivity.class)
                                .putExtra("link", link[position])
                                .putExtra("sorts", web[position]);
                        startActivity(html);
                        break;
                    case 2:
                        //hot pot
                        html.setClass(MainActivity.this, HtmlActivity.class)
                                .putExtra("link", link[position])
                                .putExtra("sorts", web[position]);
                        startActivity(html);
                        break;
                    case 3:
                        //barbeque
                        html.setClass(MainActivity.this, HtmlActivity.class)
                                .putExtra("link", link[position])
                                .putExtra("sorts", web[position]);
                        startActivity(html);
                        break;
                    case 4:
                        //japanese
                        html.setClass(MainActivity.this, HtmlActivity.class)
                                .putExtra("link", link[position])
                                .putExtra("sorts", web[position]);
                        startActivity(html);
                        break;
                    case 5:
                        //korean
                        html.setClass(MainActivity.this, HtmlActivity.class)
                                .putExtra("link", link[position])
                                .putExtra("sorts", web[position]);
                        startActivity(html);
                        break;
                    case 6:
                        //buffet
                        html.setClass(MainActivity.this, HtmlActivity.class)
                                .putExtra("link", link[position])
                                .putExtra("sorts", web[position]);
                        startActivity(html);
                        break;
                }
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.drawer_my_map) {
            // jump to google map application
            Uri gmmIntentUri = Uri.parse("geo:0,0?m=your places");
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            Toast.makeText(this.getApplicationContext(), "Loading Google Map", Toast.LENGTH_SHORT)
                    .show();
            startActivity(mapIntent);
        } else if (id == R.id.drawer_explore) {
            // Search for restaurants nearby
            Uri gmmIntentUri = Uri.parse("geo:0,0?q=restaurants");
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            Toast.makeText(this.getApplicationContext(), "Searching For Restaurants", Toast.LENGTH_SHORT)
                    .show();
            startActivity(mapIntent);
        } else if (id == R.id.drawer_logout) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

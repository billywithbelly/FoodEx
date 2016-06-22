package com.example.billywithbelly.foodex;

import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.util.Log;
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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.app.ListActivity;
import android.os.Bundle;
import android.app.assist.AssistContent;
import android.app.SharedElementCallback;
import android.os.PersistableBundle;
import android.view.SearchEvent;
import com.facebook.messenger.MessengerUtils;
import com.facebook.messenger.MessengerThreadParams;
import com.facebook.messenger.ShareToMessengerParams;
import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.widget.ProfilePictureView;
import com.github.clans.fab.FloatingActionButton;

import org.json.JSONObject;

import static com.example.billywithbelly.foodex.R.drawable.bb;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static boolean activityFlag;
    private int floatButtonFlag;
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
        activityFlag = false;
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        */
        FloatingActionButton fabSearch = (FloatingActionButton) findViewById(R.id.menu_item1);
        assert fabSearch != null;
        fabSearch.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent searchIntent = new Intent();
                        searchIntent.setClass(MainActivity.this, SearchOnGoogle.class);
                        startActivity(searchIntent);
                    }
                });

        FloatingActionButton fabMessenger = (FloatingActionButton) findViewById(R.id.menu_item2);
        assert fabMessenger != null;
        fabMessenger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                Uri uri = Uri.parse("fb-messenger://user/");
                String peopleId = "1054916604587192";
                //Log.d("id", ""+ LoginActivity.id.toString());
                uri = ContentUris.withAppendedId(uri, Long.parseLong(peopleId));
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                */
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent
                        .putExtra(Intent.EXTRA_TEXT,
                                "吃吃吃吃吃摟～");
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
            activityFlag = true;
            startActivity(mapIntent);
        } else if (id == R.id.drawer_explore) {
            // Search for restaurants nearby
            Uri gmmIntentUri = Uri.parse("geo:0,0?q=停車場");
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            Toast.makeText(this.getApplicationContext(), "Searching For Parking Lots", Toast.LENGTH_SHORT)
                    .show();
            activityFlag = true;
            startActivity(mapIntent);
        } else if (id == R.id.account) {
            Intent signInIntent = new Intent()
                    .setClass(MainActivity.this, LoginActivity.class);
            activityFlag = true;
            startActivity(signInIntent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    protected void onRestart() {
        if (activityFlag) {
            Intent i = new Intent();
            i.setClass(MainActivity.this, MainActivity.class);
            activityFlag = false;
            startActivity(i);
        }else {
            super.onRestart();
        }
    }
}

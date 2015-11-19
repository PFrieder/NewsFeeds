package com.example.pinchasfrieder.newsfeeds;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.pinchasfrieder.newsfeeds.FindFeed.Entry;
import com.example.pinchasfrieder.newsfeeds.FindFeed.Example;
import com.example.pinchasfrieder.newsfeeds.FindFeed.ResponseData;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    EditText mEditText;
    ImageButton mImageButton;
    RecyclerView recyclerView;
    MyRecycleView myRecycleView;

    private static final String TAG = "pinchas frieder";

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

        mEditText = (EditText) findViewById(R.id.editText);
        recyclerView = (RecyclerView) findViewById(R.id.recycle_view);

    }

    public void search(View view) {

        new MyAsyncTask().execute(mEditText.getText().toString());
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

        if (id == R.id.nav_camara) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    class MyAsyncTask extends AsyncTask<String, Void, Example> {

        @Override
        protected Example doInBackground(String... params) {

//            URL url = new URL("https://ajax.googleapis.com/ajax/services/feed/find?" +
//                    "v=1.0&q=Official%20Google%20Blog&userip=INSERT-USER-IP");
//            URLConnection connection = url.openConnection();
//            connection.addRequestProperty("Referer", /* Enter the URL of your site here */);
//
//            String line;
//            StringBuilder builder = new StringBuilder();
//            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//            while((line = reader.readLine()) != null) {
//                builder.append(line);
//            }
//
//            JSONObject json = new JSONObject(builder.toString());
//// now have some fun with the results...
//URLEncoder.encode(params[0], "UTF-8")
            Example example = null;
            try {
                URL url = new URL("https://ajax.googleapis.com/ajax/services/feed/find?v=1.0&q=" + URLEncoder.encode(params[0], "UTF-8"));
                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder()
                        .url(url)
                        .build();
                Response okhttpResponse =
                        client.newCall(request).execute();
                Log.d(TAG, "setRequestProperty");
                // String apiString = readStream(connection.getInputStream());
                ObjectMapper objectMapper = new ObjectMapper();
                example = objectMapper.readValue(okhttpResponse.body().byteStream(), Example.class);


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (JsonParseException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return example;
        }

        @Override
        protected void onPostExecute(Example example) {
            super.onPostExecute(example);
            Log.d(TAG, "onPostExecute");

            if (example != null) {
                Log.d(TAG, "String from objectMapper = " + example.getResponseData().getEntries().get(0).getTitle());
                recyclerView.setAdapter(new MyRecycleView(MainActivity.this, example.getResponseData().getEntries()));
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            }else {
                Log.e(TAG, "entry = null");
            }
        }
    }
}

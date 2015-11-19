package com.example.pinchasfrieder.newsfeeds;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.pinchasfrieder.newsfeeds.LoadFeeds.Example;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class Main2Activity extends AppCompatActivity {
    RecyclerView recycleView;
    LoadAdapter adapter;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
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
        recycleView = (RecyclerView) findViewById(R.id.recycle_view2);
        Bundle bundle = getIntent().getExtras();
        new MyAsyncTask().execute(bundle.getString("key"));
    }

    class MyAsyncTask extends AsyncTask<String, Void, Example> {

        private static final String TAG = "pinchas frieder";

        @Override
        protected Example doInBackground(String... params) {

//URLEncoder.encode(params[0], "UTF-8")
            Example example = null;
            try {
                URL url = new URL("https://ajax.googleapis.com/ajax/services/feed/load?v=1.0&q=" + URLEncoder.encode(params[0], "UTF-8"));
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
                Log.d(TAG, "String from objectMapper = " + example.getResponseData().getFeed().getTitle());
                recycleView.setAdapter(new LoadAdapter(Main2Activity.this, example.getResponseData().getFeed().getEntries()));
                recycleView.setLayoutManager(new LinearLayoutManager(Main2Activity.this));
            } else {
                Log.e(TAG, "entry = null");
            }
        }
    }
}




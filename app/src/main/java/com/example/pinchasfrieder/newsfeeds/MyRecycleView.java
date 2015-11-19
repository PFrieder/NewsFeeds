package com.example.pinchasfrieder.newsfeeds;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.pinchasfrieder.newsfeeds.FindFeed.Entry;
import com.example.pinchasfrieder.newsfeeds.FindFeed.Example;
import com.example.pinchasfrieder.newsfeeds.FindFeed.ResponseData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Pinchas Frieder on 11/15/2015.
 */
public class MyRecycleView extends RecyclerView.Adapter<MyRecycleView.ViewHolder> {
    Context context;
    private static final String TAG = "pinchas frieder";
    LayoutInflater layoutInflater;
    List<Entry> findFeeds = Collections.emptyList();
    List<Integer> checkList = new ArrayList<>();

    public MyRecycleView(Context context, List<Entry> findFeeds) {

        layoutInflater = LayoutInflater.from(context);
        this.findFeeds = findFeeds;
        this.context = context;
        Log.d(TAG, "constructer");


    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.row, parent, false);
        Log.d(TAG, "onCreateViewHolder");
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Entry entry = findFeeds.get(position);

        SQLiteDatabase db = new SQLiteHelper(context).getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.COLUMN_NAME, entry.getUrl());

        // Inserting Row
        db.insert(SQLiteHelper.TABLE_FIND, null, values);

        //db.close(); // Closing database connection


        if (position % 2 == 0) {
            holder.itemView.setBackgroundColor(Color.LTGRAY);
        } else {
            holder.itemView.setBackgroundColor(Color.WHITE);
        }
        Log.d(TAG, "onBindViewHolder");
        holder.header.setText(entry.getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.checkBox.isChecked()) {
                    SQLiteDatabase db = new SQLiteHelper(context).getWritableDatabase();
                    String selectQuery= "SELECT * FROM " + SQLiteHelper.TABLE_FIND+" ORDER BY " + SQLiteHelper.COLUMN_ID + " WHERE " + SQLiteHelper.COLUMN_ID
                     + " = " + position + 1 + " DESC LIMIT 1";

                    Cursor cursor = db.rawQuery(selectQuery, null);
                    String str = "";
                    if(cursor.moveToFirst())
                        str  =  cursor.getString(0);
                    cursor.close();
                    ContentValues values = new ContentValues();
                    values.put(SQLiteHelper.COLUMN_NAME, entry.getUrl());


                    checkList.add(Integer.parseInt(str));
                }


                Intent intent = new Intent(v.getContext(), Main2Activity.class);
                intent.putExtra("key", entry.getUrl());
                v.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return findFeeds.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView header;
        CheckBox checkBox;

        public ViewHolder(View itemView) {
            super(itemView);
            header = (TextView) itemView.findViewById(R.id.textView2);
            checkBox = (CheckBox) itemView.findViewById(R.id.checkBox);
        }
    }
}

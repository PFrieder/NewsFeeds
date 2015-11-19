package com.example.pinchasfrieder.newsfeeds;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pinchasfrieder.newsfeeds.LoadFeeds.Entry;
import com.example.pinchasfrieder.newsfeeds.LoadFeeds.Example;

import java.util.Collections;
import java.util.List;

/**
 * Created by Pinchas Frieder on 11/19/2015.
 */
public class LoadAdapter extends RecyclerView.Adapter<LoadAdapter.MyViewHolder> {
    String myDate = DateUtils.getRelativeTimeSpanString(System.currentTimeMillis()).toString();
    List<Entry> loadFeed = Collections.emptyList();
    LayoutInflater layoutInflater;

    public LoadAdapter(Context context, List<Entry> loadFeed) {
        layoutInflater = LayoutInflater.from(context);
        this.loadFeed = loadFeed;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.load_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Entry entry = loadFeed.get(position);
        if (position %2 == 0) {
            holder.itemView.setBackgroundColor(Color.LTGRAY);
        }else {
            holder.itemView.setBackgroundColor(Color.WHITE);
        }
        holder.news.setText(entry.getContentSnippet().toString());
        holder.date.setText(myDate);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(entry.getLink()));
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return loadFeed.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView news, date;
        public MyViewHolder(View itemView) {
            super(itemView);
            news = (TextView) itemView.findViewById(R.id.textView3);
            date = (TextView) itemView.findViewById(R.id.textView4);
        }
    }
}

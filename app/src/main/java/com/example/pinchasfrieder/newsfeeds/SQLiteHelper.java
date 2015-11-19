package com.example.pinchasfrieder.newsfeeds;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Pinchas Frieder on 11/19/2015.
 */
public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String TAG = SQLiteHelper.class.getSimpleName();

    public static final String TABLE_FIND = "find";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_Checked = "checked";


    private static final String DATABASE_NAME = "news.db";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE =
            "CREATE TABLE " + TABLE_FIND
                    + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUMN_NAME + " TEXT, "
                    + COLUMN_Checked + " INTEGER " +
                    ");";
    public static final String TABLE_LOAD = "load.db";
    public static final String COLUMN_LOAD_ID = "_id";
    public static final String COLUMN_LOAD_ARTICLE = "article";
    public static final String COLUMN_LOAD_DATE = "date";
    private static final String LOAD_DATABASE_CREATE =
            "CREATE TABLE " + TABLE_LOAD
                    + " (" + COLUMN_LOAD_ID + " INTEGER, "
                    + COLUMN_LOAD_ARTICLE + " TEXT, "
                    + COLUMN_LOAD_DATE + " INTEGER " +
                    ");";

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "DB created");
        db.execSQL(DATABASE_CREATE);
        db.execSQL(LOAD_DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "DB upgrading. Dropping old table...");
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOAD);
        onCreate(db);
    }
}

package com.example.nokra;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ActivityDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "activity_tracker.db";
    private static final int DATABASE_VERSION = 1;

    public ActivityDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE activities (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " + // Renamed from id to _id
                "name TEXT NOT NULL, " +
                "timestamp DATETIME NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS activities");
        onCreate(db);
    }

    public void addActivity(String name, String timestamp) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("timestamp", timestamp);
        db.insert("activities", null, values);
    }
}

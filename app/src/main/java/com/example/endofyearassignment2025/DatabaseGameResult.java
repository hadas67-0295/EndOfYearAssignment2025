package com.example.endofyearassignment2025;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseGameResult extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "game_results.db";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "game_results";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_SCORE = "score";
    public static final String COLUMN_MOTIVATION = "motivation";

    public DatabaseGameResult(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_USERNAME + " TEXT,"
                + COLUMN_SCORE + " INTEGER,"
                + COLUMN_MOTIVATION + " TEXT"
                + ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void insertResult(String username, int score, String motivation) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_SCORE, score);
        values.put(COLUMN_MOTIVATION, motivation);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public Cursor getAllResults() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_NAME, null, null, null, null, null, COLUMN_ID + " DESC");
    }
}

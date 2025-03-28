package com.example.final_application;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper  {
    private static final String DATABASE_NAME = "learnjs.db";
    private static final int DATABASE_VERSION = 1;

    // User table
    public static final String TABLE_USER = "user_table";
    public static final String COLUMN_USER_ID = "id";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";

    // Test unit table for collecting score
    public static final String TABLE_TEST_SCORES = "test_unit_table";
    public static final String COLUMN_TEST_ID = "id";
    public static final String COLUMN_UNIT = "unit";
    public static final String COLUMN_SCORE = "score";
    public static final String COLUMN_USER_FK = "username"; // Foreign key to username from user_table

    private static final String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER
            + " (" +
            COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_EMAIL + " TEXT, " +
            COLUMN_USERNAME + " TEXT, " +
            COLUMN_PASSWORD + " TEXT );";

    private static final String CREATE_TEST_SCORES_TABLE = "CREATE TABLE " + TABLE_TEST_SCORES
            + " (" +
            COLUMN_TEST_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_UNIT + " TEXT, " +
            COLUMN_SCORE + " INTEGER, " +
            COLUMN_USER_FK + " TEXT, " + // Username as Foreign Key
            "FOREIGN KEY(" + COLUMN_USER_FK + ") REFERENCES " + TABLE_USER + "(" + COLUMN_USERNAME + ") );";

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_USER_TABLE);
        sqLiteDatabase.execSQL(CREATE_TEST_SCORES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_TEST_SCORES);
        onCreate(sqLiteDatabase);
    }

    // Insert user table
    public boolean insertUserData(String email, String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_PASSWORD, password);
        long result = db.insert(TABLE_USER, null, values);
        db.close();
        return result != -1;
    }

    // Insert test unit table
    public boolean insertTestScore(String username, String unit, int score) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_UNIT, unit);
        values.put(COLUMN_SCORE, score);
        values.put(COLUMN_USER_FK, username); // Store the username for reference
        long result = db.insert(TABLE_TEST_SCORES, null, values);
        db.close();
        return result != -1;
    }

    // check Login
    public boolean checkLogin(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_USER + " WHERE " + COLUMN_USERNAME + " = ? AND " + COLUMN_PASSWORD + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{username, password});

        // check if this username already have
        if (cursor != null && cursor.getCount() > 0) {
            cursor.close();
            return true; // if already have (can login)
        } else {
            cursor.close();
            return false; // if not
        }
    }

    // check dup email & dup username
    public boolean checkUserExists(String username, String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_USER + " WHERE " + COLUMN_USERNAME + " = ? OR " + COLUMN_EMAIL + " = ?";
        Cursor cursor = db.rawQuery(query, new String[] { username, email });

        boolean exists = false;
        if (cursor != null && cursor.getCount() > 0) {
            exists = true; // if cursor has count (mean duplicate) set to true
        }
        cursor.close();
        return exists;
    }

}

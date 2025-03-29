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
    public static final String COLUMN_USER_FK = "userid"; // Foreign key to username from user_table

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
            COLUMN_USER_FK + " INTEGER, " + // userid as Foreign Key
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
        // Get userId from username
        int userId = getUserIdByUsername(username);
        if (userId == -1) {
            return false;
        }
        SQLiteDatabase db = this.getWritableDatabase();
        // Query to check old score
        String query = "SELECT " + COLUMN_SCORE + " FROM " + TABLE_TEST_SCORES +
                " WHERE " + COLUMN_USER_FK + " = ? AND " + COLUMN_UNIT + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(userId), unit});

        if (cursor.moveToFirst()) {
            int existScore = cursor.getInt(0);
            cursor.close();

            // Check best attempt
            if (score <= existScore) {
                db.close();
                return false;
            }

            ContentValues values = new ContentValues();
            values.put(COLUMN_SCORE, score);
            int rowsUpdated = db.update(TABLE_TEST_SCORES, values,
                    COLUMN_USER_FK + " = ? AND " + COLUMN_UNIT + " = ?",
                    new String[]{String.valueOf(userId), unit});
            db.close();
            return rowsUpdated > 0; // ถ้า rows อัปเดตมากกว่า 0 ให้ return true
        }

        // ถ้าไม่มี record เก่าก็ insert ใหม่
        cursor.close();
        ContentValues values = new ContentValues();
        values.put(COLUMN_UNIT, unit);
        values.put(COLUMN_SCORE, score);
        values.put(COLUMN_USER_FK, userId); // ใช้ userId สำหรับ reference
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

    public int getUnitScore(String username, String unit) {
        int userId = getUserIdByUsername(username);
        if (userId == -1) return 0;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT MAX(" + COLUMN_SCORE + ") FROM " + TABLE_TEST_SCORES +
                        " WHERE " + COLUMN_USER_FK + " = ? AND " + COLUMN_UNIT + " = ?",
                new String[]{String.valueOf(userId), unit});
        int score = 0;
        if (cursor.moveToFirst() && !cursor.isNull(0)) {
            score = cursor.getInt(0);
        }
        cursor.close();
        return score;
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

    public int getUserIdByUsername(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + COLUMN_USER_ID + " FROM " + TABLE_USER + " WHERE " + COLUMN_USERNAME + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{username});

        int userId = -1; // -1 = not found username
        if (cursor.moveToFirst()) {
            userId = cursor.getInt(0);
        }
        cursor.close();
        return userId;
    }

}

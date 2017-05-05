package com.robertkiszelirk.habittracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.robertkiszelirk.habittracker.HabitDbContract.*;

class HabitDbHelper extends SQLiteOpenHelper {

    /* DATABASE NAME */
    private static final String DATABASE_NAME = "habittracker.db";

    /* DATABASE VERSION */
    private static final int DATABASE_VERSION = 1;

    /* DATABASE */
    private SQLiteDatabase sqLiteDatabase;

    HabitDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        /* CREATE NEW TABLE */
        createTable(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        /* DELETE CURRENT TABLE */
        String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS " + HabitDbEntry.DB_HABIT_TABLE_NAME;
        db.execSQL(SQL_DELETE_TABLE);
        /* CREATE NEW TABLE */
        createTable(db);
    }

    void insertValue(ContentValues contentValues) {
        sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.insert(HabitDbEntry.DB_HABIT_TABLE_NAME, null, contentValues);
    }

    Cursor readHabit(int i) {
        /* BASE CURSOR */
        Cursor cursor;
        /* QUERY SELECTION */
        String selection = HabitDbEntry.DB_HABIT_ID + "=?";
        /* QUERY SELECTION ARGS */
        String[] sArgs = new String[]{Integer.toString(i)};
        /* GET DATABASE */
        sqLiteDatabase = getReadableDatabase();
        /* FILL CURSOR */
        cursor = sqLiteDatabase.query(true,
                HabitDbEntry.DB_HABIT_TABLE_NAME,
                null,
                selection,
                sArgs,
                null,
                null,
                null,
                null);
        /* CURSOR GET TO FIRST ITEM */
        cursor.moveToFirst();
        /* CLOSE DATABASE */
        sqLiteDatabase.close();
        /* RETURN STRING */
        return cursor;
    }

    private void createTable(SQLiteDatabase sqLiteDatabase) {
        /* CREATE TABLE */
        /* CREATE STRING FOR THE SQL STATEMENT */
        String SQL_CREATE_HABIT_TRACKER_TABLE = "CREATE TABLE " + HabitDbEntry.DB_HABIT_TABLE_NAME + "(" +
                HabitDbEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                HabitDbEntry.DB_HABIT_NAME + " TEXT NOT NULL, " +
                HabitDbEntry.DB_HABIT_LENGTH + " INT NOT NULL DEFAULT 0);";
        /* CREATE DATABASE */
        sqLiteDatabase.execSQL(SQL_CREATE_HABIT_TRACKER_TABLE);
    }
}

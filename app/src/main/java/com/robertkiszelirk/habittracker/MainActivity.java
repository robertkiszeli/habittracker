package com.robertkiszelirk.habittracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import static com.robertkiszelirk.habittracker.HabitDbContract.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView checkDatabase = (TextView) findViewById(R.id.check_db);

        /* CREATE DATABASE HELPER */
        HabitDbHelper habitDbHelper = new HabitDbHelper(this);

        /* CREATE VALUE TO INSERT */
        ContentValues values = new ContentValues();
        values.put(HabitDbEntry.DB_HABIT_NAME, "programing");
        values.put(HabitDbEntry.DB_HABIT_LENGTH, 4);

        /* INSERT VALUE */
        habitDbHelper.insertValue(values);

        /* GET CURSOR */
        Cursor cursor = habitDbHelper.readHabit(1);

        /* BUILD UP RETURN STRING */
        String string = "I usually " + cursor.getString(1) + " " + cursor.getInt(2) + " hour a day.";

        /* CLOSE CURSOR */
        cursor.close();

        /* CHECK INSERT */
        checkDatabase.setText(string);
    }
}

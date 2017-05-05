package com.robertkiszelirk.habittracker;

import android.provider.BaseColumns;

class HabitDbContract {

    static final class HabitDbEntry implements BaseColumns {

        /* TABLE NAME */
        static final String DB_HABIT_TABLE_NAME = "habits";

        /* HABITS COLUMNS NAME */
        static final String DB_HABIT_ID = BaseColumns._ID;
        static final String DB_HABIT_NAME = "name";
        static final String DB_HABIT_LENGTH = "length";
    }
}

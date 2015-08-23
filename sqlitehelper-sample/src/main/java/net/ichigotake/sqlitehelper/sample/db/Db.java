package net.ichigotake.sqlitehelper.sample.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import net.ichigotake.sqlitehelper.DatabaseHelper;

public class Db {

    private static String URI_UPDATED_ITEMS;
    private static SQLiteOpenHelper sqLiteOpenHelper;

    public static synchronized SQLiteDatabase getWritableDatabase(Context context) {
        if (sqLiteOpenHelper == null) {
            sqLiteOpenHelper = new DatabaseHelper(context, new DbConfiguration());
        }
        return sqLiteOpenHelper.getWritableDatabase();
    }

}

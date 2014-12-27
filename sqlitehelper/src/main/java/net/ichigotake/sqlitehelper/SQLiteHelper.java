package net.ichigotake.sqlitehelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class SQLiteHelper extends android.database.sqlite.SQLiteOpenHelper {
    
    private static final String DATABASE_NAME = "stub.db";
    private static final int DATABASE_VERSION = 0;

    public static void initialize(Context context) {
        Cache.applicationContext = context;
        // Call for onCreate or onUpgrade
        new SQLiteHelper(context).getReadableDatabase();
    }
    
    public static void destroy() {
        Cache.applicationContext = null;
    }

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

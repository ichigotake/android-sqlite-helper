package net.ichigotake.sqlitehelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import net.ichigotake.sqlitehelper.ddl.CreateTable;
import net.ichigotake.sqlitehelper.ddl.Table;

public class SQLiteOpenHelper extends android.database.sqlite.SQLiteOpenHelper {
    
    public static void initialize(Context context, Configuration configuration) {
        Cache.applicationContext = context;
        Cache.configuration = configuration;
        // Call for onCreate or onUpgrade
        new SQLiteOpenHelper(context).getReadableDatabase();
    }

    public static void destroy() {
        Cache.applicationContext = null;
    }

    private static Configuration getConfiguration() {
        Configuration configuration = Cache.getConfiguration();
        if (configuration == null) {
            throw new IllegalStateException("Must call to SQLiteOpenHelper#initialize!");
        }
        return configuration;
    }

    public SQLiteOpenHelper(Context context) {
        super(context, getConfiguration().getDatabaseName(), null, getConfiguration().getDatabaseVersion());
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        for (Table table : Cache.getConfiguration().getDatabaseTables()) {
            new CreateTable(db, table.getTableSchema()).execute();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        for (Table table : Cache.getConfiguration().getDatabaseTables()) {
            if (oldVersion <= table.getSenseVersion() && table.getSenseVersion() < newVersion) {
                new CreateTable(db, table.getTableSchema()).execute();
            }
        }
    }
}

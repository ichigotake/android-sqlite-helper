package net.ichigotake.sqlitehelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    
    private final DatabaseConfiguration configuration;

    public DatabaseHelper(Context context, DatabaseConfiguration configuration) {
        super(context, configuration.getDatabaseName(), null, configuration.getDatabaseVersion());
        this.configuration = configuration;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
		new DatabaseMigrationHelper().onCreate(db, configuration);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        new DatabaseMigrationHelper().onUpgrade(db, oldVersion, newVersion, configuration);
    }

    public DatabaseConfiguration getConfiguration() {
        return configuration;
    }

}

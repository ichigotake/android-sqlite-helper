package net.ichigotake.sqlitehelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseHelper extends android.database.sqlite.SQLiteOpenHelper {
    
    private final Configuration configuration;

    public DatabaseHelper(Context context, Configuration configuration) {
        super(context, configuration.getDatabaseName(), null, configuration.getDatabaseVersion());
        this.configuration = configuration;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        MigrationHelper.onCreate(db, configuration);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        MigrationHelper.onUpgrade(db, oldVersion, newVersion, configuration);
    }

    public Configuration getConfiguration() {
        return configuration;
        
    }
}

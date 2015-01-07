package net.ichigotake.sqlitehelper;

import android.database.sqlite.SQLiteDatabase;

public interface MigrationCallback {
    
    void onAfterCreateTable(SQLiteDatabase database);
    
}

package net.ichigotake.sqlitehelper;

import android.database.sqlite.SQLiteDatabase;

public class NoMigrationCallback implements MigrationCallback {
    
    @Override
    public void onAfterCreateTable(SQLiteDatabase database) {
        
    }
}

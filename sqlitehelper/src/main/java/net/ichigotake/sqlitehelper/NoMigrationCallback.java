package net.ichigotake.sqlitehelper;

import android.database.sqlite.SQLiteDatabase;

public class NoMigrationCallback implements MigrationCallback {
    
    @Override
    public void onAfterCreate(SQLiteDatabase database) {
        
    }

    @Override
    public void onAfterUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {

    }
}

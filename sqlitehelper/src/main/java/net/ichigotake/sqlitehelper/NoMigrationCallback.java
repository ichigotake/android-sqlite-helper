package net.ichigotake.sqlitehelper;

import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

public class NoMigrationCallback implements MigrationCallback {
    
    @Override
    public void onAfterCreate(@NonNull SQLiteDatabase database, @NonNull DatabaseConfiguration configuration) {
        
    }

    @Override
    public void onAfterUpgrade(@NonNull SQLiteDatabase database, int oldVersion, int newVersion, @NonNull DatabaseConfiguration configuration) {

    }
}

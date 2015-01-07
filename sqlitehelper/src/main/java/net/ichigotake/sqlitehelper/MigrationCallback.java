package net.ichigotake.sqlitehelper;

import android.database.sqlite.SQLiteDatabase;

public interface MigrationCallback {

    void onAfterCreate(SQLiteDatabase database);

    void onAfterUpgrade(SQLiteDatabase database, int oldVersion, int newVersion);

}

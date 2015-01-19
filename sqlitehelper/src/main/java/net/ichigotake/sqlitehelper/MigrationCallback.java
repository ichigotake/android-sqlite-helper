package net.ichigotake.sqlitehelper;

import android.database.sqlite.SQLiteDatabase;

public interface MigrationCallback {

    void onAfterCreate(SQLiteDatabase database, Configuration configuration);

    void onAfterUpgrade(SQLiteDatabase database, int oldVersion, int newVersion, Configuration configuration);

}

package net.ichigotake.sqlitehelper;

import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

public interface MigrationCallback {

    void onAfterCreate(@NonNull SQLiteDatabase database, @NonNull DatabaseConfiguration configuration);

    void onAfterUpgrade(
            @NonNull SQLiteDatabase database,
            int oldVersion,
            int newVersion,
            @NonNull DatabaseConfiguration configuration);

}

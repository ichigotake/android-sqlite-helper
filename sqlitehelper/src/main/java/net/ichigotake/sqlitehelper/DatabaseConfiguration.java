package net.ichigotake.sqlitehelper;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;

import net.ichigotake.sqlitehelper.schema.DatabaseTable;

import java.util.List;

public interface DatabaseConfiguration {

    @NonNull
    List<DatabaseTable> getDatabaseTables();

    @IntRange(from = 0, to = Integer.MAX_VALUE)
    int getDatabaseVersion();

    @NonNull
    String getDatabaseName();

    @NonNull
    MigrationCallback getMigrationCallback();
    
}

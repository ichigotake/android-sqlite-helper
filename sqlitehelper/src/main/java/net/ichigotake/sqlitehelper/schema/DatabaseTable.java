package net.ichigotake.sqlitehelper.schema;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;

public interface DatabaseTable {

    @NonNull
    String getTableName();

    @IntRange(from = 0, to = Integer.MAX_VALUE)
    int getCreatedVersion();

    @NonNull
    TableSchema getTableSchema();

}

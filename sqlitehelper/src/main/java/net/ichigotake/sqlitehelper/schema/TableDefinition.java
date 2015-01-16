package net.ichigotake.sqlitehelper.schema;

import android.database.sqlite.SQLiteDatabase;

public interface TableDefinition {

    String getTableName();
    
    int getCreatedVersion();

    TableSchema getTableSchema();

    Table getTable(SQLiteDatabase database);

}

package net.ichigotake.sqlitehelper.schema;

import android.database.sqlite.SQLiteDatabase;

import net.ichigotake.sqlitehelper.table.RowMapper;

public interface TableDefinition {

    String getTableName();
    
    int getCreatedVersion();

    TableSchema getTableSchema();

    RowMapper getTable(SQLiteDatabase database);

}

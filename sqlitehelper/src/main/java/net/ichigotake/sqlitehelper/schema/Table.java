package net.ichigotake.sqlitehelper.schema;

import android.database.sqlite.SQLiteDatabase;

import net.ichigotake.sqlitehelper.dml.TableQuery;

public interface Table {

    String getTableName();
    
    int getCreatedVersion();

    TableSchema getTableSchema();

    TableQuery createTableQuery(SQLiteDatabase database);

}

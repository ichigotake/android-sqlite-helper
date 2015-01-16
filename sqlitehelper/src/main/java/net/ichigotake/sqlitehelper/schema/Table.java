package net.ichigotake.sqlitehelper.schema;

import android.database.Cursor;

public interface Table<T> {

    String getTableName();
    
    int getCreatedVersion();
    
    TableSchema getTableSchema();
    
    T retrieveItem(Cursor cursor);

}

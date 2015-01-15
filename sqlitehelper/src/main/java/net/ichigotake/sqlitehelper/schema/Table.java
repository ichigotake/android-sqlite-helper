package net.ichigotake.sqlitehelper.schema;

import java.util.List;
import android.database.Cursor;

public interface Table<T> {
    
    String getTableName();
    
    int getSenseVersion();
    
    TableSchema getTableSchema();
    
    List<TableField> getTableFields();

    T retrieveItem(Cursor cursor);

}

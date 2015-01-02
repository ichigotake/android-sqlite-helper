package net.ichigotake.sqlitehelper.schema;

import java.util.List;

public interface Table {
    
    String getTableName();
    
    int getSenseVersion();
    
    TableSchema getTableSchema();
    
    List<TableField> getTableFields();
}

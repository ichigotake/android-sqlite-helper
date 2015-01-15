package net.ichigotake.sqlitehelper.schema;

public interface Table {
    
    String getTableName();
    
    int getCreatedVersion();
    
    TableSchema getTableSchema();
    
}

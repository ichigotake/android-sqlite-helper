package net.ichigotake.sqlitehelper.schema;

public interface Table {
    
    String getTableName();
    
    int getSenseVersion();
    
    TableSchema getTableSchema();
    
}

package net.ichigotake.sqlitehelper.ddl;

public interface Table {
    
    String getTableName();
    
    int getSenseVersion();
    
    TableSchema getTableSchema();
}

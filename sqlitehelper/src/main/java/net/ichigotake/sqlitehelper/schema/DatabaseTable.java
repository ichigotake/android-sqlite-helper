package net.ichigotake.sqlitehelper.schema;

public interface DatabaseTable {

    String getTableName();
    
    int getCreatedVersion();

    TableSchema getTableSchema();

}

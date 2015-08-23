package net.ichigotake.sqlitehelper;

import net.ichigotake.sqlitehelper.schema.DatabaseTable;

import java.util.List;

public interface Configuration {
    
    List<DatabaseTable> getDatabaseTables();
    
    int getDatabaseVersion();
    
    String getDatabaseName();

    MigrationCallback getMigrationCallback();
    
}

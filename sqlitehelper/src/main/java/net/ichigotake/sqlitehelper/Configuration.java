package net.ichigotake.sqlitehelper;

import net.ichigotake.sqlitehelper.schema.Table;

import java.util.List;

public interface Configuration {
    
    List<Table> getDatabaseTables();
    
    int getDatabaseVersion();
    
    String getDatabaseName();

    MigrationCallback getMigrationCallback();
    
}

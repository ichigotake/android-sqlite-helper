package net.ichigotake.sqlitehelper;

import net.ichigotake.sqlitehelper.schema.TableDefinition;

import java.util.List;

public interface Configuration {
    
    List<TableDefinition> getDatabaseTables();
    
    int getDatabaseVersion();
    
    String getDatabaseName();

    MigrationCallback getMigrationCallback();
    
}

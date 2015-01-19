package net.ichigotake.sqlitehelper;

import net.ichigotake.sqlitehelper.schema.TableDefinition;

import java.util.Arrays;
import java.util.List;

public class MockConfiguration implements Configuration {
    
    @Override
    public List<TableDefinition> getDatabaseTables() {
        return Arrays.<TableDefinition>asList(
                new MockTableDefinition()
        );
    }

    @Override
    public int getDatabaseVersion() {
        return 1;
    }

    @Override
    public String getDatabaseName() {
        return "mock.db";
    }

    @Override
    public MigrationCallback getMigrationCallback() {
        return new NoMigrationCallback();
    }
}

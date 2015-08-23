package net.ichigotake.sqlitehelper;

import android.support.annotation.NonNull;

import net.ichigotake.sqlitehelper.schema.DatabaseTable;

import java.util.Arrays;
import java.util.List;

public class MockConfiguration implements DatabaseConfiguration {
    
    @NonNull
    @Override
    public List<DatabaseTable> getDatabaseTables() {
        return Arrays.<DatabaseTable>asList(
                new MockTableDefinition()
        );
    }

    @Override
    public int getDatabaseVersion() {
        return 1;
    }

    @NonNull
    @Override
    public String getDatabaseName() {
        return "mock.db";
    }

    @NonNull
    @Override
    public MigrationCallback getMigrationCallback() {
        return new NoMigrationCallback();
    }
}

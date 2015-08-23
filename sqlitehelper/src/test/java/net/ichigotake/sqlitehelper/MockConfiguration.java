package net.ichigotake.sqlitehelper;

import android.support.annotation.NonNull;

import net.ichigotake.sqlitehelper.schema.TableDefinition;

import java.util.Arrays;
import java.util.List;

public class MockConfiguration implements Configuration {
    
    @NonNull
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

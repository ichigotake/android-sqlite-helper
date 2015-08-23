package net.ichigotake.sqlitehelper.sample.db;

import android.support.annotation.NonNull;

import net.ichigotake.sqlitehelper.DatabaseConfiguration;
import net.ichigotake.sqlitehelper.MigrationCallback;
import net.ichigotake.sqlitehelper.NoMigrationCallback;
import net.ichigotake.sqlitehelper.schema.DatabaseTable;

import java.util.Arrays;
import java.util.List;

class DbConfiguration implements DatabaseConfiguration {

    @NonNull
    @Override
    public List<DatabaseTable> getDatabaseTables() {
        return Arrays.asList(
                new TodoItemsTable(),
                new TodoListsTable()
        );
    }

    @Override
    public int getDatabaseVersion() {
        return 1;
    }

    @NonNull
    @Override
    public String getDatabaseName() {
        return "todo";
    }

    @NonNull
    @Override
    public MigrationCallback getMigrationCallback() {
        return new NoMigrationCallback();
    }

}

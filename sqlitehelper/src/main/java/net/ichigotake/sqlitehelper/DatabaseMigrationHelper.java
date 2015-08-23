package net.ichigotake.sqlitehelper;

import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import net.ichigotake.sqlitehelper.ddl.AlterTable;
import net.ichigotake.sqlitehelper.ddl.CreateIndex;
import net.ichigotake.sqlitehelper.ddl.CreateTable;
import net.ichigotake.sqlitehelper.schema.DatabaseTable;

public class DatabaseMigrationHelper {

    public void onCreate(@NonNull SQLiteDatabase db, @NonNull DatabaseConfiguration configuration) {
        for (DatabaseTable databaseTable : configuration.getDatabaseTables()) {
            new CreateTable(db, databaseTable.getTableSchema()).createTableIfNotExists();
        }
        configuration.getMigrationCallback().onAfterCreate(db, configuration);
    }

    public void onUpgrade(
            @NonNull SQLiteDatabase db,
            int oldVersion,
            int newVersion,
            @NonNull DatabaseConfiguration configuration) {
        AlterTable alterTable = new AlterTable(db);
        for (DatabaseTable databaseTable : configuration.getDatabaseTables()) {
            if (oldVersion <= databaseTable.getCreatedVersion() && databaseTable.getCreatedVersion() <= newVersion) {
                new CreateTable(db, databaseTable.getTableSchema()).createTableIfNotExists();
            }
            new CreateIndex(db, databaseTable.getTableSchema()).createIndexIfNotExists();
            alterTable.addColumnIfNotExists(databaseTable);
        }
        configuration.getMigrationCallback().onAfterUpgrade(db, oldVersion, newVersion, configuration);
    }

}

package net.ichigotake.sqlitehelper;

import android.database.sqlite.SQLiteDatabase;

import net.ichigotake.sqlitehelper.ddl.AlterTable;
import net.ichigotake.sqlitehelper.ddl.CreateIndex;
import net.ichigotake.sqlitehelper.ddl.CreateTable;
import net.ichigotake.sqlitehelper.schema.TableDefinition;

public class MigrationHelper {

    public void onCreate(SQLiteDatabase db, Configuration configuration) {
        for (TableDefinition tableDefinition : configuration.getDatabaseTables()) {
            new CreateTable(db, tableDefinition.getTableSchema()).createTableIfNotExists();
        }
        configuration.getMigrationCallback().onAfterCreate(db, configuration);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion, Configuration configuration) {
        AlterTable alterTable = new AlterTable(db);
        for (TableDefinition tableDefinition : configuration.getDatabaseTables()) {
            if (oldVersion <= tableDefinition.getCreatedVersion() && tableDefinition.getCreatedVersion() <= newVersion) {
                new CreateTable(db, tableDefinition.getTableSchema()).createTableIfNotExists();
            }
            new CreateIndex(db, tableDefinition.getTableSchema()).createIndexIfNotExists();
            alterTable.addColumnIfNotExists(tableDefinition);
        }
        configuration.getMigrationCallback().onAfterUpgrade(db, oldVersion, newVersion, configuration);
    }

}

package net.ichigotake.sqlitehelper;

import android.database.sqlite.SQLiteDatabase;

import net.ichigotake.sqlitehelper.ddl.AlterTable;
import net.ichigotake.sqlitehelper.ddl.CreateIndex;
import net.ichigotake.sqlitehelper.ddl.CreateTable;
import net.ichigotake.sqlitehelper.schema.Table;

public class MigrationHelper {

    public void onCreate(SQLiteDatabase db, Configuration configuration) {
        for (Table table : configuration.getDatabaseTables()) {
            new CreateTable(db, table.getTableSchema()).createTableIfNotExists();
        }
        configuration.getMigrationCallback().onAfterCreate(db, configuration);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion, Configuration configuration) {
        AlterTable alterTable = new AlterTable(db);
        for (Table table : configuration.getDatabaseTables()) {
            if (oldVersion <= table.getCreatedVersion() && table.getCreatedVersion() <= newVersion) {
                new CreateTable(db, table.getTableSchema()).createTableIfNotExists();
            }
            new CreateIndex(db, table.getTableSchema()).createIndexIfNotExists();
            alterTable.addColumnIfNotExists(table);
        }
        configuration.getMigrationCallback().onAfterUpgrade(db, oldVersion, newVersion, configuration);
    }

}

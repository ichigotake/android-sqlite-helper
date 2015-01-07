package net.ichigotake.sqlitehelper;

import android.database.sqlite.SQLiteDatabase;

import net.ichigotake.sqlitehelper.ddl.AlterTable;
import net.ichigotake.sqlitehelper.ddl.CreateIndex;
import net.ichigotake.sqlitehelper.ddl.CreateTable;
import net.ichigotake.sqlitehelper.schema.Table;

public class MigrationHelper {

    public static void onCreate(SQLiteDatabase db, Configuration configuration) {
        for (Table table : configuration.getDatabaseTables()) {
            new CreateTable(db, table.getTableSchema()).createTableIfNotExists();
        }
    }

    public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion, Configuration configuration) {
        for (Table table : configuration.getDatabaseTables()) {
            if (oldVersion <= table.getSenseVersion() && table.getSenseVersion() <= newVersion) {
                new CreateTable(db, table.getTableSchema()).createTableIfNotExists();
            }
            new CreateIndex(db, table.getTableSchema()).createIndexIfNotExists();
            new AlterTable(db, table).addColumn();
        }
    }

    private MigrationHelper() {}
}

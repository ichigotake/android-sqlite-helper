package net.ichigotake.sqlitehelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import net.ichigotake.sqlitehelper.ddl.AlterTableAdd;
import net.ichigotake.sqlitehelper.ddl.CreateTable;
import net.ichigotake.sqlitehelper.ddl.Table;

public class SQLiteOpenHelper extends android.database.sqlite.SQLiteOpenHelper {
    
    private final Configuration configuration;

    public SQLiteOpenHelper(Context context, Configuration configuration) {
        super(context, configuration.getDatabaseName(), null, configuration.getDatabaseVersion());
        this.configuration = configuration;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        for (Table table : configuration.getDatabaseTables()) {
            new CreateTable(db, table.getTableSchema()).execute();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        for (Table table : configuration.getDatabaseTables()) {
            if (oldVersion <= table.getSenseVersion() && table.getSenseVersion() < newVersion) {
                new CreateTable(db, table.getTableSchema()).execute();
            }
            new AlterTableAdd(db, table).execute();
        }
    }

    public Configuration getConfiguration() {
        return configuration;
        
    }
}

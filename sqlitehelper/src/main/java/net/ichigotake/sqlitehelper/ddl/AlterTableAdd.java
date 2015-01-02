package net.ichigotake.sqlitehelper.ddl;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import net.ichigotake.sqlitehelper.dml.Select;

public class AlterTableAdd {

    private final SQLiteDatabase database;
    private final Table table;

    public AlterTableAdd(SQLiteDatabase database, Table table) {
        this.database = database;
        this.table = table;
    }
    
    public void execute() {
        for (TableField field : table.getTableFields()) {
            Cursor cursor = new Select(database, table).execute();
            boolean fieldExists = cursor.getColumnIndex(field.getFieldName()) >= 0;
            if (fieldExists) {
                continue;
            }
            database.execSQL(buildQuery(field));
        }
    }

    /* visible for testing */
    String buildQuery(TableField field) {
        return "ALTER TABLE " + table.getTableName() + " ADD COLUMN " +
                field.getFieldName() + " " + field.getFieldType().getReservedName();
    }
}

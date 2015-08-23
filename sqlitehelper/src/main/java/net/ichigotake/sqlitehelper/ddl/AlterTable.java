package net.ichigotake.sqlitehelper.ddl;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import net.ichigotake.sqlitehelper.dml.Select;
import net.ichigotake.sqlitehelper.schema.DatabaseTable;
import net.ichigotake.sqlitehelper.schema.TableField;

public class AlterTable {

    private final SQLiteDatabase database;

    public AlterTable(SQLiteDatabase database) {
        this.database = database;
    }
    
    public void addColumnIfNotExists(DatabaseTable databaseTable) {
        for (TableField field : databaseTable.getTableSchema().getFields()) {
            Cursor cursor = new Select(database, databaseTable).execute();
            boolean fieldExists = cursor.getColumnIndex(field.getFieldName()) >= 0;
            if (fieldExists) {
                continue;
            }
            database.execSQL(buildQuery(databaseTable.getTableName(), field));
        }
    }

    /* visible for testing */
    String buildQuery(String tableName, TableField field) {
        return "ALTER TABLE " + tableName + " ADD COLUMN " +
                field.getFieldName() + " " + field.getFieldType().getReservedName();
    }
}

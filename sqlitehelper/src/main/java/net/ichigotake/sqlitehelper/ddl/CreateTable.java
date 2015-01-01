package net.ichigotake.sqlitehelper.ddl;

import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

public class CreateTable {
    
    private final SQLiteDatabase database;
    private final TableSchema schema;

    public CreateTable(SQLiteDatabase database, TableSchema schema) {
        this.database = database;
        this.schema = schema;
    }
    
    public void execute() {
        database.rawQuery(buildQueryAsCreateTableIfNotExists(), new String[]{});
        new CreateIndex(database, schema).createIndexIfNotExists();
    }

    /* visible for testing */
    String buildQueryAsCreateTableIfNotExists() {
        List<String> list = new ArrayList<>();
        for (TableField field : schema.getFields()) {
            String query = field.getFieldName() + " " + field.getFieldType().getReservedName();
            if (field.getAttributes().contains(FieldAttribute.PRIMARY_KEY)) {
                query += " PRIMARY KEY";
            }
            list.add(query);
        }
        for (UniqueField field : schema.getUniqueFields()) {
            list.add(buildQueryAsUnique(field));
        }
        return  "CREATE TABLE " + schema.getTableName() + " IF NOT EXISTS (" + TextUtils.join(",", list) + ")";
    }

    /* visible for testing */
    String buildQueryAsUnique(UniqueField field) {
        List<String> names = new ArrayList<>();
        for (TableField item : field.getFields()) {
            names.add(item.getFieldName());
        }
        return "UNIQUE (" + TextUtils.join(",", names) + ")";
    }

}

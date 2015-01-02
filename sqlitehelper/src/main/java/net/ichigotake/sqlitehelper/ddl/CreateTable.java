package net.ichigotake.sqlitehelper.ddl;

import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import net.ichigotake.sqlitehelper.schema.FieldAttribute;
import net.ichigotake.sqlitehelper.schema.TableField;
import net.ichigotake.sqlitehelper.schema.TableSchema;
import net.ichigotake.sqlitehelper.schema.UniqueField;

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
        database.execSQL(buildQueryAsCreateTableIfNotExists());
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
        return  "CREATE TABLE IF NOT EXISTS " + schema.getTableName() + " (" + TextUtils.join(",", list) + ")";
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

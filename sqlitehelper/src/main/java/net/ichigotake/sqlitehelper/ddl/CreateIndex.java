package net.ichigotake.sqlitehelper.ddl;

import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

class CreateIndex {
    
    private final SQLiteDatabase database;
    private final TableSchema schema;

    CreateIndex(SQLiteDatabase database, TableSchema schema) {
        this.database = database;
        this.schema = schema;
    }

    void createIndexIfNotExists() {
        for (Index index : schema.getIndexes()) {
            database.execSQL(buildCreateIndexClause(index));
        }
    }

    /* visible for testing */
    String buildCreateIndexClause(Index index) {
        return "CREATE INDEX IF NOT EXISTS " + buildIndexName(index)
                + " ON " + schema.getTableName() + "(" + buildIndexColumnName(index) + ")";
    }

    private String buildIndexName(Index index) {
        return index.getTableName() + "_" + TextUtils.join("_and_", getFieldNames(index));
    }

    private String buildIndexColumnName(Index index) {
        return TextUtils.join(",", getFieldNames(index));
    }

    private List<String> getFieldNames(Index index) {
        List<String> names = new ArrayList<>();
        for (TableField field : index.getFields()) {
            names.add(field.getFieldName());
        }
        return names;
    }
}

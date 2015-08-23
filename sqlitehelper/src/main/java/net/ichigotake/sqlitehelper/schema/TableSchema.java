package net.ichigotake.sqlitehelper.schema;

import android.support.annotation.NonNull;

import java.util.List;

public class TableSchema {

    private final String tableName;
    private final List<TableField> fields;
    private final List<Index> indexes;
    private final List<UniqueField> uniqueFields;

    TableSchema(
            @NonNull String tableName,
            @NonNull List<TableField> fields,
            @NonNull List<Index> indexes,
            @NonNull List<UniqueField> uniqueFields) {
        this.tableName = tableName;
        this.fields = fields;
        this.indexes = indexes;
        this.uniqueFields = uniqueFields;
    }

    @NonNull
    public String getTableName() {
        return tableName;
    }

    @NonNull
    public List<TableField> getFields() {
        return fields;
    }

    @NonNull
    public List<Index> getIndexes() {
        return indexes;
    }

    @NonNull
    public List<UniqueField> getUniqueFields() {
        return uniqueFields;
    }
    
}

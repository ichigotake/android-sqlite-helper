package net.ichigotake.sqlitehelper.schema;

import android.support.annotation.NonNull;

import java.util.List;

public class Index {

    private final String tableName;
    private final List<TableField> fields;

    public Index(String tableName, List<TableField> fields) {
        this.tableName = tableName;
        this.fields = fields;
    }

    @NonNull
    public String getTableName() {
        return tableName;
    }

    @NonNull
    public List<TableField> getFields() {
        return fields;
    }

}

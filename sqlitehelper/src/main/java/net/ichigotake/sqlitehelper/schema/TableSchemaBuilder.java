package net.ichigotake.sqlitehelper.schema;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TableSchemaBuilder {

    private final String tableName;
    private final List<TableField> fields;
    private final List<Index> indexes;
    private final List<UniqueField> uniqueFields;

    public TableSchemaBuilder(@NonNull String tableName) {
        this.tableName = tableName;
        this.fields = new ArrayList<>();
        this.indexes = new ArrayList<>();
        this.uniqueFields = new ArrayList<>();
    }
    public TableSchemaBuilder(@NonNull TableSchema schema) {
        this.tableName = schema.getTableName();
        this.fields = schema.getFields();
        this.indexes = schema.getIndexes();
        this.uniqueFields = schema.getUniqueFields();
    }


    @NonNull
    public TableSchema build() {
        return new TableSchema(tableName, fields, indexes, uniqueFields);
    }

    @NonNull
    public TableSchemaBuilder field(@NonNull TableField... fields) {
        return field(Arrays.asList(fields));
    }

    @NonNull
    public TableSchemaBuilder field(@NonNull List<TableField> fields) {
        for (TableField field : fields) {
            this.fields.add(field);
            if (field.getFieldAttributes().contains(TableFieldAttribute.UNIQUE)) {
                unique(field);
            }
        }
        return this;
    }

    @NonNull
    public TableSchemaBuilder index(@NonNull TableField field, @NonNull TableField... pair) {
        List<TableField> targets = new ArrayList<>();
        targets.add(field);
        targets.addAll(Arrays.asList(pair));
        indexes.add(new Index(tableName, targets));
        return this;
    }

    @NonNull
    public TableSchemaBuilder unique(@NonNull TableField field, @NonNull TableField... pair) {
        uniqueFields.add(new UniqueField(field, pair));
        return this;
    }

}

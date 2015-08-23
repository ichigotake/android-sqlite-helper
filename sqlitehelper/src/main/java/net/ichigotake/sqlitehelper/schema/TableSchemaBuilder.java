package net.ichigotake.sqlitehelper.schema;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TableSchemaBuilder {

    private final String tableName;
    private final List<TableField> fields;
    private final List<Index> indexes;
    private final List<UniqueField> uniqueFields;

    public TableSchemaBuilder(String tableName) {
        this.tableName = tableName;
        this.fields = new ArrayList<>();
        this.indexes = new ArrayList<>();
        this.uniqueFields = new ArrayList<>();
    }
    public TableSchemaBuilder(TableSchema schema) {
        this.tableName = schema.getTableName();
        this.fields = schema.getFields();
        this.indexes = schema.getIndexes();
        this.uniqueFields = schema.getUniqueFields();
    }


    public TableSchema build() {
        return new TableSchema(tableName, fields, indexes, uniqueFields);
    }

    public TableSchemaBuilder field(TableField... fields) {
        return field(Arrays.asList(fields));
    }

    public TableSchemaBuilder field(List<TableField> fields) {
        for (TableField field : fields) {
            this.fields.add(field);
            if (field.getFieldAttributes().contains(TableFieldAttribute.UNIQUE)) {
                unique(field);
            }
        }
        return this;
    }

    public TableSchemaBuilder index(TableField field, TableField... pair) {
        List<TableField> targets = new ArrayList<>();
        targets.add(field);
        targets.addAll(Arrays.asList(pair));
        indexes.add(new Index(tableName, targets));
        return this;
    }
    
    public TableSchemaBuilder unique(TableField field, TableField... pair) {
        uniqueFields.add(new UniqueField(field, pair));
        return this;
    }

}

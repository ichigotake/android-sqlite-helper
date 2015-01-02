package net.ichigotake.sqlitehelper.schema;

import java.util.List;

public class TableSchema {

    private final String tableName;
    private final List<TableField> fields;
    private final List<Index> indexes;
    private final List<UniqueField> uniqueFields;

    TableSchema(String tableName, List<TableField> fields, List<Index> indexes, List<UniqueField> uniqueFields) {
        this.tableName = tableName;
        this.fields = fields;
        this.indexes = indexes;
        this.uniqueFields = uniqueFields;
    }
    
    public String getTableName() {
        return tableName;
    }

    public List<TableField> getFields() {
        return fields;
    }

    public List<Index> getIndexes() {
        return indexes;
    }

    public List<UniqueField> getUniqueFields() {
        return uniqueFields;
    }
    
}

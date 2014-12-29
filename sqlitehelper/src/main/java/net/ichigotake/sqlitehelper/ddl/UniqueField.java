package net.ichigotake.sqlitehelper.ddl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UniqueField {
    
    private final List<TableField> fields;

    public UniqueField(TableField field, TableField... pair) {
        this.fields = new ArrayList<>();
        Collections.addAll(fields, field);
        Collections.addAll(fields, pair);
    }
    
    public List<TableField> getFields() {
        return fields;
    }
    
}

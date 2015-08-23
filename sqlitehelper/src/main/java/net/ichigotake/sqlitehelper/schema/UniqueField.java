package net.ichigotake.sqlitehelper.schema;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UniqueField {

    private final List<TableField> fields;

    public UniqueField(@NonNull TableField field, @NonNull TableField... pair) {
        this.fields = new ArrayList<>();
        Collections.addAll(fields, field);
        Collections.addAll(fields, pair);
    }

    @NonNull
    public List<TableField> getFields() {
        return fields;
    }
    
}

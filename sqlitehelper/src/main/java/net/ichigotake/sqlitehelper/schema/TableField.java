package net.ichigotake.sqlitehelper.schema;

import android.support.annotation.NonNull;

import java.util.List;

public interface TableField {

    @NonNull
    String getFieldName();

    @NonNull
    TableFieldType getFieldType();

    @NonNull
    List<TableFieldAttribute> getFieldAttributes();

}

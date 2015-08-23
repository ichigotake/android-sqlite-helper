package net.ichigotake.sqlitehelper.schema;

import java.util.List;

public interface TableField {

    String getFieldName();

    TableFieldType getFieldType();

    List<TableFieldAttribute> getFieldAttributes();

}

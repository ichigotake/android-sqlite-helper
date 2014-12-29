package net.ichigotake.sqlitehelper.ddl;

import java.util.List;

public interface TableField {

    String getFieldName();

    TableFieldType getFieldType();

    List<FieldAttribute> getAttributes();

}

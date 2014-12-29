package net.ichigotake.sqlitehelper;

import net.ichigotake.sqlitehelper.ddl.FieldAttribute;
import net.ichigotake.sqlitehelper.ddl.Table;
import net.ichigotake.sqlitehelper.ddl.TableSchema;
import net.ichigotake.sqlitehelper.ddl.TableSchemaBuilder;
import net.ichigotake.sqlitehelper.ddl.TableField;
import net.ichigotake.sqlitehelper.ddl.TableFieldType;

import java.util.Arrays;
import java.util.List;

public class MockTable implements Table {

    public static enum Field implements TableField {

        ID("_id", TableFieldType.LONG, Arrays.asList(FieldAttribute.PRIMARY_KEY)),
        ITEM_NAME("item_name", TableFieldType.TEXT, FieldAttribute.NONE()),
        ITEM_TYPE("item_type", TableFieldType.TEXT, FieldAttribute.NONE()),
        CATEGORY_ID("category_id", TableFieldType.INTEGER, Arrays.asList(FieldAttribute.UNIQUE)),
        CATEGORY_NAME("category_name", TableFieldType.TEXT, FieldAttribute.NONE()),
        ;

        private final String fieldName;
        private final TableFieldType fieldType;
        private final List<FieldAttribute> attributes;

        private Field(String fieldName, TableFieldType fieldType, List<FieldAttribute> attributes) {
            this.fieldName = fieldName;
            this.fieldType = fieldType;
            this.attributes = attributes;
        }

        @Override
        public String getFieldName() {
            return fieldName;
        }

        @Override
        public TableFieldType getFieldType() {
            return fieldType;
        }
        
        @Override
        public List<FieldAttribute> getAttributes() {
            return attributes;
        }

    }

    @Override
    public int getSenseVersion() {
        return 1;
    }

    @Override
    public TableSchema getTableSchema() {
        return new TableSchemaBuilder(getTableName())
                .field(Field.values())
                .index(Field.ID)
                .unique(Field.ITEM_NAME, Field.ITEM_TYPE)
                .build();
    }

    @Override
    public String getTableName() {
        return "mock";
    }

}

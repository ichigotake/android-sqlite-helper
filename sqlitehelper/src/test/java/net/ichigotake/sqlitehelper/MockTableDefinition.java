package net.ichigotake.sqlitehelper;

import android.support.annotation.NonNull;

import net.ichigotake.sqlitehelper.schema.TableFieldAttribute;
import net.ichigotake.sqlitehelper.schema.DatabaseTable;
import net.ichigotake.sqlitehelper.schema.TableSchema;
import net.ichigotake.sqlitehelper.schema.TableSchemaBuilder;
import net.ichigotake.sqlitehelper.schema.TableField;
import net.ichigotake.sqlitehelper.schema.TableFieldType;

import java.util.Arrays;
import java.util.List;

import static net.ichigotake.sqlitehelper.schema.TableFieldAttribute.PRIMARY_KEY;
import static net.ichigotake.sqlitehelper.schema.TableFieldAttribute.UNIQUE;
import static net.ichigotake.sqlitehelper.schema.TableFieldType.INTEGER;
import static net.ichigotake.sqlitehelper.schema.TableFieldType.LONG;
import static net.ichigotake.sqlitehelper.schema.TableFieldType.TEXT;

public class MockTableDefinition implements DatabaseTable {
    
    public enum Field implements TableField {

        ID("_id", LONG, PRIMARY_KEY),
        ITEM_NAME("item_name", TEXT),
        ITEM_TYPE("item_type", TEXT),
        CATEGORY_ID("category_id", INTEGER, UNIQUE),
        CATEGORY_NAME("category_name", TEXT),
        ;

        private final String fieldName;
        private final TableFieldType fieldType;
        private final List<TableFieldAttribute> attributes;

        Field(String fieldName, TableFieldType fieldType, TableFieldAttribute... attributesArray) {
            this.fieldName = fieldName;
            this.fieldType = fieldType;
            this.attributes = Arrays.asList(attributesArray);
        }

        @NonNull
        @Override
        public String getFieldName() {
            return fieldName;
        }

        @NonNull
        @Override
        public TableFieldType getFieldType() {
            return fieldType;
        }
        
        @NonNull
        @Override
        public List<TableFieldAttribute> getFieldAttributes() {
            return attributes;
        }

    }

    @Override
    public int getCreatedVersion() {
        return 1;
    }

    @NonNull
    @Override
    public TableSchema getTableSchema() {
        return new TableSchemaBuilder(getTableName())
                .field(Field.values())
                .index(Field.ID)
                .unique(Field.ITEM_NAME, Field.ITEM_TYPE)
                .build();
    }

    @NonNull
    @Override
    public String getTableName() {
        return "mock";
    }

}

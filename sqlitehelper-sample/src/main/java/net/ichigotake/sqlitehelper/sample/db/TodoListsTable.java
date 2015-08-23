package net.ichigotake.sqlitehelper.sample.db;

import android.support.annotation.NonNull;

import net.ichigotake.sqlitehelper.schema.DatabaseTable;
import net.ichigotake.sqlitehelper.schema.TableField;
import net.ichigotake.sqlitehelper.schema.TableFieldAttribute;
import net.ichigotake.sqlitehelper.schema.TableFieldType;
import net.ichigotake.sqlitehelper.schema.TableSchema;
import net.ichigotake.sqlitehelper.schema.TableSchemaBuilder;

import java.util.Arrays;
import java.util.List;

import static net.ichigotake.sqlitehelper.schema.TableFieldAttribute.PRIMARY_KEY;
import static net.ichigotake.sqlitehelper.schema.TableFieldType.TEXT;

public class TodoListsTable implements DatabaseTable {

    public enum Field implements TableField {

        LIST_ID("list_id", TEXT, PRIMARY_KEY),
        TITLE("title", TEXT),
        ;

        private final String fieldName;
        private final TableFieldType tableFieldType;
        private final List<TableFieldAttribute> tableFieldAttributeList;

        Field(String fieldName, TableFieldType tableFieldType, TableFieldAttribute... tableFieldAttributeArray) {
            this.fieldName = fieldName;
            this.tableFieldType = tableFieldType;
            this.tableFieldAttributeList = Arrays.asList(tableFieldAttributeArray);
        }

        @NonNull
        @Override
        public String getFieldName() {
            return fieldName;
        }

        @NonNull
        @Override
        public TableFieldType getFieldType() {
            return tableFieldType;
        }

        @NonNull
        @Override
        public List<TableFieldAttribute> getFieldAttributes() {
            return tableFieldAttributeList;
        }

    }

    @NonNull
    @Override
    public String getTableName() {
        return "todo_list";
    }

    @Override
    public int getCreatedVersion() {
        return 0;
    }

    @NonNull
    @Override
    public TableSchema getTableSchema() {
        return new TableSchemaBuilder(getTableName())
                .field(Field.values())
                .build();
    }
}

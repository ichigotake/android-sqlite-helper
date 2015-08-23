package net.ichigotake.sqlitehelper.sample.db;

import android.content.ContentValues;
import android.support.annotation.NonNull;

import net.ichigotake.sqlitehelper.schema.DatabaseTable;
import net.ichigotake.sqlitehelper.schema.TableField;
import net.ichigotake.sqlitehelper.schema.TableFieldAttribute;
import net.ichigotake.sqlitehelper.schema.TableFieldType;
import net.ichigotake.sqlitehelper.schema.TableSchema;
import net.ichigotake.sqlitehelper.schema.TableSchemaBuilder;

import java.util.Arrays;
import java.util.List;

import static net.ichigotake.sqlitehelper.sample.db.TodoItemsTable.Field.COMPLETE;
import static net.ichigotake.sqlitehelper.sample.db.TodoItemsTable.Field.DESCRIPTION;
import static net.ichigotake.sqlitehelper.sample.db.TodoItemsTable.Field.LIST_ID;
import static net.ichigotake.sqlitehelper.schema.TableFieldType.LONG;
import static net.ichigotake.sqlitehelper.schema.TableFieldType.TEXT;

public class TodoItemsTable implements DatabaseTable {

    public static class ValuesBuilder {

        private final ContentValues values;

        public ValuesBuilder() {
            values = new ContentValues();
        }

        public ValuesBuilder setDescription(String description) {
            values.put(DESCRIPTION.getFieldName(), description);
            return this;
        }

        public ValuesBuilder setComplete(String complete) {
            values.put(COMPLETE.getFieldName(), complete);
            return this;
        }

        public ValuesBuilder setListId(long listId) {
            values.put(LIST_ID.getFieldName(), listId);
            return this;
        }

        public ContentValues build() {
            return values;
        }
    }

    public enum Field implements TableField {
        LIST_ID("list_id", LONG),
        DESCRIPTION("description", TEXT),
        COMPLETE("complete", TEXT)
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
        return "todo_item";
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

package net.ichigotake.sqlitehelper.dml;

import net.ichigotake.sqlitehelper.ddl.DatabaseTable;
import net.ichigotake.sqlitehelper.ddl.TableDdl;
import net.ichigotake.sqlitehelper.ddl.TableField;
import net.ichigotake.sqlitehelper.ddl.TableFieldType;
import net.ichigotake.sqlitehelper.ddl.TableIndexBuilder;

import java.util.Arrays;

class MockTable implements DatabaseTable {

    enum Field implements TableField {

        ID("_id", TableFieldType.LONG),
        ;

        private final String name;
        private final TableFieldType type;

        private Field(String name, TableFieldType type) {
            this.name = name;
            this.type = type;
        }

        @Override
        public String getFieldName() {
            return name;
        }

        @Override
        public TableFieldType getFieldType() {
            return type;
        }

    }

    @Override
    public int getSenseVersion() {
        return 1;
    }

    @Override
    public String getTableName() {
        return "mock";
    }

    @Override
    public TableDdl getDdl() {
        return new TableDdl(
                Arrays.<TableField>asList(Field.values()),
                new TableIndexBuilder(this).add(Field.ID).build()
        );
    }
}

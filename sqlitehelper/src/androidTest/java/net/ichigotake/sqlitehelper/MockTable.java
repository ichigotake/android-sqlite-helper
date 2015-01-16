package net.ichigotake.sqlitehelper;

import net.ichigotake.sqlitehelper.table.InsertableTable;
import net.ichigotake.sqlitehelper.table.DeletableTable;
import net.ichigotake.sqlitehelper.table.UpdatableTable;

public class MockTable
        implements InsertableTable<Object>,
        UpdatableTable<Object>,
        DeletableTable<Object> {
    
    @Override
    public void deleteRow(Object item) {
        throw new RuntimeException("TBD");
    }

    @Override
    public void updateItem(Object item) {
        throw new RuntimeException("TBD");
    }

    @Override
    public void insertRow(Object item) {
        throw new RuntimeException("TBD");
    }
}

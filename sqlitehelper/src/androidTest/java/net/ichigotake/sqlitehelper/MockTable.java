package net.ichigotake.sqlitehelper;

import android.database.Cursor;

import net.ichigotake.sqlitehelper.schema.Table;
import net.ichigotake.sqlitehelper.schema.DeletableTable;
import net.ichigotake.sqlitehelper.schema.UpdatableTable;

public class MockTable
        implements Table<Object>,
        UpdatableTable<Object>,
        DeletableTable<Object> {
    
    @Override
    public Object convertFrom(Cursor cursor) {
        throw new RuntimeException("TBD");
    }

    @Override
    public void deleteRow(Object item) {
        throw new RuntimeException("TBD");
    }

    @Override
    public void updateItem(Object item) {
        throw new RuntimeException("TBD");
    }

}

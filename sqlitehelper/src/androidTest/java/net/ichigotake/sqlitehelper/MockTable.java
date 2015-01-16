package net.ichigotake.sqlitehelper;

import android.database.Cursor;

import net.ichigotake.sqlitehelper.table.RowMapper;
import net.ichigotake.sqlitehelper.table.DeletableTable;
import net.ichigotake.sqlitehelper.table.UpdatableTable;

public class MockTable
        implements RowMapper<Object>,
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

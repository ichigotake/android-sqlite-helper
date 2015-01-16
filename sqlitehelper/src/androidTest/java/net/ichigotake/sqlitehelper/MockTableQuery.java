package net.ichigotake.sqlitehelper;

import android.database.Cursor;

import net.ichigotake.sqlitehelper.dml.TableQuery;
import net.ichigotake.sqlitehelper.schema.DeletableTable;
import net.ichigotake.sqlitehelper.schema.UpdatableTable;

public class MockTableQuery
        implements TableQuery<Object>,
        UpdatableTable<Object>,
        DeletableTable<Object> {
    
    @Override
    public Object retrieveItem(Cursor cursor) {
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

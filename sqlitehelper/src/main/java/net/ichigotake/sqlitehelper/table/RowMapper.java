package net.ichigotake.sqlitehelper.table;

import android.database.Cursor;

public interface RowMapper<T> extends Table {

    T convertFrom(Cursor cursor);

}

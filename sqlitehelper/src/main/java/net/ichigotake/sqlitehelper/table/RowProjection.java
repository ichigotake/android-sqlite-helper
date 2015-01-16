package net.ichigotake.sqlitehelper.table;

import android.database.Cursor;

public interface RowProjection<T> {

    T convertFrom(Cursor cursor);

}

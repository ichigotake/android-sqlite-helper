package net.ichigotake.sqlitehelper;

import android.database.Cursor;

public interface RowProjection<T> {

    T convertFrom(Cursor cursor);

}

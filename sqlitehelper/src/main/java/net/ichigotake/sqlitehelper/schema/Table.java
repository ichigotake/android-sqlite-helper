package net.ichigotake.sqlitehelper.schema;

import android.database.Cursor;

public interface Table<T> {

    T convertFrom(Cursor cursor);

}

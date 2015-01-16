package net.ichigotake.sqlitehelper.schema;

import android.database.Cursor;

public interface SelectableTable<T> {

    T retrieveItem(Cursor cursor);

}

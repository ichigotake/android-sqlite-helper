package net.ichigotake.sqlitehelper.dml;

import android.database.Cursor;

public interface TableQuery<T> {

    T retrieveItem(Cursor cursor);

}

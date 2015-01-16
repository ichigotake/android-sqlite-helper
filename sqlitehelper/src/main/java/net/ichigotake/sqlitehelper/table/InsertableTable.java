package net.ichigotake.sqlitehelper.table;

public interface InsertableTable<T> extends Table {

    void insertRow(T item);

}

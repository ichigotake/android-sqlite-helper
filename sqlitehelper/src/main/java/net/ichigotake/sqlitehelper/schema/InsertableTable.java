package net.ichigotake.sqlitehelper.schema;

public interface InsertableTable<T> {

    void insertRow(T item);
}

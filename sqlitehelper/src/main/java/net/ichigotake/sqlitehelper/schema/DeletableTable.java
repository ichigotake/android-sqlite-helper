package net.ichigotake.sqlitehelper.schema;

public interface DeletableTable<T> {

    void deleteRow(T item);

}

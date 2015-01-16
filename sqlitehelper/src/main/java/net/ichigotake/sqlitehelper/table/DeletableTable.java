package net.ichigotake.sqlitehelper.table;

public interface DeletableTable<T> extends Table {

    void deleteRow(T item);

}

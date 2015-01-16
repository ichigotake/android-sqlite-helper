package net.ichigotake.sqlitehelper.table;

public interface UpdatableTable<T> extends Table {

    void updateItem(T item);

}

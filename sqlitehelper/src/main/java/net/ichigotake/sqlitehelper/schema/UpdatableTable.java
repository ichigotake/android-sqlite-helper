package net.ichigotake.sqlitehelper.schema;

public interface UpdatableTable<T> {

    void updateItem(T item);

}

package net.ichigotake.sqlitehelper;

import android.database.Cursor;

import net.ichigotake.sqlitehelper.schema.TableField;

public class TableCursor {

    final private Cursor mCursor;

    public TableCursor(Cursor c) {
        mCursor = c;
    }
    
    public boolean moveToFirst() {
        return mCursor.moveToFirst();
    }
    
    public boolean moveToNext() {
        return mCursor.moveToNext();
    }
    
    public byte[] getBlob(TableField field) {
        return mCursor.getBlob(mCursor.getColumnIndex(field.getFieldName()));
    }

    public int getInt(TableField field) {
        return mCursor.getInt(mCursor.getColumnIndex(field.getFieldName()));
    }

    public long getLong(TableField field) {
        return mCursor.getLong(mCursor.getColumnIndex(field.getFieldName()));
    }

    public String getString(TableField field) {
        return mCursor.getString(mCursor.getColumnIndex(field.getFieldName()));
    }

    public void close() {
        mCursor.close();
    }
}

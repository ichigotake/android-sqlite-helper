package net.ichigotake.sqlitehelper;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteTransactionListener;

public class SQLiteTransaction {

    private final SQLiteDatabase database;

    public SQLiteTransaction(SQLiteDatabase database) {
        this.database = database;
    }

    public void execute(SQLiteTransactionListener listener) throws SQLiteTransactionException {
        Exception exception = null;
        try {
            database.beginTransactionWithListener(listener);
            database.setTransactionSuccessful();
        } catch (Exception e) {
            exception  = e;
        } finally {
            database.endTransaction();
            if (exception != null) {
                throw new SQLiteTransactionException("Error in transaction.", exception);
            }
        }
    }
}

package net.ichigotake.sqlitehelper.dml;

import android.database.sqlite.SQLiteDatabase;

public class QueryTransaction {

    private final SQLiteDatabase database;

    public QueryTransaction(SQLiteDatabase database) {
        this.database = database;
    }

    public void execute(TransactionTask task) throws SQLiteTransactionException {
        Exception exception = null;
        try {
            database.beginTransaction();
            task.execute();
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

package net.ichigotake.sqlitehelper.dml;

import android.annotation.TargetApi;
import android.database.sqlite.SQLiteException;
import android.os.Build;

public class SQLiteTransactionException extends SQLiteException {

    public SQLiteTransactionException() {
    }

    public SQLiteTransactionException(String error) {
        super(error);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public SQLiteTransactionException(String error, Throwable cause) {
        super(error, cause);
    }

}

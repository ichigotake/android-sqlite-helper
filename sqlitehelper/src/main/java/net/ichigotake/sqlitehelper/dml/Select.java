package net.ichigotake.sqlitehelper.dml;

import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;

import net.ichigotake.sqlitehelper.Cache;
import net.ichigotake.sqlitehelper.ddl.DatabaseTable;
import net.ichigotake.sqlitehelper.SQLiteHelper;

import java.util.ArrayList;
import java.util.List;

public class Select {

    private final SQLiteHelper sqlite;
    private final DatabaseTable table;
    private final Where where;
    private final List<String> orderBy;

    public Select(DatabaseTable from) {
        this(Cache.getApplicationContext(), from);
    }

    public Select(Context context, DatabaseTable from) {
        this.sqlite = new SQLiteHelper(context);
        this.table = from;
        this.where = new Where();
        this.orderBy = new ArrayList<>();
    }

    public Select where(Where where) {
        if (!where.isEmpty()) {
            this.where.and(where);
        }
        return this;
    }

    public Select orderBy(Order field) {
        String seq = field.getSequence() == Order.Sequence.DESC ? "DESC" : "ASC";
        orderBy.add(field.getFieldName() + " " + seq);
        return this;
    }

    public Cursor execute() {
        return sqlite.getReadableDatabase()
                .rawQuery(buildQuery(), getArguments());
    }

    /** visible for testing */
    String buildQuery() {
        String query = "SELECT * FROM " + table.getTableName();
        if (!where.isEmpty()) {
            query += " WHERE " + where.getQuery();
        }
        if (!orderBy.isEmpty()) {
            query += " ORDER BY " + TextUtils.join(",", orderBy);
        }
        return query;
    }

    /** visible for testing */
    String[] getArguments() {
        return where.getArguments();
    }

}

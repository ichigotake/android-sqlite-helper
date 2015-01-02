package net.ichigotake.sqlitehelper.dml;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import net.ichigotake.sqlitehelper.ddl.Table;

import java.util.ArrayList;
import java.util.List;

public class Select {

    private final SQLiteDatabase database;
    private final Table table;
    private final Where where;
    private final List<String> orderBy;

    public Select(SQLiteDatabase database, Table from) {
        this.database = database;
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
        return database.rawQuery(buildQuery(), getArguments());
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

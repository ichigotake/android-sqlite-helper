package net.ichigotake.sqlitehelper.dml;

import android.text.TextUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * TODO: if (whereClause.placeholderCount() != arguments.length) throw new RuntimeException
 */
public final class Where {

    private final List<String> conditions;
    private final List<String> values;
    private final List<String> logicalOperators;

    public Where() {
        conditions = new CopyOnWriteArrayList<>();
        values = new CopyOnWriteArrayList<>();
        logicalOperators = new CopyOnWriteArrayList<>();
    }

    public Where(Where where) {
        this();
        addCondition(where);
    }

    public Where(String condition, Object... arguments) {
        this();
        addCondition(condition, Arrays.asList(arguments));
    }

    public boolean isEmpty() {
        return conditions.size() <= 0;
    }

    public Where and(String condition, Object... arguments) {
        addAndOperator();
        addCondition(condition, Arrays.asList(arguments));
        return this;
    }

    public Where and(Where where) {
        addAndOperator();
        addCondition(where);
        return this;
    }

    public Where or(String condition, Object... arguments) {
        addOrOperator();
        addCondition(condition, Arrays.asList(arguments));
        return this;
    }

    public Where or(Where where) {
        addOrOperator();
        addCondition(where);
        return this;
    }

    private void addAndOperator() {
        if (conditions.size() > 0) {
            logicalOperators.add("AND");
        }
    }

    private void addOrOperator() {
        if (conditions.size() > 0) {
            logicalOperators.add("OR");
        }
    }

    private void addCondition(String condition, Collection<Object> arguments) {
        conditions.add(condition);
        for (Object argv : arguments) {
            System.out.println("argv " + argv);
            values.add(argv.toString());
        }
        System.out.println("----");
    }

    private void addCondition(Where where) {
        conditions.add(where.getQuery());
        Collections.addAll(values, where.getArguments());
    }

    public String getQuery() {
        if (isEmpty()) {
            return "";
        }

        String query = "";
        System.out.println("operators : " + TextUtils.join(", ", logicalOperators));
        for (int i=0,size=conditions.size(); i<size; i++) {
            if (i > 0) {
                query += " " + logicalOperators.get(i-1) + " ";
            }
            query += "(" + conditions.get(i) + ")";
        }
        return query;
    }

    public String[] getArguments() {
        return values.toArray(new String[values.size()]);
    }

    @Override
    public String toString() {
        return getQuery();
    }

}

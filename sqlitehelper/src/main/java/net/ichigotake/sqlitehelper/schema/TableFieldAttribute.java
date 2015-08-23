package net.ichigotake.sqlitehelper.schema;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public enum TableFieldAttribute {
    
    PRIMARY_KEY,
    UNIQUE,
    ;

    @NonNull
    public static List<TableFieldAttribute> NONE() {
        return new ArrayList<>();
    }
}

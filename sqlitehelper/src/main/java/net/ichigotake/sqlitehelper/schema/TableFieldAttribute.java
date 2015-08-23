package net.ichigotake.sqlitehelper.schema;

import java.util.ArrayList;
import java.util.List;

public enum TableFieldAttribute {
    
    PRIMARY_KEY,
    UNIQUE,
    ;

    public static List<TableFieldAttribute> NONE() {
        return new ArrayList<>();
    }
}

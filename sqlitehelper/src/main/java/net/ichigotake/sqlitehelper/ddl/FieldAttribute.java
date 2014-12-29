package net.ichigotake.sqlitehelper.ddl;

import java.util.ArrayList;
import java.util.List;

public enum FieldAttribute {
    
    PRIMARY_KEY,
    UNIQUE,
    ;

    public static List<FieldAttribute> NONE() {
        return new ArrayList<>();
    }
}

package net.ichigotake.sqlitehelper.schema;

import android.support.annotation.NonNull;

public enum TableFieldType {

    TEXT("TEXT"),
    INTEGER("INTEGER"),
    LONG("INTEGER"),
    ;
    
    private final String reservedName;
    
    TableFieldType(String reservedName) {
        this.reservedName = reservedName;
    }

    @NonNull
    public String getReservedName() {
        return reservedName;
        
    }

}

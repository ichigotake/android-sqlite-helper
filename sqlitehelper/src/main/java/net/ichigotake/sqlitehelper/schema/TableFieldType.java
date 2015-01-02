package net.ichigotake.sqlitehelper.schema;

public enum TableFieldType {

    TEXT("TEXT"),
    INTEGER("INTEGER"),
    LONG("INTEGER"),
    ;
    
    private final String reservedName;
    
    private TableFieldType(String reservedName) {
        this.reservedName = reservedName;
    }
    
    public String getReservedName() {
        return reservedName;
        
    }

}

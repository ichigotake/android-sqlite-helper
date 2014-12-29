package net.ichigotake.sqlitehelper.ddl;

public enum TableFieldType {

    TEXT("TEXT"),
    INTEGER("INTEGER"),
    LONG("LONG"),
    ;
    
    private final String reservedName;
    
    private TableFieldType(String reservedName) {
        this.reservedName = reservedName;
    }
    
    public String getReservedName() {
        return reservedName;
        
    }

}

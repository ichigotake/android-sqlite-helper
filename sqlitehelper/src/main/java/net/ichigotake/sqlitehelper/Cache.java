package net.ichigotake.sqlitehelper;

import android.content.Context;

public class Cache {

    static Context applicationContext;
    static Configuration configuration;

    public static Context getApplicationContext() {
        return applicationContext;
    }
    
    public static Configuration getConfiguration() {
        return configuration;
        
    }

}

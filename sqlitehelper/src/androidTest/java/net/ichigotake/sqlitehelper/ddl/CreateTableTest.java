package net.ichigotake.sqlitehelper.ddl;

import junit.framework.Assert;

import net.ichigotake.sqlitehelper.MockTable;
import net.ichigotake.sqlitehelper.SQLiteHelper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@Config(emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class CreateTableTest {
    
    @Test
    public void testBuildQueryAsCreateTableIfNotExists() {
        SQLiteHelper sqlite = new SQLiteHelper(Robolectric.application);
        CreateTable createTable = new CreateTable(
                sqlite.getReadableDatabase(), new MockTable().getTableSchema());
        String expected = "CREATE TABLE mock IF NOT EXISTS (" +
                "_id INTEGER PRIMARY KEY," +
                "item_name TEXT," +
                "item_type TEXT," +
                "category_id INTEGER," +
                "category_name TEXT," +
                "UNIQUE (category_id)," +
                "UNIQUE (item_name,item_type)" +
                ")";
        Assert.assertEquals(expected, createTable.buildQueryAsCreateTableIfNotExists());
    }

    @Test
    public void testBuildUniqueQuery() {
        SQLiteHelper sqlite = new SQLiteHelper(Robolectric.application);
        CreateTable createTable = new CreateTable(
                sqlite.getReadableDatabase(), new MockTable().getTableSchema());
        UniqueField sample = new UniqueField(MockTable.Field.CATEGORY_ID);
        Assert.assertEquals("UNIQUE (category_id)", createTable.buildQueryAsUnique(sample));
    }

}

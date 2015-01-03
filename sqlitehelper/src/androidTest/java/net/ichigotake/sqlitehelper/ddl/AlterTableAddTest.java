package net.ichigotake.sqlitehelper.ddl;

import android.database.sqlite.SQLiteDatabase;

import junit.framework.Assert;

import net.ichigotake.sqlitehelper.DatabaseHelper;
import net.ichigotake.sqlitehelper.MockConfiguration;
import net.ichigotake.sqlitehelper.MockTable;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@Config(emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class AlterTableAddTest {
    
    @Test
    public void testBuildQuery() {
        SQLiteDatabase database = new DatabaseHelper(Robolectric.application, new MockConfiguration())
                .getWritableDatabase();
        AlterTableAdd alterTableAdd = new AlterTableAdd(database, new MockTable());
        String expected = "ALTER TABLE mock ADD COLUMN category_id INTEGER";
        Assert.assertEquals(expected, alterTableAdd.buildQuery(MockTable.Field.CATEGORY_ID));
    }
    
}

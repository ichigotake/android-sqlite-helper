package net.ichigotake.sqlitehelper.ddl;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;

import junit.framework.Assert;

import net.ichigotake.sqlitehelper.BuildConfig;
import net.ichigotake.sqlitehelper.DatabaseHelper;
import net.ichigotake.sqlitehelper.MockConfiguration;
import net.ichigotake.sqlitehelper.MockTableDefinition;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowApplication;

@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.KITKAT)
@RunWith(RobolectricGradleTestRunner.class)
public class AlterTableDefinitionTest {
    
    @Test
    public void testBuildQuery() {
        Context context = ShadowApplication.getInstance().getApplicationContext();
        SQLiteDatabase database = new DatabaseHelper(context, new MockConfiguration())
                .getWritableDatabase();
        MockTableDefinition mock = new MockTableDefinition();
        AlterTable alterTable = new AlterTable(database);
        String expected = "ALTER TABLE mock ADD COLUMN category_id INTEGER";
        Assert.assertEquals(expected, alterTable.buildQuery(mock.getTableName(), MockTableDefinition.Field.CATEGORY_ID));
    }
    
}

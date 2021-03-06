package net.ichigotake.sqlitehelper.dml;

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
public class SelectTest {
    
    private SQLiteDatabase database() {
        Context context = ShadowApplication.getInstance().getApplicationContext();
        return new DatabaseHelper(context, new MockConfiguration())
                .getReadableDatabase();
    }

    @Test
    public void testOrder() {
        {
            String expected = "SELECT * FROM mock";
            String got = new Select(database(), new MockTableDefinition()).buildQuery();
            Assert.assertEquals(expected, got);
        }
        {
            String expected = "SELECT * FROM mock ORDER BY _id ASC";
            String got = new Select(database(), new MockTableDefinition())
                    .orderBy(new Order(MockTableDefinition.Field.ID))
                    .buildQuery();
            Assert.assertEquals(expected, got);
        }
        {
            String expected = "SELECT * FROM mock ORDER BY _id ASC";
            String got = new Select(database(), new MockTableDefinition())
                    .orderBy(new Order(MockTableDefinition.Field.ID, Order.Sequence.ASC))
                    .buildQuery();
            Assert.assertEquals(expected, got);
        }
        {
            String expected = "SELECT * FROM mock ORDER BY _id DESC";
            String got = new Select(database(), new MockTableDefinition())
                    .orderBy(new Order(MockTableDefinition.Field.ID, Order.Sequence.DESC))
                    .buildQuery();
            Assert.assertEquals(expected, got);
        }
    }

    @Test
    public void textBuildQuery() {
        Select query = new Select(database(), new MockTableDefinition());
        {
            String expected = "SELECT * FROM mock";
            Assert.assertEquals(expected, query.buildQuery());
        }
        {
            String expected = "SELECT * FROM mock ORDER BY _id DESC";
            query.orderBy(new Order(MockTableDefinition.Field.ID, Order.Sequence.DESC));
            Assert.assertEquals(expected, query.buildQuery());
        }
        {
            String expected = "SELECT * FROM mock ORDER BY _id DESC";
            query.where(new Where()).buildQuery();
            Assert.assertEquals(expected, query.buildQuery());
        }
        {
            String expected = "SELECT * FROM mock WHERE ((_id = ?)) ORDER BY _id DESC";
            String condition = MockTableDefinition.Field.ID.getFieldName() + " = ?";
            query.where(new Where(condition, 1));
            Assert.assertEquals(expected, query.buildQuery());
        }
    }
}

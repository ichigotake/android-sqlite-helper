package net.ichigotake.sqlitehelper.dml;

import junit.framework.Assert;

import net.ichigotake.sqlitehelper.MockTable;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@Config(emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class SelectTest {

    @Test
    public void testOrder() {
        {
            String expected = "SELECT * FROM mock";
            String got = new Select(new MockTable()).buildQuery();
            Assert.assertEquals(expected, got);
        }
        {
            String expected = "SELECT * FROM mock ORDER BY _id ASC";
            String got = new Select(new MockTable())
                    .orderBy(new Order(MockTable.Field.ID))
                    .buildQuery();
            Assert.assertEquals(expected, got);
        }
        {
            String expected = "SELECT * FROM mock ORDER BY _id ASC";
            String got = new Select(new MockTable())
                    .orderBy(new Order(MockTable.Field.ID, Order.Sequence.ASC))
                    .buildQuery();
            Assert.assertEquals(expected, got);
        }
        {
            String expected = "SELECT * FROM mock ORDER BY _id DESC";
            String got = new Select(new MockTable())
                    .orderBy(new Order(MockTable.Field.ID, Order.Sequence.DESC))
                    .buildQuery();
            Assert.assertEquals(expected, got);
        }
    }

    @Test
    public void textBuildQuery() {
        Select query = new Select(new MockTable());
        {
            String expected = "SELECT * FROM mock";
            Assert.assertEquals(expected, query.buildQuery());
        }
        {
            String expected = "SELECT * FROM mock ORDER BY _id DESC";
            query.orderBy(new Order(MockTable.Field.ID, Order.Sequence.DESC));
            Assert.assertEquals(expected, query.buildQuery());
        }
        {
            String expected = "SELECT * FROM mock ORDER BY _id DESC";
            query.where(new Where()).buildQuery();
            Assert.assertEquals(expected, query.buildQuery());
        }
        {
            String expected = "SELECT * FROM mock WHERE ((_id = ?)) ORDER BY _id DESC";
            String condition = MockTable.Field.ID.getFieldName() + " = ?";
            query.where(new Where(condition, 1));
            Assert.assertEquals(expected, query.buildQuery());
        }
    }
}

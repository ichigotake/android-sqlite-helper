package net.ichigotake.sqlitehelper.dml;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@Config(emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class WhereTest {

    @Test
    public void testAnd() {
        {
            Where where = new Where("_id = 1");
            Assert.assertEquals("(_id = 1)", where.getQuery());
            Assert.assertTrue(!where.isEmpty());
            Assert.assertEquals(0, where.getArguments().length);
        }
        {
            Where where = new Where("_id = 2").or("_id = 1");
            Assert.assertEquals("(_id = 2) OR (_id = 1)", where.getQuery());
            Assert.assertTrue(!where.isEmpty());
            Assert.assertEquals(0, where.getArguments().length);
        }
        {
            Where where = new Where();
            Assert.assertTrue(where.isEmpty());
            where.and("_id = ?", 1);
            Assert.assertEquals("(_id = ?)", where.getQuery());
            Assert.assertTrue(!where.isEmpty());
            Assert.assertEquals(1, where.getArguments().length);

            String[] expected = new String[]{"1"};
            for (int i=0,size=expected.length; i<size; i++) {
                Assert.assertEquals(expected[i], where.getArguments()[i]);
            }
        }
    }

    @Test
    public void testMerge() {
        {
            Where where = new Where();
            Assert.assertTrue(where.isEmpty());

            where.and("_id = ?", 1);
            Where merged = new Where(where);
            Assert.assertTrue(!merged.isEmpty());
            Assert.assertEquals("((_id = ?))", merged.getQuery());

            Assert.assertTrue(merged.isEmpty() == where.isEmpty());
            String[] expected = new String[]{"1"};
            for (int i=0,size=expected.length; i<size; i++) {
                Assert.assertEquals(expected[i], merged.getArguments()[i]);
            }
        }
        {
            Where merged = new Where("_id = ?", 5)
                    .or(new Where("_id = ?", 2).and("_id = ?", 1))
                    .and(new Where("_id = 3").or("_id = 4"));
            String expected = "(_id = ?) OR ((_id = ?) AND (_id = ?)) AND ((_id = 3) OR (_id = 4))";
            Assert.assertEquals(expected, merged.getQuery());
            Assert.assertTrue(!merged.isEmpty());
            Assert.assertEquals(3, merged.getArguments().length);
            Assert.assertEquals("5", merged.getArguments()[0]);
            Assert.assertEquals("2", merged.getArguments()[1]);
            Assert.assertEquals("1", merged.getArguments()[2]);
        }
    }

}

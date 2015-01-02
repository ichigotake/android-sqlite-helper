package net.ichigotake.sqlitehelper;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@Config(emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class ApplicationTest {

    @Before
    public void testCacheException() {
        Exception got = null;
        try {
            new SQLiteOpenHelper(Robolectric.application);
        } catch (IllegalStateException e) {
            got = e;
        }
        Assert.assertTrue(got != null);
    }

    @Test
    public void testInitializer() {
        SQLiteOpenHelper.initialize(Robolectric.application, new MockConfiguration());
    }
    
}

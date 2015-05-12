package net.ichigotake.sqlitehelper.ddl;

import junit.framework.Assert;

import net.ichigotake.sqlitehelper.BuildConfig;
import net.ichigotake.sqlitehelper.MockTableDefinition;
import net.ichigotake.sqlitehelper.schema.Index;
import net.ichigotake.sqlitehelper.schema.TableField;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.util.Arrays;

@Config(constants = BuildConfig.class, emulateSdk = 21)
@RunWith(RobolectricGradleTestRunner.class)
public class CreateIndexTest {
    
    @Test
    public void testClause() {
        MockTableDefinition mock = new MockTableDefinition();
        CreateIndex createIndex = new CreateIndex(null, mock.getTableSchema());
        {
            Index sample = new Index(mock.getTableName(), Arrays.<TableField>asList(MockTableDefinition.Field.ID));
            String query = createIndex.buildCreateIndexClause(sample);
            String expected = "CREATE INDEX IF NOT EXISTS mock__id ON mock(_id)";
            Assert.assertEquals(expected, query);
        }
        {
            Index sample = new Index(
                    mock.getTableName(),
                    Arrays.<TableField>asList(MockTableDefinition.Field.ITEM_TYPE, MockTableDefinition.Field.ITEM_NAME)
                    );
            String query = createIndex.buildCreateIndexClause(sample);
            String expected = "CREATE INDEX IF NOT EXISTS mock_item_type_and_item_name" +
                    " ON mock(item_type,item_name)";
            Assert.assertEquals(expected, query);
        }
    }
    
}

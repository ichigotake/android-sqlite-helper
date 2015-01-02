package net.ichigotake.sqlitehelper.ddl;

import junit.framework.Assert;

import net.ichigotake.sqlitehelper.MockTable;
import net.ichigotake.sqlitehelper.schema.Index;
import net.ichigotake.sqlitehelper.schema.TableField;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.Arrays;

@Config(emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class CreateIndexTest {
    
    @Test
    public void testClause() {
        MockTable mock = new MockTable();
        CreateIndex createIndex = new CreateIndex(null, mock.getTableSchema());
        {
            Index sample = new Index(mock.getTableName(), Arrays.<TableField>asList(MockTable.Field.ID));
            String query = createIndex.buildCreateIndexClause(sample);
            String expected = "CREATE INDEX IF NOT EXISTS mock__id ON mock(_id)";
            Assert.assertEquals(expected, query);
        }
        {
            Index sample = new Index(
                    mock.getTableName(),
                    Arrays.<TableField>asList(MockTable.Field.ITEM_TYPE, MockTable.Field.ITEM_NAME)
                    );
            String query = createIndex.buildCreateIndexClause(sample);
            String expected = "CREATE INDEX IF NOT EXISTS mock_item_type_and_item_name" +
                    " ON mock(item_type,item_name)";
            Assert.assertEquals(expected, query);
        }
    }
    
}

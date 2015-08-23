package net.ichigotake.sqlitehelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;

import junit.framework.Assert;

import net.ichigotake.sqlitehelper.schema.TableFieldAttribute;
import net.ichigotake.sqlitehelper.schema.DatabaseTable;
import net.ichigotake.sqlitehelper.schema.TableField;
import net.ichigotake.sqlitehelper.schema.TableFieldType;
import net.ichigotake.sqlitehelper.schema.TableSchema;
import net.ichigotake.sqlitehelper.schema.TableSchemaBuilder;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowApplication;

import java.util.Collections;
import java.util.List;

@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.KITKAT)
@RunWith(RobolectricTestRunner.class)
public class DatabaseHelperTest {

    @Test
    public void testInitializer() {
        new DatabaseHelper(ShadowApplication.getInstance().getApplicationContext(), new MockConfiguration());
    }
    
    @Test
    public void testMigrate() {
        Context context = ShadowApplication.getInstance().getApplicationContext();
        {
            DatabaseConfiguration configuration = new ConfigurationBeforeUpgrade();
            SQLiteDatabase database = new DatabaseHelper(context, configuration)
                    .getWritableDatabase();
            Cursor cursor = database.rawQuery("SELECT * FROM mock", new String[]{});
            Assert.assertTrue("Before migrate", cursor.getColumnIndex(NewField.fieldName) == -1);
            cursor.close();
        }
        {
            DatabaseConfiguration configuration = new ConfigurationAfterUpgrade();
            // exec migration
            new DatabaseHelper(context, configuration).getWritableDatabase();
            
            Cursor cursor = new DatabaseHelper(context, configuration)
                    .getReadableDatabase()
                    .rawQuery("SELECT * FROM mock", new String[]{});
            Assert.assertTrue("After migrate", cursor.getColumnIndex(NewField.fieldName) >= 0);
            cursor.close();
        }
    }
    
}

class ConfigurationBeforeUpgrade extends MockConfiguration {

    @Override
    public String getDatabaseName() {
        return super.getDatabaseName() + "_for_migration";
    }
    
    @Override
    public int getDatabaseVersion() {
        return 1;
    }

}

class ConfigurationAfterUpgrade extends ConfigurationBeforeUpgrade {

    @Override
    public int getDatabaseVersion() {
        return super.getDatabaseVersion() + 1;
    }

    @Override
    public List<DatabaseTable> getDatabaseTables() {
        return Collections.<DatabaseTable>singletonList(new MockTableDefinitionForUpgrade());
    }

}

class MockTableDefinitionForUpgrade extends MockTableDefinition {
   
    @Override
    public TableSchema getTableSchema() {
        return new TableSchemaBuilder(super.getTableSchema())
                .field(new NewField())
                .build();
    }
    
}

class NewField implements TableField {
    
    static String fieldName = "new_field";

    @Override
    public String getFieldName() {
        return fieldName;
    }

    @Override
    public TableFieldType getFieldType() {
        return TableFieldType.INTEGER;
    }

    @Override
    public List<TableFieldAttribute> getFieldAttributes() {
        return TableFieldAttribute.NONE();
    }
}

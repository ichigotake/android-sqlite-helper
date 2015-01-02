package net.ichigotake.sqlitehelper;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import junit.framework.Assert;

import net.ichigotake.sqlitehelper.schema.FieldAttribute;
import net.ichigotake.sqlitehelper.schema.Table;
import net.ichigotake.sqlitehelper.schema.TableField;
import net.ichigotake.sqlitehelper.schema.TableFieldType;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Config(emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class SQLiteOpenHelperTest {

    @Test
    public void testInitializer() {
        new SQLiteOpenHelper(Robolectric.application, new MockConfiguration());
    }
    
    @Test
    public void testMigrate() {
        {
            Configuration configuration = new ConfigurationBeforeUpgrade();
            SQLiteDatabase database = new SQLiteOpenHelper(Robolectric.application, configuration)
                    .getWritableDatabase();
            Cursor cursor = database.rawQuery("SELECT * FROM mock", new String[]{});
            Assert.assertTrue("Before migrate", cursor.getColumnIndex(NewField.fieldName) == -1);
        }
        {
            Configuration configuration = new ConfigurationAfterUpgrade();
            // exec migration
            new SQLiteOpenHelper(Robolectric.application, configuration).getWritableDatabase();
            
            Cursor cursor = new SQLiteOpenHelper(Robolectric.application, configuration)
                    .getReadableDatabase()
                    .rawQuery("SELECT * FROM mock", new String[]{});
            Assert.assertTrue("After migrate", cursor.getColumnIndex(NewField.fieldName) >= 0);
        }
    }
    
}

class ConfigurationBeforeUpgrade extends MockConfiguration {

    @Override
    public String getDatabaseName() {
        return "mock_for_migration";
    }
    
    @Override
    public int getDatabaseVersion() {
        return 1;
    }

}

class ConfigurationAfterUpgrade extends ConfigurationBeforeUpgrade {

    @Override
    public int getDatabaseVersion() {
        return 2;
    }

    @Override
    public List<Table> getDatabaseTables() {
        return Arrays.<Table>asList(new MockTableForUpgrade());
    }

}

class MockTableForUpgrade extends MockTable {
    
    @Override
    public List<TableField> getTableFields() {
        List<TableField> fields = new ArrayList<>();
        fields.addAll(super.getTableFields());
        fields.add(new NewField());
        return fields;
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
    public List<FieldAttribute> getAttributes() {
        return FieldAttribute.NONE();
    }
}

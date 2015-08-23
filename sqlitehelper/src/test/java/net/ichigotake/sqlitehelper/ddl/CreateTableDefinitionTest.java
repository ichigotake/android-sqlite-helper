package net.ichigotake.sqlitehelper.ddl;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Build;
import android.support.annotation.NonNull;

import junit.framework.Assert;

import net.ichigotake.sqlitehelper.BuildConfig;
import net.ichigotake.sqlitehelper.DatabaseHelper;
import net.ichigotake.sqlitehelper.MockConfiguration;
import net.ichigotake.sqlitehelper.MockTableDefinition;
import net.ichigotake.sqlitehelper.schema.Index;
import net.ichigotake.sqlitehelper.schema.DatabaseTable;
import net.ichigotake.sqlitehelper.schema.TableSchema;
import net.ichigotake.sqlitehelper.schema.UniqueField;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowApplication;

@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.KITKAT)
@RunWith(RobolectricGradleTestRunner.class)
public class CreateTableDefinitionTest {
    
    private DatabaseTable mock() {
        return new MockTableForCreateTableDefinition();
    }
    
    private DatabaseHelper sqliteHelper() {
        return new DatabaseHelper(ShadowApplication.getInstance().getApplicationContext(), new MockConfiguration());
    }
    
    @Test
    public void testBuildQueryAsCreateTableIfNotExists() {
        DatabaseHelper sqlite = sqliteHelper();
        CreateTable createTable = new CreateTable(
                sqlite.getReadableDatabase(), mock().getTableSchema());
        String expected = "CREATE TABLE IF NOT EXISTS mock_for_create_table (" +
                "_id INTEGER PRIMARY KEY," +
                "item_name TEXT," +
                "item_type TEXT," +
                "category_id INTEGER," +
                "category_name TEXT," +
                "UNIQUE (category_id)," +
                "UNIQUE (item_name,item_type)" +
                ")";
        Assert.assertEquals(expected, createTable.buildQueryAsCreateTableIfNotExists());
    }

    @Test
    public void testBuildUniqueQuery() {
        DatabaseHelper sqlite = sqliteHelper();
        CreateTable createTable = new CreateTable(
                sqlite.getReadableDatabase(), mock().getTableSchema());
        UniqueField sample = new UniqueField(MockTableDefinition.Field.CATEGORY_ID);
        Assert.assertEquals("UNIQUE (category_id)", createTable.buildQueryAsUnique(sample));
    }
    
    @Test
    public void testCreateTable() {
        SQLiteDatabase database = sqliteHelper().getWritableDatabase();
        CreateTable createTable = new CreateTable(database, mock().getTableSchema());
        {
            Exception got = null;
            try {
                database.rawQuery("SELECT * FROM mock_for_create_table", new String[]{});
            } catch (SQLiteException e) {
                got = e;
            }
            Assert.assertTrue(got != null);
        }
        database.execSQL(createTable.buildQueryAsCreateTableIfNotExists());
        {
            database.execSQL("SELECT * FROM mock_for_create_table", new String[]{});
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testCreateIndex() {
        TableSchema schema = mock().getTableSchema();
        SQLiteDatabase database = sqliteHelper().getWritableDatabase();
        CreateTable createTable = new CreateTable(database, schema);

        Assert.assertTrue(!indexExists(database, schema));
        createTable.createTableIfNotExists();
        Assert.assertTrue(indexExists(database, schema));
    }
    
    private boolean indexExists(SQLiteDatabase database, TableSchema schema) {
        if (schema.getIndexes().isEmpty()) {
            throw new IllegalStateException("Indexes is empty");
        }
        CreateIndex createIndex = new CreateIndex(database, schema);
        for (Index index : schema.getIndexes()) {
            try {
                String query = createIndex.buildCreateIndexClause(index)
                        .replace("IF NOT EXISTS ", "");
                database.execSQL(query);
            } catch (SQLiteException e) {
                if (!e.getCause().getMessage().contains("already exists]")) {
                    return false;
                }
            }
        }
        return true;
    }

}

class MockTableForCreateTableDefinition extends MockTableDefinition {
    
    @NonNull
    @Override
    public String getTableName() {
        return "mock_for_create_table";
        
    }
    
}
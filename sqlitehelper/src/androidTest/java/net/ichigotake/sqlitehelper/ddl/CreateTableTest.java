package net.ichigotake.sqlitehelper.ddl;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import junit.framework.Assert;

import net.ichigotake.sqlitehelper.MockConfiguration;
import net.ichigotake.sqlitehelper.MockTable;
import net.ichigotake.sqlitehelper.SQLiteOpenHelper;
import net.ichigotake.sqlitehelper.schema.Index;
import net.ichigotake.sqlitehelper.schema.Table;
import net.ichigotake.sqlitehelper.schema.TableSchema;
import net.ichigotake.sqlitehelper.schema.UniqueField;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@Config(emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class CreateTableTest {
    
    private Table mock() {
        return new MockTableForCreateTable();
    }
    
    private SQLiteOpenHelper sqliteHelper() {
        return new SQLiteOpenHelper(Robolectric.application, new MockConfiguration());
    }
    
    @Test
    public void testBuildQueryAsCreateTableIfNotExists() {
        SQLiteOpenHelper sqlite = sqliteHelper();
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
        SQLiteOpenHelper sqlite = sqliteHelper();
        CreateTable createTable = new CreateTable(
                sqlite.getReadableDatabase(), mock().getTableSchema());
        UniqueField sample = new UniqueField(MockTable.Field.CATEGORY_ID);
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
        createTable.execute();
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

class MockTableForCreateTable extends MockTable {
    
    @Override
    public String getTableName() {
        return "mock_for_create_table";
        
    }
    
}
package fr.ynov.applpc;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

import java.lang.Exception;
import java.lang.String;
import fr.ynov.applpc.data.AppLpcDBContract;
import fr.ynov.applpc.data.AppLpcDBHelper;

/**
 * Created by clément on 09/02/2016.
 */
public class TestDb extends AndroidTestCase {
    void deleteTheDatabase(){
        mContext.deleteDatabase(AppLpcDBHelper.DATABASE_NAME);
    }
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        deleteTheDatabase();
    }
    public void testCreateDb() {
        SQLiteDatabase db = new AppLpcDBHelper(this.mContext)
                .getWritableDatabase();
        assertEquals(true, db.isOpen());
        db.close();
    }

    public void testInsertReadDb(){
        String testTitle = "Test news 1";
        String testDate = "01/01/2001";
        String testContent = "Test de ma news 1 !";

        SQLiteDatabase db = new AppLpcDBHelper(this.mContext)
                .getWritableDatabase();
        assertEquals(true, db.isOpen());

        ContentValues contentValues = new ContentValues();
        contentValues.put(AppLpcDBContract.ParentsEntry.COLUMN_TITLE, testTitle);
        contentValues.put(AppLpcDBContract.ParentsEntry.COLUMN_DATE, testDate);
        contentValues.put(AppLpcDBContract.ParentsEntry.COLUMN_CONTENT, testContent);

        long parentsRowId;
        parentsRowId = db.insert(AppLpcDBContract.ParentsEntry.TABLE_NAME,
                null, contentValues);

        assertTrue(parentsRowId != -1);

        String[] columnsToRead = {AppLpcDBContract.ParentsEntry._ID,
                AppLpcDBContract.ParentsEntry.COLUMN_TITLE,
                AppLpcDBContract.ParentsEntry.COLUMN_DATE,
                AppLpcDBContract.ParentsEntry.COLUMN_CONTENT
        };

        Cursor cursor = db.query(AppLpcDBContract.ParentsEntry.TABLE_NAME,//table name to query
                columnsToRead,
                null,//columns for the where clause
                null,//values for the where clause
                null,//columns to group by
                null, //columns to filter by rows group
                null //sort order
        );

        if(cursor.moveToFirst()){
            String title = cursor.getString(
                    cursor.getColumnIndex(AppLpcDBContract.ParentsEntry.COLUMN_TITLE));
            String date = cursor.getString(
                    cursor.getColumnIndex(AppLpcDBContract.ParentsEntry.COLUMN_DATE));
            String content = cursor.getString(
                    cursor.getColumnIndex(AppLpcDBContract.ParentsEntry.COLUMN_CONTENT));
            assertEquals(title,testTitle);
            assertEquals(date,testDate);
            assertEquals(content,testContent);

            cursor.close();
        }else{
            fail("No value returned ");
        }
    }
}

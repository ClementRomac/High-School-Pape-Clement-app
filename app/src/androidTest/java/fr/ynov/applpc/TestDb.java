package fr.ynov.applpc;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import android.util.Log;

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

    /////////////// PARENTS //////////////////
    public void testInsertReadDbParents(){
        String[] table_columns = new String[]{AppLpcDBContract.ParentsEntry.COLUMN_TITLE,
                AppLpcDBContract.ParentsEntry.COLUMN_DATE,
                AppLpcDBContract.ParentsEntry.COLUMN_CONTENT
        };
        String[] testValues = new String[]{"Test news 1 parents",
            "01/01/2002",
            "Test de ma news 1 parents!"};

        testInsertReadDB(AppLpcDBContract.ParentsEntry.TABLE_NAME, table_columns, testValues);
    }

    /////////////// STUDENTS //////////////////
    public void testInsertReadDbStudents(){
        String[] table_columns = {AppLpcDBContract.StudentsEntry.COLUMN_CLASS,
                AppLpcDBContract.StudentsEntry.COLUMN_MONDAY,
                AppLpcDBContract.StudentsEntry.COLUMN_TUESDAY,
                AppLpcDBContract.StudentsEntry.COLUMN_WEDNESDAY,
                AppLpcDBContract.StudentsEntry.COLUMN_THURSDAY,
                AppLpcDBContract.StudentsEntry.COLUMN_FRIDAY
        };

        String[] testValues = new String[]{"Terminale STI2D SIN",
            "8h-12h: SIN \n 13h-17h : TRANSVERSAL",
            "8h-12h: MATHS \n 13h-17h : TRANSVERSAL",
            "8h-12h: SIN \n 13h-17h : MATHS",
            "8h-12h: PYSIQUE \n 13h-17h : TRANSVERSAL",
            "8h-12h: SIN \n 13h-17h : PHYSIQUE"};

        testInsertReadDB(AppLpcDBContract.StudentsEntry.TABLE_NAME, table_columns, testValues);
    }

    /////////////// HIGH SCHOOL //////////////////
    public void testInsertReadDbHighDchool(){
        String[] table_columns = new String[]{AppLpcDBContract.HighSchoolEntry.COLUMN_TITLE,
                AppLpcDBContract.HighSchoolEntry.COLUMN_DATE,
                AppLpcDBContract.HighSchoolEntry.COLUMN_CONTENT
        };
        String[] testValues = new String[]{"Test news 1 lycee",
                "01/01/2004",
                "Test de ma news 1 lycee !"};

        testInsertReadDB(AppLpcDBContract.HighSchoolEntry.TABLE_NAME, table_columns, testValues);
    }

    /////////////// CVL //////////////////
    public void testInsertReadDbCVL(){
        String[] table_columns = new String[]{AppLpcDBContract.CVLEntry.COLUMN_TITLE,
                AppLpcDBContract.CVLEntry.COLUMN_DATE,
                AppLpcDBContract.CVLEntry.COLUMN_CONTENT
        };
        String[] testValues = new String[]{"Test news 1 cvl",
                "01/01/2011",
                "Test de ma news 1 cvl!"};

        testInsertReadDB(AppLpcDBContract.CVLEntry.TABLE_NAME, table_columns, testValues);
    }

    /////////////// CDI //////////////////
    public void testInsertReadDbCDI(){
        String[] table_columns = new String[]{AppLpcDBContract.CDIEntry.COLUMN_TITLE,
                AppLpcDBContract.CDIEntry.COLUMN_DATE,
                AppLpcDBContract.CDIEntry.COLUMN_CONTENT
        };
        String[] testValues = new String[]{"Test news 1 cdi",
                "01/01/2401",
                "Test de ma news 1 cdi!"};

        testInsertReadDB(AppLpcDBContract.CDIEntry.TABLE_NAME, table_columns, testValues);
    }

    // Test
    public void testInsertReadDB(String table_name, String[] table_columns, String[] testValues){
        SQLiteDatabase db = new AppLpcDBHelper(this.mContext)
                .getWritableDatabase();
        assertEquals(true, db.isOpen());

        ContentValues contentValues = new ContentValues();
        for(int i=0; i<table_columns.length; i++) {
            contentValues.put(table_columns[i], testValues[i]);
        }

        long contentRowId;
        contentRowId = db.insert(table_name,
                null, contentValues);

        assertTrue(contentRowId != -1);

        Cursor cursor = db.query(table_name,//table name to query
                table_columns,
                null,//columns for the where clause
                null,//values for the where clause
                null,//columns to group by
                null, //columns to filter by rows group
                null //sort order
        );

        if(cursor.moveToFirst()){
            for(int i=0; i<table_columns.length; i++) {
                assertEquals(cursor.getString(
                        cursor.getColumnIndex(table_columns[i])), testValues[i]);
            }

            cursor.close();
        }else{
            fail("No value returned ");
        }
    }
}

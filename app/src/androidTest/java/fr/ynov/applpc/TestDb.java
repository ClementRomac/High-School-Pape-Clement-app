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

    public void testInsertReadDb(){
        SQLiteDatabase db = new AppLpcDBHelper(this.mContext)
                .getWritableDatabase();
        assertEquals(true, db.isOpen());

        /////////////// PARENTS //////////////////
        String testTitle = "Test news 1";
        String testDate = "01/01/2001";
        String testContent = "Test de ma news 1 !";

        ContentValues contentValues = new ContentValues();
        contentValues.put(AppLpcDBContract.ParentsEntry.COLUMN_TITLE, testTitle);
        contentValues.put(AppLpcDBContract.ParentsEntry.COLUMN_DATE, testDate);
        contentValues.put(AppLpcDBContract.ParentsEntry.COLUMN_CONTENT, testContent);

        long parentsRowId;
        parentsRowId = db.insert(AppLpcDBContract.ParentsEntry.TABLE_NAME,
                null, contentValues);

        assertTrue(parentsRowId != -1);

        String[] columnsToRead = {
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

        /////////////// STUDENTS //////////////////
        String testClass = "Terminale STI2D SIN";
        String testMonday = "8h-12h: SIN \n 13h-17h : TRANSVERSAL";
        String testTuesday = "8h-12h: MATHS \n 13h-17h : TRANSVERSAL";
        String testWednesday = "8h-12h: SIN \n 13h-17h : MATHS";
        String testThursday = "8h-12h: PYSIQUE \n 13h-17h : TRANSVERSAL";
        String testFriday = "8h-12h: SIN \n 13h-17h : PHYSIQUE";

        ContentValues contentValuesStudents = new ContentValues();
        contentValuesStudents.put(AppLpcDBContract.StudentsEntry.COLUMN_CLASS, testClass);
        contentValuesStudents.put(AppLpcDBContract.StudentsEntry.COLUMN_MONDAY, testMonday);
        contentValuesStudents.put(AppLpcDBContract.StudentsEntry.COLUMN_TUESDAY, testTuesday);
        contentValuesStudents.put(AppLpcDBContract.StudentsEntry.COLUMN_WEDNESDAY, testWednesday);
        contentValuesStudents.put(AppLpcDBContract.StudentsEntry.COLUMN_THURSDAY, testThursday);
        contentValuesStudents.put(AppLpcDBContract.StudentsEntry.COLUMN_FRIDAY, testFriday);

        long studentsRowId;
        studentsRowId = db.insert(AppLpcDBContract.StudentsEntry.TABLE_NAME,
                null, contentValuesStudents);

        assertTrue(studentsRowId != -1);

        String[] studentsColumnsToRead = {AppLpcDBContract.StudentsEntry.COLUMN_CLASS,
                AppLpcDBContract.StudentsEntry.COLUMN_MONDAY,
                AppLpcDBContract.StudentsEntry.COLUMN_TUESDAY,
                AppLpcDBContract.StudentsEntry.COLUMN_WEDNESDAY,
                AppLpcDBContract.StudentsEntry.COLUMN_THURSDAY,
                AppLpcDBContract.StudentsEntry.COLUMN_FRIDAY
        };

        Cursor studentsCursor = db.query(AppLpcDBContract.StudentsEntry.TABLE_NAME,//table name to query
                studentsColumnsToRead,
                null,//columns for the where clause
                null,//values for the where clause
                null,//columns to group by
                null, //columns to filter by rows group
                null //sort order
        );

        if(studentsCursor.moveToFirst()){
            String studentClass = studentsCursor.getString(
                    studentsCursor.getColumnIndex(AppLpcDBContract.StudentsEntry.COLUMN_CLASS));
            String monday = studentsCursor.getString(
                    studentsCursor.getColumnIndex(AppLpcDBContract.StudentsEntry.COLUMN_MONDAY));
            String tuesday = studentsCursor.getString(
                    studentsCursor.getColumnIndex(AppLpcDBContract.StudentsEntry.COLUMN_TUESDAY));
            String wednesday = studentsCursor.getString(
                    studentsCursor.getColumnIndex(AppLpcDBContract.StudentsEntry.COLUMN_WEDNESDAY));
            String thursday = studentsCursor.getString(
                    studentsCursor.getColumnIndex(AppLpcDBContract.StudentsEntry.COLUMN_THURSDAY));
            String friday = studentsCursor.getString(
                    studentsCursor.getColumnIndex(AppLpcDBContract.StudentsEntry.COLUMN_FRIDAY));
            assertEquals(studentClass,testClass);
            assertEquals(monday,testMonday);
            assertEquals(tuesday,testTuesday);
            assertEquals(wednesday,testWednesday);
            assertEquals(thursday,testThursday);
            assertEquals(friday,testFriday);

            cursor.close();
        }else{
            fail("No value returned ");
        }
    }
}

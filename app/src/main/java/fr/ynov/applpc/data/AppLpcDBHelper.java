package fr.ynov.applpc.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import fr.ynov.applpc.data.AppLpcDBContract.*;

/**
 * Created by clément on 24/02/2016.
 */
public class AppLpcDBHelper extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "applpc.db";

    public AppLpcDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_PARENTS_TABLE = "CREATE TABLE " +
                ParentsEntry.TABLE_NAME + "(" +
                ParentsEntry.COLUMN_DATE + " TEXT NOT NULL," +
                ParentsEntry.COLUMN_TITLE + " TEXT NOT NULL," +
                ParentsEntry.COLUMN_CONTENT + " TEXT NOT NULL);";

        db.execSQL(SQL_CREATE_PARENTS_TABLE);

        final String SQL_CREATE_STUDENTS_TABLE = "CREATE TABLE " +
                StudentsEntry.TABLE_NAME + "(" +
                StudentsEntry.COLUMN_CLASS + " TEXT NOT NULL," +
                StudentsEntry.COLUMN_MONDAY + " TEXT NOT NULL," +
                StudentsEntry.COLUMN_TUESDAY + " TEXT NOT NULL," +
                StudentsEntry.COLUMN_WEDNESDAY + " TEXT NOT NULL," +
                StudentsEntry.COLUMN_THURSDAY + " TEXT NOT NULL," +
                StudentsEntry.COLUMN_FRIDAY + " TEXT NOT NULL);";

        db.execSQL(SQL_CREATE_STUDENTS_TABLE);

        final String SQL_CREATE_HIGH_SCHOOL_TABLE = "CREATE TABLE " +
                HighSchoolEntry.TABLE_NAME + "(" +
                HighSchoolEntry.COLUMN_DATE + " TEXT NOT NULL," +
                HighSchoolEntry.COLUMN_TITLE + " TEXT NOT NULL," +
                HighSchoolEntry.COLUMN_CONTENT + " TEXT NOT NULL);";

        db.execSQL(SQL_CREATE_HIGH_SCHOOL_TABLE);

        final String SQL_CREATE_CVL_TABLE = "CREATE TABLE " +
                CVLEntry.TABLE_NAME + "(" +
                CVLEntry.COLUMN_DATE + " TEXT NOT NULL," +
                CVLEntry.COLUMN_TITLE + " TEXT NOT NULL," +
                CVLEntry.COLUMN_CONTENT + " TEXT NOT NULL);";

        db.execSQL(SQL_CREATE_CVL_TABLE);

        final String SQL_CREATE_CDI_TABLE = "CREATE TABLE " +
                CDIEntry.TABLE_NAME + "(" +
                CDIEntry.COLUMN_DATE + " TEXT NOT NULL," +
                CDIEntry.COLUMN_TITLE + " TEXT NOT NULL," +
                CDIEntry.COLUMN_CONTENT + " TEXT NOT NULL);";

        db.execSQL(SQL_CREATE_CDI_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ParentsEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + StudentsEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + HighSchoolEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + CVLEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + CDIEntry.TABLE_NAME);

        onCreate(db);
    }
}

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
                ParentsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                ParentsEntry.COLUMN_DATE + " TEXT NOT NULL," +
                ParentsEntry.COLUMN_TITLE + " TEXT NOT NULL," +
                ParentsEntry.COLUMN_CONTENT + "TEXT NOT NULL);";

        db.execSQL(SQL_CREATE_PARENTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ParentsEntry.TABLE_NAME);
        onCreate(db);
    }
}

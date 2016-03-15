package fr.ynov.applpc.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

import fr.ynov.applpc.data.AppLpcDBContract.*;

/**
 * Created by clément on 23/02/2016.
 */
public class DataStudentsProvider extends CustomDataProvider{

    public DataStudentsProvider(Context context){

        this.context = context;
        mOpenHelper = new AppLpcDBHelper(this.context);
        table_colums = new String[]{StudentsEntry.COLUMN_CLASS,
                StudentsEntry.COLUMN_MONDAY,
                StudentsEntry.COLUMN_TUESDAY,
                StudentsEntry.COLUMN_WEDNESDAY,
                StudentsEntry.COLUMN_THURSDAY,
                StudentsEntry.COLUMN_FRIDAY
        };
        table_name = StudentsEntry.TABLE_NAME;

    }

    public String[] getClasses(){
        if(isOnline()) {
            callAPI(); //if User has internet connection => call Api and put datas in DB
        }
        else{
            Toast.makeText(context, "Vous n'êtes pas connecté à Internet, vos données ne sont donc pas actualisées.", Toast.LENGTH_LONG).show();
        }

        String[] classes = readClasses(); // get datas from DB

        return classes;
    }

    public String[][] getScheduleByClass(String studentClass){

        String[][] schedule = readSchedule(studentClass); // get schedule with the class

        return schedule;
    }

    private String[][] readSchedule(String studentClass){
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        String[] columnsToRead = {
                StudentsEntry.COLUMN_MONDAY,
                StudentsEntry.COLUMN_TUESDAY,
                StudentsEntry.COLUMN_WEDNESDAY,
                StudentsEntry.COLUMN_THURSDAY,
                StudentsEntry.COLUMN_FRIDAY
        };
        Cursor cursor = db.query(table_name, columnsToRead, StudentsEntry.COLUMN_CLASS+"='"+studentClass+"'", null, null, null, null);
        cursor.moveToFirst(); //Read Schedule where Class = Class asked
        String[][] result = new String[cursor.getCount()][columnsToRead.length];
        for (int i=0; i<cursor.getCount(); i++){
            for(int j=0; j<columnsToRead.length; j++) {
                result[i][j] = cursor.getString(cursor.getColumnIndex(columnsToRead[j]));
            }

            cursor.moveToNext();

            //Log.d(LOG_TAG + "-READ-DB-SCHEDULE", result[i][0] + "-" + result[i][1] + "-" + result[i][2]);
        }
        cursor.close();
        return result;
    }

    private String[] readClasses(){
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        String[] columnsToRead = {
                StudentsEntry.COLUMN_CLASS
        };
        Cursor cursor = db.query(table_name, columnsToRead, null, null, null, null, null); //Get the list of classes
        cursor.moveToFirst();
        String[] result = new String[cursor.getCount()];
        for (int i=0; i<cursor.getCount(); i++){
            result[i] = cursor.getString(cursor.getColumnIndex(StudentsEntry.COLUMN_CLASS));

            cursor.moveToNext();

            //Log.d(LOG_TAG + "-READ-DB", result[i]);
        }
        cursor.close();
        return result;
    }
}

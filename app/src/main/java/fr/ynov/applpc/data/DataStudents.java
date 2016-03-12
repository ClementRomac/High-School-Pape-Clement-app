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
public class DataStudents {
    private final String LOG_TAG = DataStudents.class.getSimpleName();
    private Context context;
    private AppLpcDBHelper mOpenHelper;

    public DataStudents(Context context){

        this.context = context;
        mOpenHelper = new AppLpcDBHelper(this.context);

    }

    public String[] getClasses(){
        if(isOnline()) {
            callAPI();
        }
        else{
            Toast.makeText(context, "Vous n'êtes pas connecté à Internet, vos données ne sont donc pas actualisée.", Toast.LENGTH_LONG).show();
        }

        String[] classes = readClasses();

        return classes;
    }

    public String[][] getScheduleByClass(String studentClass){

        String[][] schedule = readSchedule(studentClass);

        return schedule;
    }

    private String[][] parseDatas(Object datas) throws JSONException {
        String jsonData = (String) datas;

        JSONArray parentsJson = new JSONArray(jsonData);

        String[][] resultStrs = new String[parentsJson.length()][6];
        for (int i = 0; i < parentsJson.length(); i++ ){
            JSONObject parentsInfo = parentsJson.getJSONObject(i);
            resultStrs[i][0] = parentsInfo.getString("class");
            resultStrs[i][1] = parentsInfo.getString("monday");
            resultStrs[i][2] = parentsInfo.getString("tuesday");
            resultStrs[i][3] = parentsInfo.getString("wednesday");
            resultStrs[i][4] = parentsInfo.getString("thursday");
            resultStrs[i][5] = parentsInfo.getString("friday");
            //Log.d(LOG_TAG+"-TEST", resultStrs[i][0]+"-"+resultStrs[i][1]+"-"+resultStrs[i][2]);
        }

        return resultStrs;
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null &&
                cm.getActiveNetworkInfo().isConnectedOrConnecting();
    }

    private void callAPI(){
        FetchDataTask fetchDataTask = new FetchDataTask();
        fetchDataTask.execute("infos_students");
        Object dataJSON = null;
        try {
            dataJSON = fetchDataTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        try {
            String[][] datas = parseDatas(dataJSON);
            insertDB(datas);

        } catch (JSONException e) {
            e.printStackTrace();
        }catch (NullPointerException e){
            e.printStackTrace();
        }
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
        Cursor cursor = db.query(StudentsEntry.TABLE_NAME, columnsToRead, StudentsEntry.COLUMN_CLASS+"='"+studentClass+"'", null, null, null, null);
        cursor.moveToFirst();
        String[][] result = new String[cursor.getCount()][columnsToRead.length];
        for (int i=0; i<cursor.getCount(); i++){
            result[i][0] = cursor.getString(cursor.getColumnIndex(StudentsEntry.COLUMN_MONDAY));
            result[i][1] = cursor.getString(cursor.getColumnIndex(StudentsEntry.COLUMN_TUESDAY));
            result[i][2] = cursor.getString(cursor.getColumnIndex(StudentsEntry.COLUMN_WEDNESDAY));
            result[i][3] = cursor.getString(cursor.getColumnIndex(StudentsEntry.COLUMN_THURSDAY));
            result[i][4] = cursor.getString(cursor.getColumnIndex(StudentsEntry.COLUMN_FRIDAY));

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
        Cursor cursor = db.query(StudentsEntry.TABLE_NAME, columnsToRead, null, null, null, null, null);
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

    private void insertDB(String[][] datas) {
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        db.delete(StudentsEntry.TABLE_NAME, null, null);
        ContentValues contentValues = new ContentValues();
        for (String[] data : datas) {
            contentValues.put(StudentsEntry.COLUMN_CLASS, data[0]);
            contentValues.put(StudentsEntry.COLUMN_MONDAY, data[1]);
            contentValues.put(StudentsEntry.COLUMN_TUESDAY, data[2]);
            contentValues.put(StudentsEntry.COLUMN_WEDNESDAY, data[3]);
            contentValues.put(StudentsEntry.COLUMN_THURSDAY, data[4]);
            contentValues.put(StudentsEntry.COLUMN_FRIDAY, data[5]);

            //Log.d(LOG_TAG + "-API", contentValues.getAsString(ParentsEntry.COLUMN_TITLE));

            db.insert(StudentsEntry.TABLE_NAME, null, contentValues);
        }
    }
}

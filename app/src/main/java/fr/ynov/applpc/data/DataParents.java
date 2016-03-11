package fr.ynov.applpc.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

import fr.ynov.applpc.data.AppLpcDBContract.*;

/**
 * Created by clément on 23/02/2016.
 */
public class DataParents {
    private final String LOG_TAG = DataParents.class.getSimpleName();
    private Context context;
    private AppLpcDBHelper mOpenHelper;

    public DataParents(Context context){

        this.context = context;
        mOpenHelper = new AppLpcDBHelper(this.context);

    }

    public String[][] getDatas(){
        if(isOnline()) {
            callAPI();
        }
        else{
            Toast.makeText(context, "Vous n'êtes pas connecté à Internet, vos données ne sont donc pas actualisée.", Toast.LENGTH_LONG).show();
        }

        String[][] datas = readDB();

        return datas;
    }

    private String[][] parseDatas(Object datas) throws JSONException {
        String jsonData = (String) datas;

        JSONArray parentsJson = new JSONArray(jsonData);

        String[][] resultStrs = new String[parentsJson.length()][3];
        for (int i = 0; i < parentsJson.length(); i++ ){
            JSONObject parentsInfo = parentsJson.getJSONObject(i);
            resultStrs[i][0] = parentsInfo.getString("title");
            resultStrs[i][1] = parentsInfo.getString("date");
            resultStrs[i][2] = parentsInfo.getString("content");
            //Log.d(LOG_TAG, resultStrs[i][0]+"-"+resultStrs[i][1]+"-"+resultStrs[i][2]);
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
        fetchDataTask.execute("infos_parents");
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

    private String[][] readDB(){
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        String[] columnsToRead = {
                AppLpcDBContract.ParentsEntry.COLUMN_TITLE,
                AppLpcDBContract.ParentsEntry.COLUMN_DATE,
                AppLpcDBContract.ParentsEntry.COLUMN_CONTENT
        };
        Cursor cursor = db.query(ParentsEntry.TABLE_NAME, columnsToRead, null, null, null, null, null);
        cursor.moveToFirst();
        String[][] result = new String[cursor.getCount()][columnsToRead.length];
        for (int i=0; i<cursor.getCount(); i++){
            result[i][0] = cursor.getString(cursor.getColumnIndex(ParentsEntry.COLUMN_TITLE));
            result[i][1] = cursor.getString(cursor.getColumnIndex(ParentsEntry.COLUMN_DATE));
            result[i][2] = cursor.getString(cursor.getColumnIndex(ParentsEntry.COLUMN_CONTENT));

            cursor.moveToNext();

            //Log.d(LOG_TAG+"-READ-DB", result[i][0]+"-"+result[i][1]+"-"+result[i][2]);
        }
        return result;
    }

    private void insertDB(String[][] datas) {
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        db.delete(ParentsEntry.TABLE_NAME, null, null);
        ContentValues contentValues = new ContentValues();
        for (String[] data : datas) {
            contentValues.put(ParentsEntry.COLUMN_TITLE, data[0]);
            contentValues.put(ParentsEntry.COLUMN_DATE, data[1]);
            contentValues.put(ParentsEntry.COLUMN_CONTENT, data[2]);

            //Log.d(LOG_TAG + "-API", contentValues.getAsString(ParentsEntry.COLUMN_TITLE));

            db.insert(ParentsEntry.TABLE_NAME, null, contentValues);
        }
    }
}

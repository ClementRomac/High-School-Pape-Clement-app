package fr.ynov.applpc.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

/**
 * Created by clément on 13/03/2016.
 */
public abstract class CustomDataProvider {
    protected final String LOG_TAG = DataStudentsProvider.class.getSimpleName();
    protected Context context;
    protected AppLpcDBHelper mOpenHelper;
    protected String[] table_colums; //Store the colums
    protected String table_name; //Stor table name

    protected void callAPI(){
        FetchDataTask fetchDataTask = new FetchDataTask();
        fetchDataTask.execute(table_name); // Call API with the table name
        Object dataJSON = null;
        try {
            dataJSON = fetchDataTask.get(); // get the string
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        try {
            String[][] datas = parseDatas(dataJSON); // Parse it
            insertDB(datas); // Insert the datas in DB

        } catch (JSONException e) {
            e.printStackTrace();
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    protected String[][] parseDatas(Object datas) throws JSONException {
        String jsonData = (String) datas;

        JSONArray datasJson = new JSONArray(jsonData); // Transform String in JsonObject

        String[][] resultStrs = new String[datasJson.length()][table_colums.length]; // Create String array [row from api request][column of DB]
        for (int i = 0; i < datasJson.length(); i++ ){
            JSONObject parentsInfo = datasJson.getJSONObject(i);
            for(int j=0; j<table_colums.length; j++) {
                resultStrs[i][j] = parentsInfo.getString(table_colums[j]); // For each element, get the JsonObject with the name of the column in DB
            }
            //Log.d(LOG_TAG + "-TEST", resultStrs[i][0]+"-"+resultStrs[i][1]);
        }

        return resultStrs;
    }

    protected void insertDB(String[][] datas) {
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        db.delete(table_name, null, null);
        ContentValues contentValues = new ContentValues();
        for (String[] data : datas) {
            for(int j=0; j<table_colums.length; j++) {
                contentValues.put(table_colums[j], data[j]); // Insert each element in the column for the current row
            }
            //Log.d(LOG_TAG + "-API", contentValues.getAsString(table_colums[0]));

            db.insert(table_name, null, contentValues); // Insert the Row
        }
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null &&
                cm.getActiveNetworkInfo().isConnectedOrConnecting();
    }
}

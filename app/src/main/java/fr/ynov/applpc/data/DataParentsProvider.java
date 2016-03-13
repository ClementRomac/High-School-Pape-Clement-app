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
public class DataParentsProvider extends CustomDataProvider{

    public DataParentsProvider(Context context){

        this.context = context;
        mOpenHelper = new AppLpcDBHelper(this.context);
        table_colums = new String[]{ParentsEntry.COLUMN_TITLE,
                ParentsEntry.COLUMN_DATE,
                ParentsEntry.COLUMN_CONTENT
        };
        table_name = ParentsEntry.TABLE_NAME;

    }

    public String[][] getDatas(){
        if(isOnline()) {
            callAPI(); //if User has internet connection => call Api and put datas in DB
        }
        else{
            Toast.makeText(context, "Vous n'êtes pas connecté à Internet, vos données ne sont donc pas actualisée.", Toast.LENGTH_LONG).show();
        }

        String[][] datas = readDB(); // Read DB

        return datas;
    }


    private String[][] readDB(){
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        String[] columnsToRead = {
                AppLpcDBContract.ParentsEntry.COLUMN_TITLE,
                AppLpcDBContract.ParentsEntry.COLUMN_DATE,
                AppLpcDBContract.ParentsEntry.COLUMN_CONTENT
        };
        Cursor cursor = db.query(table_name, columnsToRead, null, null, null, null, null);
        cursor.moveToFirst();
        String[][] result = new String[cursor.getCount()][columnsToRead.length];
        for (int i=0; i<cursor.getCount(); i++){
            result[i][0] = cursor.getString(cursor.getColumnIndex(ParentsEntry.COLUMN_TITLE));
            result[i][1] = cursor.getString(cursor.getColumnIndex(ParentsEntry.COLUMN_DATE));
            result[i][2] = cursor.getString(cursor.getColumnIndex(ParentsEntry.COLUMN_CONTENT));

            cursor.moveToNext();

            //Log.d(LOG_TAG+"-READ-DB", result[i][0]+"-"+result[i][1]+"-"+result[i][2]);
        }
        cursor.close();
        return result;
    }
}

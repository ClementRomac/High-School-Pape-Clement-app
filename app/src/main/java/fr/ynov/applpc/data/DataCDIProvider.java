package fr.ynov.applpc.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import fr.ynov.applpc.data.AppLpcDBContract.CDIEntry;

/**
 * Created by clément on 23/02/2016.
 */
public class DataCDIProvider extends CustomDataProvider{

    public DataCDIProvider(Context context){

        this.context = context;
        mOpenHelper = new AppLpcDBHelper(this.context);
        table_colums = new String[]{CDIEntry.COLUMN_TITLE,
                CDIEntry.COLUMN_DATE,
                CDIEntry.COLUMN_CONTENT
        };
        table_name = CDIEntry.TABLE_NAME;

    }

    public String[][] getDatas(){
        if(isOnline()) {
            callAPI(); //if User has internet connection => call Api and put datas in DB
        }
        else{
            Toast.makeText(context, "Vous n'êtes pas connecté à Internet, vos données ne sont donc pas actualisée.", Toast.LENGTH_LONG).show();
        }

        String[][] datas = readDatasDB(); // Read DB

        return datas;
    }

    public String getLastDate(){
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        String[] columnsToRead = {
                CDIEntry.COLUMN_DATE,
        };
        Cursor cursor = db.query(table_name, columnsToRead, null, null, null, null, "1");
        cursor.moveToFirst();

        String lastDate = cursor.getString(cursor.getColumnIndex(columnsToRead[0]));
        cursor.close();

        return lastDate;
    }

    private String[][] readDatasDB(){
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        String[] columnsToRead = {
                CDIEntry.COLUMN_TITLE,
                CDIEntry.COLUMN_DATE,
                CDIEntry.COLUMN_CONTENT
        };
        Cursor cursor = db.query(table_name, columnsToRead, null, null, null, null, null);
        cursor.moveToFirst();
        String[][] result = new String[cursor.getCount()][columnsToRead.length];
        for (int i=0; i<cursor.getCount(); i++){
            for(int j=0; j<columnsToRead.length; j++) {
                result[i][j] = cursor.getString(cursor.getColumnIndex(columnsToRead[j]));
            }

            cursor.moveToNext();

            //Log.d(LOG_TAG + "-READ-DB", result[i][0] + "-" + result[i][1] + "-" + result[i][2]);
        }
        cursor.close();
        return result;
    }
}

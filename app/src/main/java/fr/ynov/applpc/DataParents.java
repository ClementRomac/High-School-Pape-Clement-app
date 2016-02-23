package fr.ynov.applpc;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

/**
 * Created by clément on 23/02/2016.
 */
public class DataParents {
    private final String LOG_TAG = DataParents.class.getSimpleName();
    private Context context;

    public DataParents(Context context){
        this.context = context;
    }

    public String[] getDatas() throws JSONException {
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

        return parseDatas(dataJSON);
    }

    private String[] parseDatas(Object datas) throws JSONException {
        String jsonData = (String) datas;

        JSONArray parentsJson = new JSONArray(jsonData);

        String[] resultStrs = new String[parentsJson.length()];
        for (int i = 0; i < parentsJson.length(); i++ ){
            JSONObject parentsInfo = parentsJson.getJSONObject(i);
            resultStrs[i] = parentsInfo.getString("title") + " - " + parentsInfo.getString("date") +
                    "\n" + parentsInfo.getString("content");
            //Log.d(LOG_TAG, resultStrs[i]);
        }

        return null;
    }
}

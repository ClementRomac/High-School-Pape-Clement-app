package fr.ynov.applpc.data;

import android.net.Uri;
import android.os.AsyncTask;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by clément on 17/02/2016.
 */
public class FetchDataTask extends AsyncTask<String, Void, Object> {
    @Override
    protected Object doInBackground(String... params) {
        final String LOG_TAG = this.getClass().getSimpleName();

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String DataJson = null;

        try{
            final String FORECAST_BASE_URL ="http://groovyou.hol.es/API_LPC.php?";

            Uri builtUri = Uri.parse(FORECAST_BASE_URL).buildUpon()
                    .appendQueryParameter("infos", params[0])
                    .appendQueryParameter("apikey", "3cc31cd246149aec68079241e71e98f6")
                    .build();
            //Construct URL
            URL url = new URL(builtUri.toString());

            //Create the Request
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            //Read Input
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if(inputStream == null){
                return null;
            }

            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while((line = reader.readLine()) != null ){
                buffer.append(line + "\n");
            }

            if(buffer.length() == 0){
                return null;
            }

            DataJson = buffer.toString();
            //Log.e("JSON", DataJson);

        }catch (IOException e){
            Log.e(LOG_TAG, "Error", e);
        }finally {
            if(urlConnection != null){
                urlConnection.disconnect();
            }
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    Log.e(LOG_TAG, "Error closing Stream", e);
                    e.printStackTrace();
                }
            }
        }

        return DataJson;
    }

    @Override
    protected void onPostExecute(Object object) {
        if(object != null){

        }
    }
}

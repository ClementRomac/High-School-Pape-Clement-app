package fr.ynov.applpc;

import android.content.Context;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import org.json.JSONException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onStart() {
        super.onStart();
        downloadDatas();
    }

    private void downloadDatas() {
        if(isOnline()) {
            DataParents dataParents = new DataParents(this);
            try {
                dataParents.getDatas();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else{
            Toast.makeText(this, "Vous n'êtes pas connecté à Internet, vos données ne sont donc pas actualisée.", Toast.LENGTH_LONG).show();
        }
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null &&
                cm.getActiveNetworkInfo().isConnectedOrConnecting();
    }
}

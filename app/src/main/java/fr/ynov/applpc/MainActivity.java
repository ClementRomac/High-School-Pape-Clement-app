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
        DataParents dataParents = new DataParents(this);
        try {
            dataParents.getDatas();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}

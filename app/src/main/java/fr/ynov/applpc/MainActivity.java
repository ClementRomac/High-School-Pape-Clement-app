package fr.ynov.applpc;

import android.content.Context;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import org.json.JSONException;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button buttonCdi = (Button) findViewById(R.id.buttoncdi);
        buttonCdi.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CdiActivity.class);
                startActivity(intent);
            }
        });

        final Button buttonCvl = (Button) findViewById(R.id.buttoncvl);
        buttonCvl.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CvlActivity.class);
                startActivity(intent);
            }
        });

        final Button buttonInfo = (Button) findViewById(R.id.buttoninfo);
        buttonInfo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, InfosActivity.class);
                startActivity(intent);
            }
        });
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

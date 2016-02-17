package fr.ynov.applpc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
        FetchDataTask fetchDataTask = new FetchDataTask();
        fetchDataTask.execute("infos_parents");
    }
}

package fr.ynov.applpc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class InfosActivity extends AppCompatActivity {
    //Nathan
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infos);

        ArrayList<ModelInfo> arrayModelInfo = new ArrayList<>();
        ModelInfoAdapter adapter = new ModelInfoAdapter(this, arrayModelInfo);
        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(adapter);

        for (int i = 0; i <= 10; i++){

            ModelInfo newInfo = new ModelInfo("Titre"+i, "text"+i);
            adapter.add(newInfo);

        }
    }
}

package fr.ynov.applpc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class CvlActivity extends AppCompatActivity {
//Nathan
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cvl);

        ListView listView = (ListView) findViewById(R.id.listview);
        ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> map;

        for (int i = 0; i <= 10; i++){
            map = new HashMap<String, String>();
            map.put("title", "title"+i);
            map.put("text", "text"+i);
            listItem.add(map);
        }

        SimpleAdapter mSchedule = new SimpleAdapter (this.getBaseContext(), listItem, R.layout.list_item_view,
                new String[] {"title", "text"}, new int[] {R.id.title, R.id.text});

        listView.setAdapter(mSchedule);
}
}

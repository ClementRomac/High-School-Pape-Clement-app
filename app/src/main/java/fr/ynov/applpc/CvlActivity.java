package fr.ynov.applpc;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class CvlActivity extends ActionBarActivity {
    //Nathan
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cvl);

        ArrayList<String[]> news = new ArrayList<String[]>();
        for (int i = 0; i <= 10; i++) {

            news.add(new String[]{"Titre" + i, "text" + i});
        }
        CustomAdapter myAdapter = new CustomAdapter(this, news, R.layout.list_item_view, new int[]{R.id.title_list_item, R.id.text_list_item});
        ListView listView = (ListView) findViewById(R.id.listview_cvl);
        listView.setAdapter(myAdapter);
    }
}

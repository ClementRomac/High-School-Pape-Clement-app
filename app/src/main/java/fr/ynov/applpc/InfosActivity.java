package fr.ynov.applpc;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

import fr.ynov.applpc.data.DataCDIProvider;
import fr.ynov.applpc.data.DataHighSchoolProvider;

public class InfosActivity extends ActionBarActivity {
    //Nathan
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infos);

        CustomAdapter myAdapter = new CustomAdapter(this, new ArrayList<String[]>(), R.layout.list_item_view,
                new int[]{R.id.title_list_item, R.id.date_list_item, R.id.text_list_item});
        ListView listView = (ListView) findViewById(R.id.listview_infos);
        listView.setAdapter(myAdapter);

        downloadDatas(myAdapter);
    }

    private void downloadDatas(CustomAdapter customAdapter){
        DataCDIProvider dataCDIProvider = new DataCDIProvider(this);
        customAdapter.addAll(Arrays.asList(dataCDIProvider.getDatas()));
    }
}

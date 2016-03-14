package fr.ynov.applpc;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import fr.ynov.applpc.data.DataCVLProvider;

public class CvlActivity extends ActionBarActivity {
    //Nathan
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Le C.V.L");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.cvl_color)));
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.cvl_fleche_retour);
        setContentView(R.layout.activity_cvl);

        CustomAdapter myAdapter = new CustomAdapter(this, new ArrayList<String[]>(), R.layout.list_item_view,
                new int[]{R.id.title_list_item, R.id.date_list_item, R.id.text_list_item},
                new int[]{this.getResources().getColor(R.color.cvl_color)});
        ListView listView = (ListView) findViewById(R.id.listview_cvl);
        listView.setAdapter(myAdapter);

        downloadDatas(myAdapter);
    }

    private void downloadDatas(CustomAdapter customAdapter){
        DataCVLProvider dataCVLProvider = new DataCVLProvider(this);
        customAdapter.addAll(Arrays.asList(dataCVLProvider.getDatas()));
    }

}

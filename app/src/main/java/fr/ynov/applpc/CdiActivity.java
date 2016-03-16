package fr.ynov.applpc;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

import fr.ynov.applpc.data.DataCDIProvider;

public class CdiActivity extends ActionBarActivity {
    //Nathan
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setCustomActionBar();
        setContentView(R.layout.activity_cdi);

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.listview_cdi);

        CustomAdapter myAdapter = new CustomAdapter(this, linearLayout, R.layout.list_item_view,
                new int[]{R.id.title_list_item, R.id.date_list_item, R.id.text_list_item}, new int[]{this.getResources().getColor(R.color.cdi_primary)});

        downloadDatas(myAdapter);
    }

    private void downloadDatas(CustomAdapter customAdapter){
        DataCDIProvider dataCDIProvider = new DataCDIProvider(this);
        customAdapter.addAll(new ArrayList<>(Arrays.asList(dataCDIProvider.getDatas())));
    }

    private void setCustomActionBar(){
        getSupportActionBar().setTitle(R.string.accueil_h1_cdi);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.cdi_primary)));
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.cdi_fleche_retour);
        getSupportActionBar().setElevation(0);
    }
}
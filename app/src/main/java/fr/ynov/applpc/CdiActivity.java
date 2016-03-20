package fr.ynov.applpc;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.LinearLayout;

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

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.lv_cdi);

        CustomAdapter myAdapter = new CustomAdapter(this, linearLayout, R.layout.list_item_view,
                new int[]{R.id.tv_list_item_title, R.id.tv_list_item_date, R.id.tv_list_item_text}, new int[]{this.getResources().getColor(R.color.cdi_primary)});

        downloadDatas(myAdapter);
    }

    private void downloadDatas(CustomAdapter customAdapter){
        DataCDIProvider dataCDIProvider = new DataCDIProvider(this);
        customAdapter.addAll(new ArrayList<>(Arrays.asList(dataCDIProvider.getDatas())));
    }

    private void setCustomActionBar(){
        getSupportActionBar().setTitle(R.string.home_title_cdi);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.cdi_primary)));
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.cdi_arrow);
        getSupportActionBar().setElevation(0);
    }
}
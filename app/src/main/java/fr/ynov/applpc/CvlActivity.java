package fr.ynov.applpc;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Arrays;

import fr.ynov.applpc.data.DataCVLProvider;

public class CvlActivity extends ActionBarActivity {
    //Nathan
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setCustomActionBar();
        setContentView(R.layout.activity_cvl);

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.lv_hight_school_cvl);

        CustomAdapter myAdapter = new CustomAdapter(this, linearLayout, R.layout.list_item_view,
                new int[]{R.id.tv_list_item_title, R.id.tv_list_item_date, R.id.tv_list_item_text}, new int[]{this.getResources().getColor(R.color.cvl_primary)});

        downloadDatas(myAdapter);
    }

    private void downloadDatas(CustomAdapter customAdapter){
        DataCVLProvider dataCVLProvider = new DataCVLProvider(this);
        customAdapter.addAll(new ArrayList<>(Arrays.asList(dataCVLProvider.getDatas())));
    }

    private void setCustomActionBar(){
        getSupportActionBar().setTitle(R.string.home_title_cvl);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.cvl_primary)));
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.cvl_arrow);
        getSupportActionBar().setElevation(0);
    }

}

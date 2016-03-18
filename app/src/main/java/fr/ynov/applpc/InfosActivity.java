package fr.ynov.applpc;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Arrays;

import fr.ynov.applpc.data.DataHighSchoolProvider;

public class InfosActivity extends ActionBarActivity {
    //Nathan
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setCustomActionBar();
        setContentView(R.layout.activity_infos);

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.LV_hight_school_infos);

        CustomAdapter myAdapter = new CustomAdapter(this, linearLayout, R.layout.list_item_view,
                new int[]{R.id.TV_list_item_title, R.id.TV_list_item_date, R.id.TV_list_item_text}, new int[]{this.getResources().getColor(R.color.infos_primary)});

        downloadDatas(myAdapter);
    }

    private void downloadDatas(CustomAdapter customAdapter){
        DataHighSchoolProvider dataHighSchoolProvider = new DataHighSchoolProvider(this);
        customAdapter.addAll(new ArrayList<>(Arrays.asList(dataHighSchoolProvider.getDatas())));
    }

    private void setCustomActionBar(){
        getSupportActionBar().setTitle(R.string.home_title_infos);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.infos_primary)));
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.infos_arrow);
        getSupportActionBar().setElevation(0);
    }
}

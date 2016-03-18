package fr.ynov.applpc;

import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Arrays;

import fr.ynov.applpc.data.DataParentsProvider;

public class ParentsActivity extends ActionBarActivity {
    //Cedric

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setCustomActionBar();
        setContentView(R.layout.activity_parents);

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.listview_parents);

        CustomAdapter myAdapter = new CustomAdapter(this, linearLayout, R.layout.list_item_view,
                new int[]{R.id.TV_list_item_title, R.id.TV_list_item_date, R.id.TV_list_item_text}, new int[]{this.getResources().getColor(R.color.parents_primary)});

        downloadDatas(myAdapter);
    }

    private void downloadDatas(CustomAdapter customAdapter){
        DataParentsProvider dataParentsProvider = new DataParentsProvider(this);
        customAdapter.addAll(new ArrayList<>(Arrays.asList(dataParentsProvider.getDatas())));
    }

    private void setCustomActionBar(){
        getSupportActionBar().setTitle(R.string.accueil_h1_parent);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.parents_primary)));
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.parents_fleche_retour);
        getSupportActionBar().setElevation(0);
    }
}

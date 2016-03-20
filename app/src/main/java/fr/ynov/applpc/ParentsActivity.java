package fr.ynov.applpc;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.lv_parents_news);

        CustomAdapter myAdapter = new CustomAdapter(this, linearLayout, R.layout.list_item_view,
                new int[]{R.id.tv_list_item_title, R.id.tv_list_item_date, R.id.tv_list_item_text}, new int[]{this.getResources().getColor(R.color.parents_primary)});

        downloadDatas(myAdapter);

        ImageView webBrowser = (ImageView)findViewById(R.id.iv_parents_resultats_de_mon_enfant);
        webBrowser.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String url = "http://lyceepapeclement.fr";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });
    }

    private void downloadDatas(CustomAdapter customAdapter){
        DataParentsProvider dataParentsProvider = new DataParentsProvider(this);
        customAdapter.addAll(new ArrayList<>(Arrays.asList(dataParentsProvider.getDatas())));
    }

    private void setCustomActionBar(){
        getSupportActionBar().setTitle(R.string.parents_activity_title);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.parents_primary)));
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.parents_fleche_retour);
        getSupportActionBar().setElevation(0);
    }
}

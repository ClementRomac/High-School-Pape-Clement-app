package fr.ynov.applpc;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class OrietationActivity extends ActionBarActivity {
//Axel tt
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Orientation");
        setCustomActionBar();
        setContentView(R.layout.activity_orietation);

        TextView b_browser = (TextView)findViewById(R.id.textView_orientation);
        b_browser.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String url = "http://www.onisep.fr";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });
    }

    private void setCustomActionBar(){
        getSupportActionBar().setTitle(R.string.accueil_h1_cdi);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.orientation_primary)));
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.orientation_fleche_retour);
        getSupportActionBar().setElevation(0);
    }


}

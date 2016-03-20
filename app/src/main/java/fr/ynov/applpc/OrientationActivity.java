package fr.ynov.applpc;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class OrientationActivity extends ActionBarActivity {
//Axel tt
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomActionBar();
        setContentView(R.layout.activity_orientation);

        final TextView webBrowser = (TextView)findViewById(R.id.tv_orientation);
        webBrowser.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String url = "http://"+webBrowser.getText().toString();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });
    }

    private void setCustomActionBar(){
        getSupportActionBar().setTitle(R.string.orientation_activity_title);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.orientation_primary)));
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.orientation_fleche_retour);
        getSupportActionBar().setElevation(0);
    }


}

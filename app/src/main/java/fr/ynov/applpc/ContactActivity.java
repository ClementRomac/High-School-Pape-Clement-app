package fr.ynov.applpc;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ContactActivity extends ActionBarActivity {
    //Axel
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        setCustomActionBar();

        TextView b_browser = (TextView)findViewById(R.id.textView_phone);
        b_browser.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String phone = "0557266300";
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(phone));
                startActivity(intent);
            }
        });

    }


    private void setCustomActionBar(){
        getSupportActionBar().setTitle(R.string.accueil_h1_cdi);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.contact_primary)));
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.contact_fleche_retour);
        getSupportActionBar().setElevation(0);
    }

}

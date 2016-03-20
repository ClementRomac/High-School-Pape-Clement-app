package fr.ynov.applpc;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;

public class ContactActivity extends ActionBarActivity {
    //Axel
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        setCustomActionBar();

        TextView phoneBrowser = (TextView)findViewById(R.id.tv_phone);
        phoneBrowser.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String phone = "tel: 0557266300";
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(phone));
                startActivity(intent);
            }
        });

        TextView mailBrowser = (TextView)findViewById(R.id.tv_mail);
        mailBrowser.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("message/rfc822");
                intent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[] {"vie-scolaire@lycee-pape-clement.fr"});
                Intent mailer = Intent.createChooser(intent, null);
                startActivity(mailer);
            }
        });

    }


    private void setCustomActionBar(){
        getSupportActionBar().setTitle(R.string.home_title_contact);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.contact_primary)));
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.contact_fleche_retour);
        getSupportActionBar().setElevation(0);
    }

}

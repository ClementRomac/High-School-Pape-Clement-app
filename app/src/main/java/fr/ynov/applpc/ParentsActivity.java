package fr.ynov.applpc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class ParentsActivity extends AppCompatActivity {
    //Cedric
    private LinearLayout retour_accueil = null;

    private View.OnClickListener redirect_menu = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(ParentsActivity.this, MainActivity.class);

            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parents);
        retour_accueil = (LinearLayout) findViewById(R.id.retour_accueil);

        retour_accueil.setOnClickListener(redirect_menu);

    }
}

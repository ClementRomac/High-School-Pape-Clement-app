package fr.ynov.applpc;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class ParentsActivity extends ActionBarActivity {
    //Cedric

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parents);
        getSupportActionBar().setTitle("Parents");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.accueil_bck_parent)));
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.parents_fleche_retour);
    }
}

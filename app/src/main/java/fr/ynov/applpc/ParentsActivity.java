package fr.ynov.applpc;

import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

public class ParentsActivity extends ActionBarActivity {
    //Cedric

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setCustomActionBar();
        setContentView(R.layout.activity_parents);
    }

    private void setCustomActionBar(){
        getSupportActionBar().setTitle(R.string.accueil_h1_parent);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.parents_primary)));
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.parents_fleche_retour);
        getSupportActionBar().setElevation(0);
    }
}

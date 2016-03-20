package fr.ynov.applpc;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.RelativeLayout;

public class HighSchoolActivity extends ActionBarActivity {
//Pierre

    private RelativeLayout cdi = null;
    private RelativeLayout cvl = null;
    private RelativeLayout coordonnees = null;
    private RelativeLayout orientation = null;
    private RelativeLayout infos = null;

    private View.OnClickListener redirect_cdi = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            findViewById(R.id.fleche_cdi).setBackgroundResource(R.drawable.flechecdi);
            Intent intent = new Intent(HighSchoolActivity.this, CdiActivity.class);

            startActivity(intent);
        }
    };
    private View.OnClickListener redirect_cvl = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            findViewById(R.id.fleche_cvl).setBackgroundResource(R.drawable.flechecvl);
            Intent intent = new Intent(HighSchoolActivity.this, CvlActivity.class);

            startActivity(intent);
        }
    };
    private View.OnClickListener redirect_coordonnes = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            findViewById(R.id.fleche_contact).setBackgroundResource(R.drawable.flechecoo);
            Intent intent = new Intent(HighSchoolActivity.this, ContactActivity.class);

            startActivity(intent);
        }
    };
    private View.OnClickListener redirect_orientation = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            findViewById(R.id.fleche_orientation).setBackgroundResource(R.drawable.flecheorien);
            Intent intent = new Intent(HighSchoolActivity.this, OrietationActivity.class);

            startActivity(intent);
        }
    };
    private View.OnClickListener redirect_infos = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            findViewById(R.id.fleche_infos).setBackgroundResource(R.drawable.flecheinfos);
            Intent intent = new Intent(HighSchoolActivity.this, InfosActivity.class);

            startActivity(intent);
        }
    };

    private void resetPointers(){
        findViewById(R.id.fleche_cdi).setBackgroundResource(R.drawable.flechebase);
        findViewById(R.id.fleche_cvl).setBackgroundResource(R.drawable.flechebase);
        findViewById(R.id.fleche_contact).setBackgroundResource(R.drawable.flechebase);
        findViewById(R.id.fleche_orientation).setBackgroundResource(R.drawable.flechebase);
        findViewById(R.id.fleche_infos).setBackgroundResource(R.drawable.flechebase);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setCustomActionBar();
        setContentView(R.layout.activity_high_school);

        cdi = (RelativeLayout) findViewById(R.id.relative_CDI);
        cvl = (RelativeLayout) findViewById(R.id.relative_CVL);
        coordonnees = (RelativeLayout) findViewById(R.id.relative_coordonnes);
        orientation = (RelativeLayout) findViewById(R.id.relative_orientation);
        infos = (RelativeLayout) findViewById(R.id.relative_infos);

        cdi.setOnClickListener(redirect_cdi);
        cvl.setOnClickListener(redirect_cvl);
        coordonnees.setOnClickListener(redirect_coordonnes);
        orientation.setOnClickListener(redirect_orientation);
        infos.setOnClickListener(redirect_infos);

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        resetPointers();
    }

    private void setCustomActionBar(){
        getSupportActionBar().setTitle(R.string.home_title_high_school);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.high_school_primary)));
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.high_school_fleche_retour);
        getSupportActionBar().setElevation(0);
    }
}

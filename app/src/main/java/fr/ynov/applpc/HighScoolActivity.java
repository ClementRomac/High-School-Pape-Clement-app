package fr.ynov.applpc;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import fr.ynov.applpc.data.DataCDIProvider;
import fr.ynov.applpc.data.DataCVLProvider;
import fr.ynov.applpc.data.DataHighSchoolProvider;

public class HighScoolActivity extends ActionBarActivity {
//Pierre

    private LinearLayout cdi = null;
    private LinearLayout cvl = null;
    private LinearLayout coordonnes = null;
    private LinearLayout orientation = null;
    private LinearLayout infos = null;

    private View.OnClickListener redirect_cdi = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            findViewById(R.id.iv_highschool_fleche_cdi).setBackgroundResource(R.drawable.flechecdi);
            Intent intent = new Intent(HighScoolActivity.this, CdiActivity.class);

            startActivity(intent);
        }
    };
    private View.OnClickListener redirect_cvl = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            findViewById(R.id.iv_highschool_fleche_cvl).setBackgroundResource(R.drawable.flechecvl);
            Intent intent = new Intent(HighScoolActivity.this, CvlActivity.class);

            startActivity(intent);
        }
    };
    private View.OnClickListener redirect_coordonnes = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            findViewById(R.id.iv_highschool_fleche_contact).setBackgroundResource(R.drawable.flechecoo);
            Intent intent = new Intent(HighScoolActivity.this, ContactActivity.class);

            startActivity(intent);
        }
    };
    private View.OnClickListener redirect_orientation = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            findViewById(R.id.iv_highschool_fleche_orientation).setBackgroundResource(R.drawable.flecheorien);
            Intent intent = new Intent(HighScoolActivity.this, OrientationActivity.class);

            startActivity(intent);
        }
    };
    private View.OnClickListener redirect_infos = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            findViewById(R.id.iv_highschool_fleche_infos).setBackgroundResource(R.drawable.flecheinfos);
            Intent intent = new Intent(HighScoolActivity.this, InfosActivity.class);

            startActivity(intent);
        }
    };

    private void resetPointers(){
        findViewById(R.id.iv_highschool_fleche_cdi).setBackgroundResource(R.drawable.flechebase);
        findViewById(R.id.iv_highschool_fleche_cvl).setBackgroundResource(R.drawable.flechebase);
        findViewById(R.id.iv_highschool_fleche_contact).setBackgroundResource(R.drawable.flechebase);
        findViewById(R.id.iv_highschool_fleche_orientation).setBackgroundResource(R.drawable.flechebase);
        findViewById(R.id.iv_highschool_fleche_infos).setBackgroundResource(R.drawable.flechebase);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setCustomActionBar();
        setContentView(R.layout.activity_high_scool);

        cdi = (LinearLayout) findViewById(R.id.ll_highschool_cdi);
        cvl = (LinearLayout) findViewById(R.id.ll_highschool_cvl);
        coordonnes = (LinearLayout) findViewById(R.id.ll_highschool_contact);
        orientation = (LinearLayout) findViewById(R.id.ll_highschool_orientation);
        infos = (LinearLayout) findViewById(R.id.ll_highschool_infos);

        cdi.setOnClickListener(redirect_cdi);
        cvl.setOnClickListener(redirect_cvl);
        coordonnes.setOnClickListener(redirect_coordonnes);
        orientation.setOnClickListener(redirect_orientation);
        infos.setOnClickListener(redirect_infos);
        
        setLastUpdates();

    }

    private void setLastUpdates() {
        ((TextView)findViewById(R.id.tv_highschool_cdi)).append(new DataCDIProvider(this).getLastDate());
        ((TextView)findViewById(R.id.tv_highschool_cvl)).append(new DataCVLProvider(this).getLastDate());
        ((TextView)findViewById(R.id.tv_highschool_infos)).append(new DataHighSchoolProvider(this).getLastDate());
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        resetPointers();
    }

    private void setCustomActionBar(){
        getSupportActionBar().setTitle(R.string.high_school_activity_title);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.high_school_primary)));
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.high_school_fleche_retour);
        getSupportActionBar().setElevation(0);
    }
}

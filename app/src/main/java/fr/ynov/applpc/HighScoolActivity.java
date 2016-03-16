package fr.ynov.applpc;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class HighScoolActivity extends ActionBarActivity {
//Pierre

    private RelativeLayout cdi = null;
    private RelativeLayout cvl = null;
    private RelativeLayout coordonnes = null;
    private RelativeLayout orientation = null;
    private RelativeLayout infos = null;

    private View.OnClickListener redirect_cdi = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(HighScoolActivity.this, CdiActivity.class);

            startActivity(intent);
        }
    };
    private View.OnClickListener redirect_cvl = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(HighScoolActivity.this, CvlActivity.class);

            startActivity(intent);
        }
    };
    private View.OnClickListener redirect_coordonnes = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(HighScoolActivity.this, ContactActivity.class);

            startActivity(intent);
        }
    };
    private View.OnClickListener redirect_orientation = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(HighScoolActivity.this, OrietationActivity.class);

            startActivity(intent);
        }
    };
    private View.OnClickListener redirect_infos = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(HighScoolActivity.this, InfosActivity.class);

            startActivity(intent);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setCustomActionBar();
        setContentView(R.layout.activity_high_scool);
<<<<<<< HEAD

        cdi = (RelativeLayout) findViewById(R.id.relative_CDI);
        cvl = (RelativeLayout) findViewById(R.id.relative_CVL);
        coordonnes = (RelativeLayout) findViewById(R.id.relative_coordonnes);
        orientation = (RelativeLayout) findViewById(R.id.relative_orientation);
        infos = (RelativeLayout) findViewById(R.id.relative_infos);

        cdi.setOnClickListener(redirect_cdi);
        cvl.setOnClickListener(redirect_cvl);
        coordonnes.setOnClickListener(redirect_coordonnes);
        orientation.setOnClickListener(redirect_orientation);
        infos.setOnClickListener(redirect_infos);

    }
    @Override
    public void onStart() {
        super.onStart();
    }






















=======
        //Temporaire
        final Button buttonCdi = (Button) findViewById(R.id.CDI);
        buttonCdi.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HighScoolActivity.this, CdiActivity.class);
                startActivity(intent);
            }
        });
        final Button buttonCvl = (Button) findViewById(R.id.CVL);
        buttonCvl.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HighScoolActivity.this, CvlActivity.class);
                startActivity(intent);
            }
        });
        final Button buttonInfos = (Button) findViewById(R.id.Infos);
        buttonInfos.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HighScoolActivity.this, InfosActivity.class);
                startActivity(intent);
            }
        });
        //Fin temporaire
    }

    private void setCustomActionBar(){
        getSupportActionBar().setTitle("Lycée");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.high_school_primary)));
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.high_school_fleche_retour);
        getSupportActionBar().setElevation(0);
    }
>>>>>>> a33683127c35dbf545dab28e20408f7aa2f0aa5e

    public void redirectCDI() {
        //  Intent i = new Intent(this, YourOtherActivity.class);
        //  startActivity(i);
    }

    public void redirectCVL() {
        //   Intent i = new Intent(this, YourOtherActivity.class);
        //   startActivity(i);
    }

    public void redirectCoordonnees() {
        //   Intent i = new Intent(this, YourOtherActivity.class);
        // startActivity(i);
    }

    public void redirectOrientation() {
        //   Intent i = new Intent(this, YourOtherActivity.class);
        //    startActivity(i);
    }

    public void redirectInfos() {
        //   Intent i = new Intent(this, YourOtherActivity.class);
        //    startActivity(i);
    }
}

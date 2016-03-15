package fr.ynov.applpc;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

public class HighScoolActivity extends ActionBarActivity {
//Pierre
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setCustomActionBar();
        setContentView(R.layout.activity_high_scool);
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

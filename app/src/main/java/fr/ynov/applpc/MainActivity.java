package fr.ynov.applpc;


import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import fr.ynov.applpc.data.DataParents;
import fr.ynov.applpc.data.DataStudents;

public class MainActivity extends AppCompatActivity {
    private RelativeLayout lycee = null;
    private RelativeLayout eleve = null;
    private RelativeLayout parent = null;

    private View.OnClickListener redirect_lycee = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, HighScoolActivity.class);

            startActivity(intent);
        }
    };

    private View.OnClickListener redirect_eleve = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, StudentsActivity.class);

            startActivity(intent);
        }
    };

    private View.OnClickListener redirect_parent = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, ParentsActivity.class);

            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lycee = (RelativeLayout) findViewById(R.id.accueil_rl_lycee);
        eleve = (RelativeLayout) findViewById(R.id.accueil_rl_eleve);
        parent = (RelativeLayout) findViewById(R.id.accueil_rl_parent);

        lycee.setOnClickListener(redirect_lycee);
        eleve.setOnClickListener(redirect_eleve);
        parent.setOnClickListener(redirect_parent);

    }

    @Override
    public void onStart() {
        super.onStart();
        downloadDatas();
    }

    private void downloadDatas() {
        DataStudents dataStudents = new DataStudents(this);
        dataStudents.getClasses();
        dataStudents.getScheduleByClass("Terminale SIN");

    }


}

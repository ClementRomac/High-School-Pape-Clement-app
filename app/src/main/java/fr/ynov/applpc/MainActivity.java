package fr.ynov.applpc;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import android.view.View;
import android.widget.RelativeLayout;

public class MainActivity extends ActionBarActivity {
    private RelativeLayout highschool = null;
    private RelativeLayout students = null;
    private RelativeLayout parents = null;

    private View.OnClickListener redirect_lycee = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, HighSchoolActivity.class);

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
        getSupportActionBar().hide();
        highschool = (RelativeLayout) findViewById(R.id.rl_main_highschool);
        students = (RelativeLayout) findViewById(R.id.rl_main_students);
        parents = (RelativeLayout) findViewById(R.id.rl_main_parents);

        highschool.setOnClickListener(redirect_lycee);
        students.setOnClickListener(redirect_eleve);
        parents.setOnClickListener(redirect_parent);

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    /*private void downloadDatas() {
        DataStudentsProvider dataStudentsProvider = new DataStudentsProvider(this);
        dataStudentsProvider.getClasses();
        dataStudentsProvider.getScheduleByClass("Terminale SIN");

    }*/


}

package fr.ynov.applpc;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

public class StudentsActivity extends ActionBarActivity {
//Pierre
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);

        Spinner spinner;
        //http://marclabs.com/comment-configurer-spinner-liste-deroulante-sur-android
        //Récupération du Spinner déclaré dans le fichier activity_students.xml de res/layout
        spinner = (Spinner) findViewById(R.id.spinnerClass);
        //Création d'une liste d'élément à mettre dans le Spinner(pour l'exemple)
        List listClasses = new ArrayList();
        listClasses.add("Seconde A");
        listClasses.add("Seconde B");
        listClasses.add("Seconde C");

 /*Le Spinner a besoin d'un adapter pour sa presentation alors on lui passe le context(this) et
                un fichier de presentation par défaut( android.R.layout.simple_spinner_item)
 Avec la liste des elements (exemple) */
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,listClasses);


               /* On definit une présentation du spinner quand il est déroulé         (android.R.layout.simple_spinner_dropdown_item) */
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Enfin on passe l'adapter au Spinner et c'est tout
        spinner.setAdapter(adapter);
    }
}

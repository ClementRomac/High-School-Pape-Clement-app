package fr.ynov.applpc;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import fr.ynov.applpc.data.DataCDIProvider;
import fr.ynov.applpc.data.DataStudentsProvider;

public class StudentsActivity extends ActionBarActivity {
//Pierre
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);

        ////////////////////////Partie Spinner/////////////////////
        Spinner spinner;
        //http://marclabs.com/comment-configurer-spinner-liste-deroulante-sur-android
        //Récupération du Spinner déclaré dans le fichier activity_students.xml de res/layout
        spinner = (Spinner) findViewById(R.id.spinnerClass);
        //Création d'une liste d'élément à mettre dans le Spinner(pour l'exemple)
        List listClasses = new ArrayList();
        downloadClasses(listClasses);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listClasses);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Enfin on passe l'adapter au Spinner et c'est tout
        spinner.setAdapter(adapter);
        spinner.setSelection(0);//default value


        ////////////////////////Partie Calendar/////////////////////


        String studentClasses = spinner.getSelectedItem().toString();
        ;
        String[][] Calendar = downloadCalendar(studentClasses);
        WriteCalendar(Calendar);

        // Creating adapter for spinner


        // Drop down layout style - list view with radio button
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(adapter);
    }

        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            // On selecting a spinner item
            String item = parent.getItemAtPosition(position).toString();

        String studentClasses=item;
        String[][] Calendar  =downloadCalendar(studentClasses);
        WriteCalendar(Calendar);

        }
    private void WriteCalendar( String[][] studentClasses){
        final TextView textLundi = (TextView) findViewById(R.id.textLundi);
        final TextView textMardi = (TextView) findViewById(R.id.textMardi);
        final TextView textMercredi = (TextView) findViewById(R.id.textMercredi);
        final TextView textJeudi = (TextView) findViewById(R.id.textJeudi);
        final TextView textVendredi = (TextView) findViewById(R.id.textVendredi);

        textLundi.setText(studentClasses[0][0]);
        textMardi.setText(studentClasses[0][1]);
        textMercredi.setText(studentClasses[0][2]);
        textJeudi.setText(studentClasses[0][3]);
        textVendredi.setText(studentClasses[0][4]);
    }
    private void downloadClasses(List listClasses){
        DataStudentsProvider DataStudentsProvider = new DataStudentsProvider(this);
        listClasses.addAll(Arrays.asList(DataStudentsProvider.getClasses()));
    }
    private String[][] downloadCalendar(String studentClasses){
        DataStudentsProvider DataStudentsProvider = new DataStudentsProvider(this);
        return DataStudentsProvider.getScheduleByClass(studentClasses);
    }
}

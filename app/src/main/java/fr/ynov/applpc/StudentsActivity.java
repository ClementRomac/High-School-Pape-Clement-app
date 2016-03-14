package fr.ynov.applpc;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
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
        listClasses.add("Première A");
        listClasses.add("Première B");
        listClasses.add("Terminale A");
        listClasses.add("Terminale B");
        // TODO boucle requete sql récupérer nom de chaque classe
        /*Le Spinner a besoin d'un adapter pour sa presentation alors on lui passe le context(this) et
        un fichier de presentation par défaut( android.R.layout.simple_spinner_item)
        Avec la liste des elements (exemple) */
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,listClasses);
        /* On definit une présentation du spinner quand il est déroulé         (android.R.layout.simple_spinner_dropdown_item) */
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Enfin on passe l'adapter au Spinner et c'est tout
        spinner.setAdapter(adapter);
        spinner.setSelection(0);//default value



        //Récupération de la listview créée dans le fichier main.xml
        ListView maListViewPerso = (ListView) findViewById(R.id.listviewCalendar);

        //Création de la ArrayList qui nous permettra de remplire la listView
        ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String, String>>();

        //On déclare la HashMap qui contiendra les informations pour un item
        HashMap<String, String> map;

        //Création d'une HashMap pour insérer les informations du premier item de notre listView
        map = new HashMap<String, String>();
        //on insère un élément titre que l'on récupérera dans le textView titre créé dans le fichier affichageitem.xml
        map.put("day", "Lundi");
        //on insère un élément description que l'on récupérera dans le textView description créé dans le fichier affichageitem.xml
        map.put("text", "Math 8h ");
        //on insère la référence à l'image (convertit en String car normalement c'est un int) que l'on récupérera dans l'imageView créé dans le fichier affichageitem.xml
        map.put("ligne", String.valueOf(R.drawable.vert));
        //enfin on ajoute cette hashMap dans la arrayList
        listItem.add(map);

        //On refait la manip plusieurs fois avec des données différentes pour former les items de notre ListView

        // TODO boucle requete sql récupérer nom de chaque classe
        map = new HashMap<String, String>();
        map.put("day", "Mardi");
        map.put("text", "svt 9h");
        map.put("ligne", String.valueOf(R.drawable.rouge));
        listItem.add(map);

        //Création d'un SimpleAdapter qui se chargera de mettre les items présent dans notre list (listItem) dans la vue affichageitem
        SimpleAdapter mSchedule = new SimpleAdapter(this.getBaseContext(), listItem, R.layout.model_listview_calendar,
                new String[] {"ligne", "day", "text"}, new int[] {R.id.ligne, R.id.day, R.id.text});

        //On attribut à notre listView l'adapter que l'on vient de créer
        maListViewPerso.setAdapter(mSchedule);

        //http://tutos-android-france.com/listview-afficher-une-liste-delements/
    }
}

package fr.ynov.applpc;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.ynov.applpc.data.DataStudentsProvider;

public class StudentsActivity extends ActionBarActivity implements AdapterView.OnItemSelectedListener {
//Pierre
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);
        setCustomActionBar();

        ////////////////////////Partie Spinner/////////////////////
        Spinner spinner;
        spinner = (Spinner) findViewById(R.id.spinner_students);
        List listClasses = new ArrayList();
        downloadClasses(listClasses);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listClasses);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        spinner.setSelection(0);//default value

    }

        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            // On selecting a spinner item
            String item = parent.getItemAtPosition(position).toString();

            String studentClasses = item;
            String[][] Calendar  = downloadCalendar(studentClasses);
            WriteCalendar(Calendar);
            TextView textViewClassSelected = (TextView) findViewById(R.id.tv_students_selected_class);
            textViewClassSelected.setText(studentClasses);

        }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void WriteCalendar( String[][] studentClasses){
        TextView textMonday = (TextView) findViewById(R.id.tv_students_monday_schedule);
        TextView textTuesday = (TextView) findViewById(R.id.tv_students_tuesday_schedule);
        TextView textWednesday = (TextView) findViewById(R.id.tv_students_wednesday_schedule);
        TextView textThursday = (TextView) findViewById(R.id.tv_students_thursday_schedule);
        TextView textFriday = (TextView) findViewById(R.id.tv_students_friday_schedule);

        textMonday.setText(studentClasses[0][0].replace("<br>", System.getProperty("line.separator")));
        textTuesday.setText(studentClasses[0][1].replace("<br>", System.getProperty("line.separator")));
        textWednesday.setText(studentClasses[0][2].replace("<br>", System.getProperty("line.separator")));
        textThursday.setText(studentClasses[0][3].replace("<br>", System.getProperty("line.separator")));
        textFriday.setText(studentClasses[0][4].replace("<br>", System.getProperty ("line.separator")));
    }
    private void downloadClasses(List listClasses){
        DataStudentsProvider DataStudentsProvider = new DataStudentsProvider(this);
        listClasses.addAll(Arrays.asList(DataStudentsProvider.getClasses()));
        //Log.d("test", DataStudentsProvider.getClasses()[1]);
    }
    private String[][] downloadCalendar(String studentClasses){
        DataStudentsProvider DataStudentsProvider = new DataStudentsProvider(this);
        return DataStudentsProvider.getScheduleByClass(studentClasses);
    }
    private void setCustomActionBar(){
        getSupportActionBar().setTitle("Elèves");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.students_primary)));
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.cdi_arrow);
        getSupportActionBar().setElevation(0);
    }
}

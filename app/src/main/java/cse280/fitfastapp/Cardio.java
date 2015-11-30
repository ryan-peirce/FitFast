package cse280.fitfastapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class Cardio extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    public static String cardioWorkout;
    private final String PREFS = "SharedPreferences";
    private SharedPreferences sPref;
    Integer caloriesBurned;
    TextView caloriesBurnedDisplay;
    final String caloriesKey = "calories_burned";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardio);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.cardio_workouts, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        caloriesBurnedDisplay = (TextView) findViewById(R.id.calories_burned_display);

        sPref = getSharedPreferences(PREFS, Context.MODE_PRIVATE);


    }

    @Override
    public void onResume(){
        super.onResume();
        caloriesBurned = sPref.getInt(caloriesKey,0);
        caloriesBurnedDisplay.setText(Integer.toString(caloriesBurned));
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {


        cardioWorkout = (String) parent.getItemAtPosition(pos);




        ;
    }

    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void calcBurned(View view){

        EditText timeText = (EditText) findViewById(R.id.edit_time);


        EditText distanceText = (EditText) findViewById(R.id.edit_distance);

        String type = "cardio";
        String workout = cardioWorkout;
        double coef = .1;
        if(workout.equals("Walking")){
            coef = .0085;
        }
        if(workout.equals("Running")){
            coef = .0124;
        }

        double time=0;
        double distance=0;

        try{
            time = Double.parseDouble(timeText.getText().toString());
            distance = Double.parseDouble(distanceText.getText().toString());
        }
        catch(NumberFormatException e){
            
        }

        int calsBurnedFromCardio = fitfast.fitness.Cardio.calcCalories(Double.parseDouble(sPref.getString("weight", "")), distance, time, coef);

        caloriesBurned += calsBurnedFromCardio;

        caloriesBurnedDisplay.setText(Integer.toString(caloriesBurned));

        SharedPreferences.Editor editor = sPref.edit();
        editor.putInt(caloriesKey,caloriesBurned);
        editor.commit();

    }

    public void back(View view) {
        //finishActivity(RESULT_OK);
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }

}

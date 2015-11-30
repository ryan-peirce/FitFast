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

public class Weights extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    public static String weightsWorkout;
    private final String PREFS = "SharedPreferences";
    private SharedPreferences sPref;
    Integer caloriesBurned;
    TextView caloriesBurnedDisplay;
    final String caloriesKey = "calories_burned";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weights);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.weights_workouts, android.R.layout.simple_spinner_item);

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


        weightsWorkout = (String) parent.getItemAtPosition(pos);




        ;
    }

    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void calcBurned(View view){

        EditText weightText = (EditText) findViewById(R.id.edit_weight);

        EditText repsText = (EditText) findViewById(R.id.edit_time);


        EditText setsText = (EditText) findViewById(R.id.edit_distance);

        String type = "Weights";
        String workout = weightsWorkout;
        int calsBurnedFromBody = 0;
        int sub = 0;
        double coeff = 0;

        if(workout.equals("Curls")){
            sub = 0;
            coeff = 0.005832861;
        }
        if(workout.equals("Bench-Press")){
            sub = 0;
            coeff = 0.00661057;
        }
        if(workout.equals("Squats")){
            sub = 1;
            coeff = 0.00972;
        }
        if(workout.equals("Deadlift")){
            sub = 1;
            coeff = 0.00972;
        }

        int weight =0;
        int reps = 0;
        int sets =0;

        try {
            weight = Integer.parseInt(weightText.getText().toString());
            reps = Integer.parseInt(repsText.getText().toString());
            sets = Integer.parseInt(setsText.getText().toString());
        }
        catch (NumberFormatException e){}


        calsBurnedFromBody = fitfast.fitness.WeightLift.calcCalories(Double.parseDouble(sPref.getString("weight", "")), (double) weight, reps, sets, coeff, sub);

        caloriesBurned += calsBurnedFromBody;

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

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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;




public class Workout extends AppCompatActivity {

    private final String PREFS = "SharedPreferences";
    private SharedPreferences sPref;
    Integer caloriesBurned;
    final String caloriesKey = "calories_burned";
    Toolbar toolbar;
    TextView caloriesBurnedDisplay;
    EditText calsBurned;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        caloriesBurnedDisplay = (TextView) findViewById(R.id.calories_burned_today);
        calsBurned = (EditText) findViewById(R.id.calories_burned);



        sPref = getSharedPreferences(PREFS, Context.MODE_PRIVATE);
    }

    @Override
    public void onResume(){
        super.onResume();
        caloriesBurned = sPref.getInt(caloriesKey,0);
        caloriesBurnedDisplay.setText(Integer.toString(caloriesBurned));
    }

    public void toDashboard(View view) {
        //finishActivity(RESULT_OK);
        Intent intent=new Intent();
        setResult(RESULT_OK,intent);
        finish();
    }



    public void addTotal(View view) {

        int calories;
        try {
            if (calsBurned.length() < 6) {
                calories = Integer.parseInt(calsBurned.getText().toString());
                SharedPreferences.Editor editor = sPref.edit();
                editor.putInt(caloriesKey, caloriesBurned += calories);
                editor.commit();
                caloriesBurnedDisplay.setText(Integer.toString(caloriesBurned));
                calsBurned.setText("");
            } else {
                Toast.makeText(getApplicationContext(), "Calories value is too large", Toast.LENGTH_LONG).show();
            }
        }
        catch(Exception e){
            Toast.makeText(getApplicationContext(), "Please enter a calorie value", Toast.LENGTH_LONG).show();
        }

    }

    public void cardio(View view){
        Intent intent = new Intent(this, Cardio.class);
        startActivityForResult(intent, RESULT_OK);


    }

    public void bodyWeight(View view){
        Intent intent = new Intent(this, BodyWeightExercise.class);
        startActivityForResult(intent, RESULT_OK);


    }

    public void weights(View view){
        Intent intent = new Intent(this, Weights.class);
        startActivityForResult(intent, RESULT_OK);


    }

}

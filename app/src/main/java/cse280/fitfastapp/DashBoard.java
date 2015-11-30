package cse280.fitfastapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class DashBoard extends AppCompatActivity {

    private String nameDefault = "set name";
    private String ageDefault = "set age";
    private String heightDefault = "set height";
    private String weightDefault = "set weight";
    private String goalDefault = "set goal";
    private String genderDefault = "set gender";

    private int totalCals = 0;
    private int totalCalsBurned = 0;
    private SharedPreferences sPref;
    private final String PREFS = "SharedPreferences";
    private final String genderKey = "gender";
    private final String nameKey = "name";
    private final String ageKey = "age";
    private final String heightKey = "height";
    private final String weightKey = "weight";
    private final String goalKey = "goal";
    private final String calInKey = "calories_in";
    private final String calBurnKey = "calories_burned";
    private int dailyCals;
    private int lbsLost ;
    private int lbsLeft;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sPref = getSharedPreferences(PREFS, Context.MODE_PRIVATE);

        updateTotalCals();
        updateTotalCalsBurned();
        updateRemainingCals();
        updateStats();
    }

    @Override
    public void onResume(){
        super.onResume();

        updateTotalCals();
        updateTotalCalsBurned();
        updateRemainingCals();
        updateStats();
    }

    public void updateStats(){

        dailyCals = Integer.parseInt(sPref.getString("target", "2000")) ;
        lbsLost = Integer.parseInt(sPref.getString("start", "200")) - Integer.parseInt(sPref.getString("weight", "200")) ;
        lbsLeft = Integer.parseInt(sPref.getString("weight", "200")) -  Integer.parseInt(sPref.getString("goal", "200"));


        TextView nameDisplay  = (TextView) findViewById(R.id.name_display);
        nameDisplay.setText(sPref.getString(nameKey,nameDefault));



        TextView weightDisplay  = (TextView) findViewById(R.id.weight_display);
        weightDisplay.setText(sPref.getString(weightKey,weightDefault) + " lb");


        TextView goalWeightDisplay  = (TextView) findViewById(R.id.goal_weight_display);
        goalWeightDisplay.setText(sPref.getString(goalKey,goalDefault) + " lb");


        TextView bmiDisplay  = (TextView) findViewById(R.id.bmi_display);
        bmiDisplay.setText(sPref.getString("bmi","N/A"));

        TextView naturalDisplay  = (TextView) findViewById(R.id.natural_display);
        naturalDisplay.setText(sPref.getString("daily","N/A"));

        TextView targetDisplay  = (TextView) findViewById(R.id.target_display);
        targetDisplay.setText(sPref.getString("target","N/A"));

        TextView diffDisplay  = (TextView) findViewById(R.id.difference_display);
        diffDisplay.setText(sPref.getString("diff", "N/A"));

        if(Integer.parseInt(sPref.getString("weight", "200")) >=  Integer.parseInt(sPref.getString("start", "200"))) {
            TextView lostDisplay = (TextView) findViewById(R.id.lbs_lost);
            lostDisplay.setText(Integer.toString(-lbsLost));
        }
        else{
            TextView lostDisplay = (TextView) findViewById(R.id.lbs_lost);
            lostDisplay.setText(Integer.toString(lbsLost));
        }

        if(Integer.parseInt(sPref.getString("weight", "200")) >=  Integer.parseInt(sPref.getString("goal", "200"))) {
            TextView leftDisplay = (TextView) findViewById(R.id.lbs_left);
            leftDisplay.setText(Integer.toString(lbsLeft));
        }
        else{
            TextView leftDisplay = (TextView) findViewById(R.id.lbs_left);
            leftDisplay.setText(Integer.toString(-lbsLeft));

        }

        TextView lostGainedDisplay  = (TextView) findViewById(R.id.lost_gained);
        lostGainedDisplay.setText("Lbs " + (Integer.parseInt(sPref.getString("start", "200")) >= Integer.parseInt(sPref.getString("weight", "200")) ? "Lost" : "Gained"));




    }

    public void updateTotalCals(){

        TextView calsInDisplay  = (TextView) findViewById(R.id.textView30);
        totalCals = sPref.getInt(calInKey,0);
        calsInDisplay.setText(Integer.toString(totalCals));


    }

    public void updateTotalCalsBurned(){

        TextView calsBurnedDisplay  = (TextView) findViewById(R.id.textView29);
        totalCalsBurned = sPref.getInt(calBurnKey,0);
        calsBurnedDisplay.setText(Integer.toString(totalCalsBurned));


    }

    public void updateRemainingCals(){

        TextView calsRemainingDisplay  = (TextView) findViewById(R.id.remaining_cals);
        calsRemainingDisplay.setText(Integer.toString(dailyCals - totalCals + totalCalsBurned));


    }

    public void openStats(View view) {
        Intent intent = new Intent(this, Stats.class);
        startActivityForResult(intent, RESULT_OK);

    }

    public void openCalorieInput(View view) {
        Intent intent = new Intent(this, CalorieInput.class);
        startActivityForResult(intent, RESULT_OK);

    }

    public void openHistory(View view) {
        SharedPreferences.Editor editor = sPref.edit();
        editor.putInt("calories_in", 0);
        editor.putInt("calories_burned",0);
        editor.commit();
        updateTotalCals();
        updateTotalCalsBurned();
        updateRemainingCals();


    }

    public void openWorkout(View view) {
        Intent intent = new Intent(this, Workout.class);
        startActivityForResult(intent, RESULT_OK);

    }

}